package com.springboot.girl.design.decorator;

/**
 * @Description 装饰模式
 * JDK中的应用：java.io.* 包中的具体体现
 * InputStreamReader(FileInputSteam(File))
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class TestDecorator {

    public static void main(String[] args) {
        Person person = new Person();
        TakeShirt shirt = new TakeShirt();
        TakeShoes shoes = new TakeShoes();
        TakeTrousers trousers = new TakeTrousers();

        shoes.decorate(trousers.decorate(shirt.decorate(person)));
        shoes.show();
    }

}
