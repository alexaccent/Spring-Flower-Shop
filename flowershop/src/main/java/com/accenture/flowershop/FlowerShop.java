package com.accenture.flowershop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.accenture.flowershop.backend.business.Flower;

public class FlowerShop {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        Flower flower = context.getBean("WhiteRose", Flower.class);

        System.out.println(flower.getName());

        context.close();
    }
}
