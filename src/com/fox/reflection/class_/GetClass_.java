package com.fox.reflection.class_;

import com.fox.Cat;

import java.lang.reflect.Constructor;

public class GetClass_ {
    public static void main() throws ClassNotFoundException {
        //1. Class.forName
        String classAllPath = "com.fox.Cat";
        Class cls1 = Class.forName(classAllPath);

        //2. 类名.class 一般用于参数传递
        Class cls2 = Cat.class;

        //3. 对象.getClass() 一般用于有对象实例的情况下
        Cat cat = new Cat();
        Class cls3 = cat.getClass();

        //4. 通过类加载器【四种】来获取到类的Class对象
        //（1） 先得到类加载器
        ClassLoader classLoader = cat.getClass().getClassLoader();
        //（2）通过类加载器得到Class对象
        Class<?> cls4 = classLoader.loadClass(classAllPath);

        //5. 基本数据类型按如下方式得到Class类对象
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;

        //6. 基本数据类型对应的包装类，可以通过.TYPE 得到Class类对象
        Class<Integer> type = Integer.TYPE;


        Constructor constructor = cls1.getDeclaredConstructor(String.class);
    }
}
