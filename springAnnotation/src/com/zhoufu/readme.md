# Spring 基于注解的配置(Beans.xml)
一旦 被配置后，你就可以开始注解你的代码，表明 Spring 应该自动连接值到属性，方法和构造函数
|注解|描述|
@Required   @Required 注解应用于 bean 属性的 setter 方法
@Autowired  @Autowired 注解可以应用到 bean 属性的 setter 方法，非 setter 方法，构造函数和属性
@Qualifier  通过指定确切的将被连线的 bean，@Autowired 和 @Qualifier 注解可以用来删除混乱
JSR-250 Annotations Spring 支持 JSR-250 的基础的注解，其中包括了 @Resource，@PostConstruct 和 @PreDestroy 注解

## Spring @Required注解   set值注入
@Required 注释应用于 bean 属性的 setter 方法，
@Required注解作用于Bean的setter方法上，用于检查一个Bean的属性的值在配置期间是否被赋予或设置

倘若配置了@Required的属性，在XML里面没有被配置的话，就会报错bean初始化异常BeanInitializationException,取不到响应的值

## Spring @Autowired注解  自动装配
这个注释的功能就是为我们注入一个定义好的 bean
@autowired 注释来源于英文单词 autowire,这个单词的意思是自动装配的意思
在 Spring 的世界当中，自动装配指的就是使用将 Spring 容器中的 bean 自动的和我们需要这个 bean 的类组装在一起

## Spring @Qualifier注解 
当你创建多个具有相同类型的 bean 时，并且想要用一个属性只为它们其中的一个进行装配，
在这种情况下，你可以使用 @Qualifier 注释和 @Autowired 注释通过指定哪一个真正的 bean 将会被装配来消除混乱
@Autowired
@Qualifier("sd")

## Spring 基于Java的配置 (不再使用xml)
@Configuration 和 @Bean 注解：
带有 @Configuration 的注解类表示这个类可以使用 Spring IoC 容器作为 bean 定义的来源
@Bean 注解告诉 Spring，一个带有 @Bean 的注解方法将返回一个对象，该对象应该被注册为在 Spring 应用程序上下文中的 bean
```java
package com.tutorialspoint;
import org.springframework.context.annotation.*;
@Configuration
public class HelloWorldConfig {
   @Bean 
   public HelloWorld helloWorld(){
      return new HelloWorld();
   }
}
// 带有 @Bean 注解的方法名称作为 bean 的 ID，它创建并返回实际的 bean。你的配置类可以声明多个 @Bean
```
等同于====>
```xml
<beans>
   <bean id="helloWorld" class="com.tutorialspoint.HelloWorld" />
</beans>
```
定义了配置类，就可以使用 AnnotationConfigApplicationContext 来加载并把他们提供给 Spring 容器

## Spring 的事件处理
切记 ： Spring 的核心是 ApplicationContext，它负责管理 beans 的完整生命周期

在ApplicationContext加载beans的时候，会发布某些类型的事件：
例如，当上下文启动时，ContextStartedEvent 发布，当上下文停止时，ContextStoppedEvent 发布

所以：
通过 ApplicationEvent 类和 ApplicationListener 接口来提供在 ApplicationContext 中处理事件
如果一个 bean 实现 ApplicationListener，那么每次 ApplicationEvent 被发布到 ApplicationContext 上，
那个 bean 会被通知

注：  由于 Spring 的事件处理是单线程的，所以如果一个事件被发布，直至并且除非所有的接收者得到的该消息，
        该进程被阻塞并且流程将不会继续
        
|Spring内置事件|描述|
ContextRefreshedEvent       ApplicationContext 被初始化或刷新时，该事件被发布。这也可以在 ConfigurableApplicationContext 接口中使用 refresh() 方法来发生
ContextStartedEvent         当使用 ConfigurableApplicationContext 接口中的 start() 方法启动 ApplicationContext 时，该事件被发布
ContextStoppedEvent         当使用 ConfigurableApplicationContext 接口中的 stop() 方法停止 ApplicationContext 时，发布这个事件
ContextClosedEvent          当使用 ConfigurableApplicationContext 接口中的 close() 方法关闭 ApplicationContext 时，该事件被发布
RequestHandledEvent         这是一个 web-specific 事件，告诉所有 bean HTTP 请求已经被服务


## Spring 自定义事件
1.  通过扩展 ApplicationEvent,创建一个事件类 CustomEvent。这个类必须定义一个默认的构造函数，
    它应该从 ApplicationEvent 类中继承的构造函数
2.  一旦定义事件类，你可以从任何类中发布它，假定 EventClassPublisher 实现了 ApplicationEventPublisherAware
    你还需要在 XML 配置文件中声明这个类作为一个 bean，之所以容器可以识别 bean 作为事件发布者，
    是因为它实现了 ApplicationEventPublisherAware 接口
3.  发布的事件可以在一个类中被处理，假定 EventClassHandler 实现了 ApplicationListener 接口，
    而且实现了自定义事件的 onApplicationEvent 方法






