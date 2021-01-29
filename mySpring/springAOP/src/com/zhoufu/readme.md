# Spring 框架的  AOP 
Spring 框架的一个关键组件是面向方面的编程(AOP)：
面向方面的编程需要把程序逻辑分解成不同的部分称为所谓的关注点。
    跨一个应用程序的多个点的功能被称为横切关注点，这些横切关注点在概念上独立于应用程序的业务逻辑
    
在 OOP 中，关键单元模块度是类，而在 AOP 中单元模块度是方面
    依赖注入帮助你对应用程序对象相互解耦和 AOP 可以帮助你从它们所影响的对象中对横切关注点解耦
Spring AOP 模块提供拦截器来拦截一个应用程序，例如，当执行一个方法时，你可以在方法执行之前或之后添加额外的功能

## AOP 术语
|术语|描述|
Aspect      一个模块具有一组提供横切需求的 APIs。例如，一个日志模块为了记录日志将被 AOP 方面调用
            应用程序可以拥有任意数量的方面，这取决于需求
JoinPoint   切入点，即在实际的应用程序中，其中一个操作将使用 Spring AOP 框架
Advice      实际行动之前或之后执行的方法。这是在程序执行期间通过 Spring AOP 框架实际被调用的代码
Pointcut    这是一组一个或多个连接点，通知应该被执行
Introduction    引用允许你添加新方法或属性到现有的类中
TargetObject    被一个或者多个方面所通知的对象，这个对象永远是一个被代理对象。也称为被通知对象
Weaving     Weaving 把方面连接到其它的应用程序类型或者对象上，并创建一个被通知的对象。这些可以在编译时，类加载时和运行时完成

## AOP 自定义
Spring 支持 @AspectJ annotation style 的方法
        和基于模式的方法来实现自定义AOP
1.  XML Schema based    使用常规类以及基于配置的 XML 来实现
2.  @AspectJ based       @AspectJ 引用一种声明方面的风格作为带有 Java 5 注释的常规 Java 类注释

### Spring 中基于 AOP 的 XML架构(xml中定义AOP)
需要导入aop命名空间标签
1.  声明一个aspect
```xml
<aop:config>
   <aop:aspect id="myAspect" ref="aBean">
   ...
   </aop:aspect>
</aop:config>
<bean id="aBean" class="...">
...
</bean>
```
2.  声明一个切入点
```xml
<aop:config>
   <aop:aspect id="myAspect" ref="aBean">
   <aop:pointcut id="businessService"
      expression="execution(* com.xyz.myapp.service.*.*(..))"/>
   ...
   </aop:aspect>
</aop:config>
<bean id="aBean" class="...">
...
</bean>
```

### Spring 中基于 AOP 的 @AspectJ
@AspectJ 作为通过 Java 5 注释注释的普通的 Java 类，它指的是声明 aspects 的一种风格
1. 声明一个Aspect
Aspects 类和其他任何正常的 bean 一样，除了它们将会用 @AspectJ 注释之外，它和其他类一样可能有方法和字段
```java
package org.xyz;
import org.aspectj.lang.annotation.Aspect;
@Aspect
public class AspectModule {
}
```
然后将在 XML 中按照如下进行配置，就和其他任何 bean 一样:
```xml
<bean id="myAspect" class="org.xyz.AspectModule">
   <!-- configure properties of aspect here as normal -->
</bean>
```
2. 声明一个切入点 @Pointcut()
3. 声明建议(advice)
@Before()  @After() @AfterReturning @AfterThrowing @ Around








