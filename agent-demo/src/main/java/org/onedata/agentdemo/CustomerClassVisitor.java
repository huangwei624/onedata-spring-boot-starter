package org.onedata.agentdemo;

import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/12 11:36
 * @description: CustomerClassVisitor
 */
@Slf4j
public class CustomerClassVisitor extends ClassVisitor {
    public static final Logger logger = LoggerFactory.getLogger(CustomerClassVisitor.class);


    public CustomerClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        return new AdviceAdapter(Opcodes.ASM5, methodVisitor, access, name, desc) {

            /**
             * 进入方法时执行
             */
            @Override
            protected void onMethodEnter() {
                logger.info("onMethodEnter...");
                logger.info("{}", name);
                if (name != null && name.contains("main")) {
                    logger.info("onMethodEnter...");
                }
            }

            /**
             * 退出方法时执行
             *
             * @param opcode
             */
            @Override
            protected void onMethodExit(int opcode) {
                logger.info("onMethodExit...");
                if (name != null && name.contains("main")) {
                    logger.info("onMethodExit...");
                }
            }
        };
    }
}
