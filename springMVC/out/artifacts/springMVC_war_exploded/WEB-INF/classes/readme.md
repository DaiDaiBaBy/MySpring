# Spring Web MVC 框架
  MVC 框架提供了模型-视图-控制的体系结构和可以用来开发灵活、松散耦合的 web 应用程序的组件
  MVC 模式导致了应用程序的不同方面(输入逻辑、业务逻辑和 UI 逻辑)的分离，同时提供了在这些元素之间的松散耦合
  模型--封装了应用程序数据，并且通常它们由 POJO 组成。
  视图--主要用于呈现模型数据，并且通常它生成客户端的浏览器可以解释的 HTML 输出。
  控制器--主要用于处理用户请求，并且构建合适的模型并将其传递到视图呈现 
  
## DispatcherServlet
mvc框架核心主要是围绕 dispatcherServlet来设计的，DispatcherServlet用于处理所有的Http请求和响应，大致工作流百度可画图分析
文字叙述：  DispatcherServlet 传入 HTTP 请求的事件序列
```text
收到一个 HTTP 请求后，DispatcherServlet 根据 HandlerMapping 来选择并且调用适当的控制器
控制器接受请求，并基于使用的 GET 或 POST 方法来调用适当的 service 方法
Service 方法将设置基于定义的业务逻辑的模型数据，并返回视图名称到 DispatcherServlet 中
DispatcherServlet 会从 ViewResolver 获取帮助，为请求检取定义视图
一旦确定视图，DispatcherServlet 将把模型数据传递给视图，最后呈现在浏览器中
```
```text
配置：
    要让 DispatcherServlet 处理的请求，需要通过使用在 web.xml 文件中的一个 URL 映射
    下面是一个显式声明和映射 HelloWeb DispatcherServlet 的示例：
web.xml 文件将被保留在你的应用程序的 WebContent/WEB-INF 目录下。好的，在初始化HelloWeb DispatcherServlet 时，
    该框架将尝试加载位于该应用程序的 WebContent/WEB-INF 目录中文件名为 [servlet-name]-servlet.xml 的应用程序内容
    在这种情况下，我们的文件将是 HelloWeb-servlet.xml
接下来，servlet-mapping 标签表明哪些 URLs 将被 DispatcherServlet 处理
    这里所有以 .jsp 结束的 HTTP 请求将由 HelloWeb DispatcherServle t处理
```
如果你不想使用默认文件名 [servlet-name]-servlet.xml 和默认位置 WebContent/WEB-INF，
你可以通过在 web.xml 文件中添加 servlet 监听器 ContextLoaderListener 自定义该文件的名称和位置，如下所示:
```xml
<web-app...>
....
<context-param>
   <param-name>contextConfigLocation</param-name>
   <param-value>/WEB-INF/HelloWeb-servlet.xml</param-value>
</context-param>
<listener>
   <listener-class>
      org.springframework.web.context.ContextLoaderListener
   </listener-class>
</listener>
</web-app>
```
检查 HelloWeb-servlet.xml 文件的请求配置，该文件位于 web 应用程序的 WebContent/WEB-INF 目录下：
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:context="http://www.springframework.org/schema/context"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="
   http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
   http://www.springframework.org/schema/context 
   http://www.springframework.org/schema/context/spring-context-3.0.xsd">
   <context:component-scan base-package="com.zhoufu" />

   <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
      <property name="prefix" value="/WEB-INF/jsp/" />
      <property name="suffix" value=".jsp" />
   </bean>

</beans>
```

备注：
```text
关于 HelloWeb-servlet.xml 文件的一些要点：
[servlet-name]-servlet.xml 文件将用于创建 bean 定义，重新定义在全局范围内具有相同名称的任何已定义的 bean
 <context:component-scan>标签将用于激活 Spring MVC 注释扫描功能，该功能允许使用注释，如 @Controller 和 @RequestMapping 等等
InternalResourceViewResolver 将使用定义的规则来解决视图名称。按照上述定义的规则，一个名称为 hello 的逻辑视图将发送给位于 /WEB-INF/jsp/hello.jsp 中实现的视图
```


## 定义一个控制器
DispatcherServlet 发送请求到控制器中执行特定的功能
@Controller 注释表明一个特定类是一个控制器的作用。@RequestMapping 注释用于映射 URL 到整个类或一个特定的处理方法
```java
@Controller
@RequestMapping("/hello")
public class HelloController{
   @RequestMapping(method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "hello";
   }
}

@Controller
public class HelloController{
   @RequestMapping(value = "/hello", method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "hello";
   }
}
```
```text
可以在一个 service 方法中定义需要的业务逻辑。可以根据每次需求在这个方法中调用其他方法
基于定义的业务逻辑，你将在这个方法中创建一个模型。你可以设置不同的模型属性，这些属性将被视图访问并显示最终的结果
这个示例创建了一个带有属性 “message” 的模型
一个定义的 service 方法可以返回一个包含视图名称的字符串用于呈现该模型。这个示例返回 “hello” 作为逻辑视图的名称
```
 
## 创建JSP视图
```text
对于不同的表示技术，Spring MVC 支持许多类型的视图:
    这些包括 JSP、HTML、PDF、Excel 工作表、XML、Velocity 模板、XSLT、JSON、Atom 和 RSS 提要、JasperReports 等等
    但最常使用利用 JSTL 编写的 JSP 模板
```
在 /WEB-INF/hello/hello.jsp 中编写一个简单的 hello 视图：
```xml
<html>
   <head>
   <title>Hello Spring MVC</title>
   </head>
   <body>
   <h2>${message}</h2>
   </body>
</html>
```
其中，${message} 是我们在控制器内部设置的属性。你可以在你的视图中有多个属性显示
配置完tomcat即可启动项目，测试
  
  
## Spring MVC 表单数据处理  
 @ModelAttribute注解定义可以看到,这个注解可以用在方法和参数中，将请求参数绑定到Model对象
```java
public class StudentController {
   @RequestMapping(value = "/student", method = RequestMethod.GET)
   public ModelAndView student() {
      return new ModelAndView("student", "command", new Student());
   }   
   @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
   public String addStudent(@ModelAttribute("SpringWeb")Student student, 
   ModelMap model) {
      model.addAttribute("name", student.getName());
      model.addAttribute("age", student.getAge());
      model.addAttribute("id", student.getId());      
      return "result";
   }
}
```
```text
在这里，第一个 service 方法 student()，我们已经在名称为 “command” 的 ModelAndView 对象中传递一个空的 Student 对象
因为 spring 框架需要一个名称的 “command” 的对象，如果在 JSP 文件中使用 <form:form> 标签
所以，当 student() 方法被调用时，它返回 student.jsp 视图

第二个 service 方法 addStudent() 将调用 HelloWeb/addStudent URL 中的 POST 方法
你将根据提交的信息准备好你的模型对象
最后一个 “result” 视图会从 service 方法中返回，它将导致呈现 result.jsp
```


  
  
  
  
  
  
  
  
  
  
  
  
  
  