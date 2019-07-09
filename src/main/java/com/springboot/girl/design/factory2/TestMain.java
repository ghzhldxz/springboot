package com.springboot.girl.design.factory2;

/**
 * @Description 工厂方法模式，在需要生产不同产品时，只需要改实例化工厂那一行的代码
 * 其实跟简单工厂模式的区别在于，更大程度上满足了开放-封闭原则的同时，还保持了封装对象的优点。
 * 但还是有个缺点：就是仍然需要知道创建哪种子工厂？那有还有更优秀的模式吗？----请看抽象工厂模式
 * @Author GuanHuizhen
 * @Date 2019/6/27
 */
public class TestMain {
    public static void main(String[] args) {
        IFactory factory = new ProductAFactory();
        factory.getProduct();
    }
}
