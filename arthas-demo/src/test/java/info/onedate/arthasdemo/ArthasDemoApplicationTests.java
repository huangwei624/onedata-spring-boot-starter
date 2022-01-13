package info.onedate.arthasdemo;

import com.sun.java.accessibility.AccessBridge;
import info.onedate.arthasdemo.bean.LoggerPrint;
import org.junit.Test;

public class ArthasDemoApplicationTests {

    @Test
    public void contextLoads() {

//        ClassLoader classLoader = AccessBridge.class.getClassLoader();
        ClassLoader classLoader = LoggerPrint.class.getClassLoader();
        if (classLoader == null) {
            return;
        }

        System.out.println(classLoader);
        ClassLoader parentClassLoader = classLoader.getParent();

        if (parentClassLoader == null) {
            return;
        }
        System.out.println(parentClassLoader);

        ClassLoader parent = parentClassLoader.getParent();
        if (parent == null) {
            return;
        }
        System.out.println(parent);

    }

}
