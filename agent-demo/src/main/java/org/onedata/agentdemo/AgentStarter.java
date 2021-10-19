package org.onedata.agentdemo;

import java.lang.instrument.Instrumentation;

/**
 * agent 入口类
 *
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/11 20:29
 * @description: AgentStarter
 */
public class AgentStarter {

    /**
     * 字节码增强的入口方法，方法名不能变
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("FirstAgent is Start...");
        inst.addTransformer(new FirstTransformer());
    }

}
