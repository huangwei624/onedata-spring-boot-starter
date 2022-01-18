package org;

import java.io.Serializable;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/14 16:55
 * @description: AsmHello
 */
public class AsmHello implements Cloneable, Serializable {
    public static final Integer intValue = 1;
    private static final String strVale = "hello";

    public AsmHello() {
    }

    public void hello() {
        int a = 1;
        int b = 2;
        int c = a + b;
    }
}