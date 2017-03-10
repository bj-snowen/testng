package com.vdrips.testcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

/**
 * Created by baixf on 2017/3/10.
 */
public class GroupA {
    @Configuration(beforeTestClass=true,groups="group1")
    public void C1(){
        System.out.println("A C1");
    }

    @Test(groups="group1")
    public void T1(){
        System.out.println("A T1");
    }

    @BeforeClass(groups="group2")
    public void C2(){
        System.out.println("A C2");
    }

    @Test(groups="group2")
    public void T2(){
        System.out.println("A T2");
    }
}
