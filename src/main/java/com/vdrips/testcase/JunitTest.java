package com.vdrips.testcase;

import junit.framework.TestCase;

/**
 * Created by baixf on 2017/3/10.
 * Junit的测试类
 */
public class JunitTest extends TestCase {

    /**
     * 运行测试函数前的初始化函数
     * @throws Exception
     */
    protected void setUp() throws Exception {
        super.setUp();
        //some initial code
        System.out.println("init");
    }

    /**
     * 运行测试函数结束后的清理函数
     * @throws Exception
     */
    protected void tearDown() throws Exception {
        super.tearDown ();
        //release resource and rollback
        System.out.println("release resource");
    }

    /**
     * 测试函数
     */
    public void testFunction() {
        //test code
        System.out.println("run function");
    }
}
