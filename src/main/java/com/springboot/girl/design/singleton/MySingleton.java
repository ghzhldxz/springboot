package com.springboot.girl.design.singleton;

/**
 * @Description 用静态内部类实现单例且保证线程安全
 * 小结
 * 1. 静态属性调用会触发 静态属性和静态代码块的初始化，但并不会实例化对象
 *     A a = Father.a;
 *     B b = Son.b;  先初始化父类的静态属性、静态代码块
 * 2.
 * @Author GuanHuizhen
 * @Date 2019/7/10
 */
public class MySingleton {

    public static void main(String[] args) {
       // A a = Father.a;
       // B b = Son.b;
       // SingletonObject.getInstance();
        //静态内部类实例化过程：1.会先加载外部类的静态属性和静态代码块，2.然后调用外部类的构造方法，3.接着再初始化静态内部类的静态属性和静态代码块，4.最后再调用内部类的构造方法
        //SingletonObject.InnerObject i = new SingletonObject.InnerObject();
        //访问静态内部类的静态属性，只会执行前三步 1.会先加载外部类的静态属性和静态代码块，2.然后调用外部类的构造方法，3.接着再初始化静态内部类的静态属性和静态代码块
        //SingletonObject s = SingletonObject.InnerObject.singletonObject;

        String a = String.valueOf(getString());
    }

    public static <T> T getString () {
        return (T) "ABC";
    }

}

class SingletonObject {
    static {
        System.out.println("SingletonObject static block init~~");
    }

    {
        System.out.println("SingletonObject block init~~");
    }

    private SingletonObject() {
        System.out.println("SingletonObject constructor......");
    }

    public static void testInit() {
        System.out.println("SingletonObject testInit~~~~~~~");
    }

    public static SingletonObject getInstance() {
        return InnerObject.singletonObject;
    }

    static class InnerObject {
        InnerObject() {
            System.out.println("InnerObject constructor......");
        }

        static SingletonObject singletonObject = new SingletonObject();

        static {
            System.out.println("InnerObject~~~~static blocked");
        }

        {
            System.out.println("InnerObject~~~~ blocked");
        }
    }

}

class Father {
    public static A a = new A();
    static {
        System.out.println("father static blocked......");
    }
    public Father() {
        System.out.println("Father constructor.....");
    }
}

class Son extends Father {
    public static B b = new B();
    static {
        System.out.println("Son static blocked......");
    }
    public Son() {
        System.out.println("Son constructor.....");
    }
}

class A {
    public A() {
        System.out.println("A constructor.....");
    }
}

class B {
    public B() {
        System.out.println("B constructor.....");
    }
}
