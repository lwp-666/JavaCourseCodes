package week01;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @Date: 2022/1/22
 * @description: 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 Hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件
 */
public class XlassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 如果支持包名, 则需要进行路径转换
        String resourcePath = name.replace(".", "/");
        // 文件后缀
        final String suffix = ".xlass";
        // 获取输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix);
        if (inputStream == null) {
            System.out.println("inputStream null !!");
            return null;
        }
        try {
            // 读取数据
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            // 转换
            byte[] classBytes = decode(byteArray);
            // 通知底层定义这个类
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
             throw new ClassNotFoundException(name, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 解码
    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

    public static void main(String[] args) throws Exception {
        // 相关参数
        final String className = "Hello";
        final String methodName = "hello";
        // 创建类加载器
        ClassLoader classLoader = new XlassLoader();
        // 加载相应的类
        Class<?> clazz = classLoader.loadClass(className);
        // 看看里面有些什么方法
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName() + "." + m.getName());
        }
        // 创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 调用实例方法
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);
    }

}
