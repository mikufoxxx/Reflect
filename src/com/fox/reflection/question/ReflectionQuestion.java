package com.fox.reflection.question;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import static java.lang.Class.forName;

public class ReflectionQuestion {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 根据配置文件 re.properties 指定信息，创建Cat对象并调用方法hi
        // 传统方式是直接new Cat(),或者读取流
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String method = properties.get("method").toString();

        //由于拿到的是字符串类型，所以不能 new classfullpath() 来创建对象, 因此需要反射

        //使用反射机制解决
        //(1) 加载类, 返回Class类型的对象cls = com.fox.Cat
        Class cls = Class.forName(classfullpath);
        //(2) 通过cls得到加载的类 com.fox.Cat 的对象实例
        Object o = cls.newInstance();
        //(3) 通过 cls 得到加载的类的 method 对象, 在反射中，万物皆对象，方法也是对象
        Method method1 = cls.getMethod(method);
        //(4) 通过 method1 调用方法，即通过方法对象调用对象
        method1.invoke(o);
    }
}
