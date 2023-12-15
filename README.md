# 反射

## 先导问题

根据配置文件 $re.properties$ 指定信息，创建Cat对象并调用方法hi

```properties
classfullpath = com.fox.Cat
method = hi
```

通常做法如下

```java
public static void main(String[] args) throws IOException {
        // 根据配置文件 re.properties 指定信息，创建Cat对象并调用方法hi
        // 传统方式是直接new Cat(),或者读取流
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        Object classfullpath = properties.get("classfullpath").toString();
        String method = properties.get("method").toString();

        //由于拿到的是字符串类型，所以不能 new classfullpath() 来创建对象, 因此需要反射
        }
```

## 反射机制

1. 反射机制允许程序在执行期借助于ReflectionAPI取得任何类的内部信息(比如成员变量，构造器，成员方法等等)，并能操作对象的属性及方法。反射在设计模式和框架底层都会用到
2. 加载完类之后，在堆中就产生了一个Class类型的对象 (一个类只有一个Class对象)，这个对象包含了类的完整结构信息。通过这个对象得到类的结构。这个对象就像一面镜子，透过这个镜子看到类的结构，所以，形象的称之为反射

![](https://s3.bmp.ovh/imgs/2023/12/15/a14ce4511294a1ad.png)

## 反射相关的类

- java.lang.Class : 代表一个类
- java.lang.reflect.Method : 代表类的方法
- java.lang.reflect.Field : 代表类的成员变量
- java.lang.reflect.Constructor : 代表类的构造方法