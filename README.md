### junit 和testng 差异

* TestNG是从Junit发展而来的
* Junit 测试类需继承 TestCase,前后置函数setUp tearDown无参数。执行顺序：Setup( )   test1( )    tearDown( )      Setup( )     test2( )      tearDown( )…………
* TestNG 没有继承任何类，JDK Annotations 使用注解方式，函数名字随意取
* 灵活的test configuration 配置文件（testng.xml） 分组 参数 依赖关系
* Junit是不带任何参数的，不论是测试方法还是配置方法，而TestNG都是可以添加参数的
* TestNG 执行顺序：beforeSuite afterSuite beforeTest afterTest beforeTestClass afterTestClass beforeTestMethod afterTestMethod



***
0. testng.xml
pom.xml中配置 依赖包：testng ，编译插件plugin：maven-surefire-plugin

1. 命令执行测试：mvn clean test -DtestEnv=%testEnv% -DxmlFileName=%xmlFileName%

    执行这条命令时，我们把testEvn及xmlFileName两个参数的值传入了pom.xml文件，接着，Maven在调用TestNG时又会把参数传过去。经过如上四个步骤，我们可以通过参数传递，灵活的指定测试环境、测试范围等，而不需要对测试脚本做任何改动。

    这边要讲下xmlFileName这个参数。因为考虑到要执行不同的用例集，比如只针对某一模块进行自动化测试，或对项目所有的功能进行全面回归，我们可以创建不同的TestNG XML文件，然后在执行mvn命令时指定你想要跑的那个XML文件。该方法适用于所有使用Jenkins + Maven + TestNG的测试场景。

### 使用Parameter参数

```
@Parameters({ "first-name" })
@Test
public void testSingleString(String firstName) {
System.out.println("Invoked testString " + firstName);
assert "Cedric".equals(firstName);
}
参数的值放到配置文件中
<suite name="My suite">
<parameter name="first-name"  value="Cedric"/>
<test name="Simple example">
```

这种方法偶尔用之还可以，但是很遗憾的是第一只能传String（可能可以其他的基本数据类型？？但至少不能传复杂对象），第二数据写在配置文件中，不能所见即所得。


### 依赖关系
先给个例子

@Test
public void serverStartedOk() {}

@Test(dependsOnMethods = { "serverStartedOk" })
public void method1() {}

在这个例子中，method1（）必须在serverStartedOk()执行后才能执行，而且serverStartedOk()不能fail，否则method1（）会被skip掉
基本上类似于ant的依赖关系，也很容易理解，只是分为强依靠和弱依靠，区别是弱依靠只管执行的顺序，强依靠除了顺序，还要正确，否则后面的不执行，上面的例子是强依靠，下面是弱依靠,加上alwaysrun=”true”
@Test
public void serverStartedOk() {}
@Test(dependsOnMethods = { "serverStartedOk" }，alwaysrun=”true”)
public void method1() {}

### 其他特性
   还有很多，工厂模式，并行运行（Parallel running ），BeanShell 等等

***


<!--下面参数listener parallel threadCount testFailureIgnore等可以配置在对应testng.xml中-->
<argLine>-Xmx1024m -XX:PermSize=1024m -XX:MaxPermSize=1024m</argLine>
<!--<additionalClasspathElements>-->
<!--<additionalClasspathElement>-->
<!--${basedir}/target/test-classes-->
<!--</additionalClasspathElement>-->
<!--</additionalClasspathElements>-->
<suiteXmlFiles>
    <suiteXmlFile>src/test/resources/testng.xml</suiteXmlFile>
</suiteXmlFiles>
<includes>
    <include>**/*Test.java</include>
</includes>
<!--跳过测试-->
<skipTests>false</skipTests>
<!--<excludes>-->
<!--<exclude>**/TestConstants.java</exclude>-->
<!--</excludes>-->
<!--<forkMode>pertest</forkMode>-->
<!--多线程-->
<parallel>classes</parallel>
<threadCount>10</threadCount>
<testFailureIgnore>true</testFailureIgnore>
<properties>
    监听器
    <property>
        <name>usedefaultlisteners</name>
        <value>false</value>
    </property>
    <!--<property>-->
    <!--<name>listener</name>-->
    <!--<value>org.uncommons.reportng.</value>-->
    <!--</property>-->
</properties>
```
