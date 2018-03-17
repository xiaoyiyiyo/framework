

Aop UML:

 Advisor :

![aop_advisor](https://raw.githubusercontent.com/xiaoyiyiyo/framework/master/img/aop_advisor.png)



Invocation :

![aop_invocation](https://raw.githubusercontent.com/xiaoyiyiyo/framework/master/img/aop_invocation.png)



Interceptor :

![aop_interceptor](https://raw.githubusercontent.com/xiaoyiyiyo/framework/master/img/aop_interceptor.png)



Core:![aop_core](https://raw.githubusercontent.com/xiaoyiyiyo/framework/master/img/aop_core.png)



AOP流程：

1. 创建AopApplicationContext实例，加载application.json文件，调用JsonUtils解析生成List<AopBeanDefinition>.

   application.json :

   ```json
   [
     {
       "name":"beforeMethod",
       "className":"com.xiaoyiyiyo.aop.StartTimeBeforeMethod"
     },
     {
       "name":"afterMethod",
       "className":"com.xiaoyiyiyo.aop.EndTimeAfterMethod"
     },
     {
       "name":"testService",
       "className":"com.xiaoyiyiyo.aop.TestService"
     },
     {
       "name":"testServiceProxy",
       "className":"com.xiaoyiyiyo.aop.bean.ProxyFactoryBean",
       "target":"testService",
       "adviceNames":[
         "beforeMethod",
         "afterMethod"
       ]
     }
   ]
   ```

   AopBeanDefinition:

   ```java
   public class AopBeanDefinition extends BeanDefinition {

       private String target;

       private List<String> adviceNames;
   ```

   ​

2. 注册bean: 

   根据AopBeanDefinition.getClass()判断，如果为ProxyFactoryBean.class, 则放入AopBeanDefinitionMap中, 如果不是，则转型为BeanDefinition后放入BeanDefinitionMap中.

   （key值都为beanDefinition.getClassName()）

3. getBean("testServiceProxy") : 

   3.1. 

   如果在AopBeanDefinitionMap中存在，则生成AdvisorSupport对象实例, 之后被CglibAopProxy使用。如果不存在，调用父类getBean(name).

   ```java
   public class AdvisorSupport extends Advisor {

       private TargetSource targetSource;

       private List<AopMethodInterceptor> list = new LinkedList<AopMethodInterceptor>();
     ...
   ```

    通过aopBeanDefinition.getAdviceNames获取所有adviceName, 然后调用getBean(adviceName)生成advice实例，将其封装成相对应的自定义Interceptor，然后添加到AdvisorSupport.list中。

   ```java
   public class BeforeRunningAdviceInterceptor implements AopMethodInterceptor {

       private BeforeRunningAdvice advice;

       public BeforeRunningAdviceInterceptor(BeforeRunningAdvice advice) {
           this.advice = advice;
       }

       public Object invoke(MethodInvocation mi) throws Throwable {
           advice.before(mi.getMethod(), mi.getArguments(), mi);
           return mi.proceed();
       }
   }
   ```

   通过aopBeanDefinition.getTarget获取目标对象名称，然后getBean(serviceName)生成目标对象，封装到TargetSource对象中，然后set到AdvisorSupport.targetSource中。

   3.2 

   将AdvisorSupport注入到CglibAopProxy中，CglibAopProxy调用getProxy利用Cglib生成targetClass代理对象。

   ```java
   public Object getProxy(ClassLoader classLoader) {
           Class<?> rootClass = advisorSupport.getTargetSource().getTargetCLass();

           if (classLoader == null) {
               classLoader = ClassUtils.getDefultClassLoader();
           }

           Enhancer enhancer = new Enhancer();
           enhancer.setSuperclass(rootClass.getSuperclass());
           Callback callback = getCallBack(advisorSupport);
           enhancer.setCallback(callback);
           enhancer.setClassLoader(classLoader);

           if (constructorArgs != null && constructorArgs.length > 0) {
               return enhancer.create(constructorArgTypes, constructorArgs);
           }

           return enhancer.create();
       }

   private Callback getCallBack(AdvisorSupport advisorSupport) {
           return new DynamicAdvisedInterceptor(advisorSupport.getList(), advisorSupport.getTargetSource());
       }
   ```

   其中Callback: DynamicAdvisedInterceptor继承自cglib的MethodInterceptor，重写intercept方法。

   ```java
       public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
           MethodInvocation invocation = new CglibMethodInvocation(o, targetSource.getTargetObject(),
                   method, objects, interceptorList, methodProxy);
           return invocation.proceed();
       }
   ```

4. 得到代理对象后，转型为目标类型（TestService）,调用相应的service方法。这样其实调用的proxy的service方法，会触发Callback的intercept方法（参见上面代码），生成CglibMethodInvocation对象。

   CglibMethodInvocation 继承自 ReflectionMethodInvocation，其实现ProxyMethodInvocation接口。

   当调用proceed()方法时：

   ```java
   //ReflectionMethodInvocation
       public Object proceed() throws Throwable {

           if (currentInterceptorIndex == this.interceptorList.size() - 1) {
               return invokeOriginal();   //注意这里
           }

           AopMethodInterceptor interceptor = interceptorList.get(++currentInterceptorIndex);
           return interceptor.invoke(this);   //注意这里，传入this，根据currentInterceptorIndex来决定是调用advice的增加方法，还是调用目标类原始方法
       }
   ```

   CglibMethodInvocation重写了invokeOriginal方法, 利用cglib的MethodProxy调用目标类的原始方法。

   ```java
       @Override
       protected Object invokeOriginal() throws Throwable {
           return methodProxy.invoke(target, arguments);
       }
   ```

   ​

