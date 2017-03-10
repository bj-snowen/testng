package com.vdrips.testcase;


import org.testng.annotations.*;
import org.w3c.dom.ranges.RangeException;

/**
 * 测试类A
 */
public class TestA {

    @Parameters({"browser", "testEnv"})
    @BeforeClass
    public void beforeClass(String browser, String testEnv) {
        System.out.println("A beforeClass browser=" + browser + " env=" + testEnv);
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("A1_before_method");
    }

    @Test(priority = 1, testName = "A1", description = "A类测试方法1")
    public void test1() {
        System.out.println("A test1 method");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("A1_after_method");
    }

    // This method will provide data to any test method that declares that its Data Provider
    // is named "provide_data"
    @DataProvider(name = "provide_data")
    public Object[][] createData() {
        return new Object[][]{
                new Object[]{"Cedric", new Integer(36)},
                new Object[]{"Anne", new Integer(37)},
        };
    }

    // This test method declares that its data should be supplied by the Data Provider
    // named "provide_data"
    @Test(dataProvider = "provide_data",testName = "A provide_data", description = "A类测试方法 数据提供器")
    public void verifyData(String n1, Integer n2) {
        System.out.println("A provide_data verifyData" + n1 + " " + n2);
    }

    @AfterClass
    public void afterClass() {
        System.out.println("A afterClass");
    }


//    依赖关系

    @Test(testName = "dependsMethod", description = "A类测试方法 parentMethod/skipMethod的依赖函数")
    public void dependsMethod() throws Exception {
        System.out.println("A dependsMethod");
        throw new Exception("dependsMethod Exception");
    }


    @Test(dependsOnMethods = { "dependsMethod" }, testName = "parentMethod", description = "A类测试方法parentMethod")
    public void method() {
        System.out.println("A dependsMethod ok method");
    }

    @Test(dependsOnMethods = { "dependsMethod" }, testName = "skipMethod", description = "A类测试方法skipMethod",alwaysRun = true)
    public void skipMethod() {
        System.out.println("A dependsMethod ok method");
    }

}
