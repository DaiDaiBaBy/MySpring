# Spring IOC 容器
Spring容器是Spring的框架的核心，容器将创建对象，把它们练习在一起，配置它们，并管理它们的整个生命周期从创建到销毁
Spring容器使用依赖注入(DI)来管理组成一个应用程序的组件。这些对象被称为Spring Beans；

Spring IoC 容器利用 Java 的 POJO 类和配置元数据来生成完全配置和可执行的系统或应用程序

IOC 容器具有依赖注入功能的容器，它可以创建对象，IOC 容器负责实例化、定位、配置应用程序中的对象及建立这些对象间的依赖
通常new一个实例，控制权由程序员控制，而"控制反转"是指new实例工作不由程序员来做而是交给Spring容器来做
在Spring中BeanFactory是IOC容器的实际代表者

## Spring提供了两种不同类型的容器 BeanFactory和ApplicationContext
ApplicationContext 容器包括 BeanFactory 容器的所有功能，所以通常不建议使用BeanFactory
ApplicationContext是BeanFactory的子接口

### Spring 的 BeanFactory容器
最简单的容器，主要的功能是为依赖注入DI提供支持，这个容器接口在org.springframework.beans.factory.BeanFactory中被定义

在 Spring 中，有大量对 BeanFactory 接口的实现。其中，最常被使用的是 XmlBeanFactory 类
这个容器从一个 XML 文件中读取配置元数据，由这些元数据来生成一个被配置化的系统或者应用

### Spring 的 ApplicationContext容器
ApplicationContext除了拥有BeanFactory的所有功能外，还增加了一些另外的企业级功能，比如：
从属性文件中解析文本信息和将事件传递给所指定的监听器：
这个容器在 org.springframework.context.ApplicationContext interface 接口中定义
 常被使用到的ApplicationContext接口实现：
    FileSystemXmlApplicationContext：该容器从XML文件中加载已被定义的bean。在这里，你需要提供给构造器 XML 文件的完整路径
    ClassPathXmlApplicationContext：该容器从 XML 文件中加载已被定义的 bean。在这里，你不需要提供 XML 文件的完整路径，
        只需正确配置 CLASSPATH 环境变量即可，因为，容器会从 CLASSPATH 中搜索 bean 配置文件
    WebXmlApplicationContext：该容器会在一个 web 应用程序的范围内加载在 XML 文件中已被定义的 bean

## Spring Bean的定义
```xml
<bean id="beanFactory_helloWorld" class="com.zhoufu.spring_ioc.BeanFactory.HelloWorld">
    <property name="message" value="Spring BeanFactory HelloWorld!" />
</bean>
```
1.1 如何创建一个 bean
bean 是一个被实例化，组装，并通过 Spring IoC 容器所管理的对象。这些 bean 是由用容器提供的配置元数据创建的
class	这个属性是强制性的，并且指定用来创建 bean 的 bean 类,比如HelloWorld
name    这个属性指定唯一的 bean 标识符。在基于 XML 的配置元数据中，你可以使用 ID 和/或 name 属性来指定 bean 标识符
1.2`bean 的生命周期的详细信息

1.3`bean 的依赖关系

## Spring Bean 的 作用域  (bean属性使用 scope配置  scope="singleton")
当在 Spring 中定义一个 bean 时，你必须声明该 bean 的作用域的选项
比如：
为了强制 Spring 在每次需要时都产生一个新的 bean 实例，你应该声明 bean 的作用域的属性为 prototype
如果想让 Spring 在每次需要时都返回同一个bean实例，你应该声明 bean 的作用域的属性为 singleton(单例)
Spring 框架支持以下五个作用域，分别为 singleton、prototype、request、session 和 global session

|作用域|描述|
singleton       在spring IoC容器仅存在一个Bean实例，Bean以单例方式存在，默认值（只要 id 与该 bean 定义相匹配，则只会返回 bean 的同一实例）
prototype       每次从容器中调用Bean时，都返回一个新的实例，即每次调用getBean()时，相当于执行newXxxBean()
request         每次HTTP请求都会创建一个新的Bean，该作用域仅适用于WebApplicationContext环境
session         同一个HTTP Session共享一个Bean，不同Session使用不同的Bean，仅适用于WebApplicationContext环境
global-session  一般用于Portlet应用环境，该作用域仅适用于WebApplicationContext环境  


## Spring Bean 生命周期
Bean的生命周期可以表达为：Bean的定义——Bean的初始化——Bean的使用——Bean的销毁
当一个 bean 被实例化时，它可能需要执行一些初始化使它转换成可用状态；
同样，当 bean 不再需要，并且从容器中移除时，可能需要做一些清除工作

为了定义一个Bean的初始化与销毁：声明带有 init-method 和/或 destroy-method 参数的bean，两者都指定对应的方法即可回调
实现接口回调：不推荐，还是xml配置灵活
    初始化回调：org.springframework.beans.factory.InitializingBean 
    销毁回调：org.springframework.beans.factory.DisposableBean

在基于 XML 的配置元数据的情况下，你可以使用 init-method 属性来指定带有 void 无参数方法的名称。例如：
```xml
<bean id="exampleBean" 
         class="examples.ExampleBean" init-method="init"/>
<bean id="exampleBean"
         class="examples.ExampleBean" destroy-method="destroy"/>
```
```java
public class ExampleBean {
   public void init() {
      // do some initialization work
   }
}
public class ExampleBean {
   public void destroy() {
      // do some destruction work
   }
}
```
默认的初始化和销毁方法：
倘若有太多具有相同名称的初始化或者销毁方法的bean，则不必在每个bean上去进行method配置
只需在beans里面配置一个默认的即可 default-init-method 和 default-destroy-method 
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
    default-init-method="init" 
    default-destroy-method="destroy">
   <bean id="..." class="...">
       <!-- collaborators and configuration for this bean go here -->
   </bean>
</beans>
```

## Spring Bean 后置处理器 BeanPostProcessor
Bean 后置处理器允许在调用初始化方法前后对 Bean 进行额外的处理
BeanPostProcessor ​接口定义回调方法，你可以实现该方法来提供自己的实例化逻辑，依赖解析逻辑等
配置多个BeanPostProcessor ​接口，通过设置 ​BeanPostProcessor ​实现的​ Ordered ​接口提供的​ order​ 属性来控制这些​ BeanPostProcessor​ 接口的执行顺序

ApplicationContext​ 会自动检测由 ​BeanPostProcessor​ 接口的实现定义的 ​bean​，注册这些​ bean​ 为后置处理器，然后通过在容器中创建​ bean​，在适当的时候调用它
 在你自定义的的​ BeanPostProcessor​ 接口实现类中，要实现以下的两个抽象方法  
 ​BeanPostProcessor.postProcessBeforeInitialization(Object, String)​ 和
 ​BeanPostProcessor.postProcessAfterInitialization(Object, String)​ 和，注意命名要准确
 
 
## Spring Bean定义继承 bean--> parent配置父Id值
```xml
<bean id="helloWorld" class="com.tutorialspoint.HelloWorld">
      <property name="message1" value="Hello World!"/>
      <property name="message2" value="Hello Second World!"/>
   </bean>
<bean id="helloIndia" class="com.tutorialspoint.HelloIndia" parent="helloWorld">
      <property name="message1" value="Hello India!"/>
      <property name="message3" value="Namaste India!"/>
   </bean>
```
 
 
# Spring 依赖注入DI
Spring框架的核心功能之一就是通过依赖注入的方式来管理Bean之间的依赖关系
```java
public class TextEditor {
   private SpellChecker spellChecker;
   public TextEditor(SpellChecker spellChecker) {
      this.spellChecker = spellChecker;
   }
}
```
上面一段代码是在spring控制反转IOC的场景中，两个类的依赖关系显示：
在这里，TextEditor 不应该担心 SpellChecker 的实现。SpellChecker 将会独立实现，
并且在 TextEditor 实例化的时候将提供给 TextEditor，整个过程是由 Spring 框架的控制

## Spring 基于构造函数的依赖注入
当容器调用带有一组参数的类构造函数时，基于构造函数的 DI 就完成了，其中每个参数代表一个对其他类的依赖

如果存在不止一个参数时，当把参数传递给构造函数时，可能会存在歧义
要解决这个问题，那么构造函数的参数在 bean 定义中的顺序就是把这些参数提供给适当的构造函数的顺序就可以了

## Spring 基于设值函数的依赖注入
当容器调用一个无参的构造函数或一个无参的静态 factory 方法来初始化你的 bean 后，
通过容器在你的 bean 上调用设值函数，基于设值函数的 DI 就完成了

 
 
 
 
 
 
 