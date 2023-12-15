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
    - getField不能得到私有属性
- java.lang.reflect.Constructor : 代表类的构造方法

## 获取Class对象的六种方式

```Java
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
}
```



## 通过反射创建对象

- 方式1：调用类中的public修饰的无参构造器

  ```java
    ConObject o = cls1.newInstance();
    ```

- 方式2：调用类中的指定构造器

    - 通过public的有参构造器创建实例

      ```Java
      Constructor constructor = cls1.getConstructor(String.class);
      ConObject fox = constructor.newInstance("fox");
      ```

    - 通过非public的有参构造器创建实例

      ```Java
      Constructor cons = cls1.getDeclaredConstructor(String.class);
      ConObject fox2 = cons.newInstance("fox2");
      ```