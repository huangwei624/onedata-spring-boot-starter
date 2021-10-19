package org.onedata.agentdemo;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/11 20:31
 * @description: FirstTransformer
 */
public class FirstTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {

        //第一步：读取类的字节码流
        ClassReader reader = new ClassReader(classfileBuffer);
        //第二步：创建操作字节流值对象，ClassWriter.COMPUTE_MAXS:表示自动计算栈大小
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
        //第三步：接受一个ClassVisitor子类进行字节码修改
        reader.accept(new CustomerClassVisitor(Opcodes.ASM5, writer), ClassReader.EXPAND_FRAMES);
        //第四步：返回修改后的字节码流
        return writer.toByteArray();
    }
}
