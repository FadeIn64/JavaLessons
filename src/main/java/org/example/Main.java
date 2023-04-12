package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import su.springExample.marketplace.MarketPlaceController;
import su.springExample.persons.Student;
import su.springExample.storage.Storage;
import su.springExample.streams.Convertor;
import su.springExample.trafficlight.TraficLight;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext("su");

        System.out.println(ctx.getBean("hello"));

        System.out.println(ctx.getBean("rand"));
        System.out.println(ctx.getBean("rand"));

        System.out.println(ctx.getBean("date"));
        System.out.println(ctx.getBean("date"));


        System.out.println(ctx.getBean("min"));
        System.out.println(ctx.getBean("max"));

//        for(int i = 0; i <= 10; i++){
//            System.out.println(((MyRand)ctx.getBean("myrand")).next());
//        }

        System.out.println(ctx.getBean("bestReview"));

        TraficLight traficLight = (TraficLight) ctx.getBean("traficlight");

        System.out.println(traficLight.next());
        System.out.println(traficLight.next());
        traficLight.on();
        System.out.println(traficLight.next());
        System.out.println(traficLight.next());
        System.out.println(traficLight.next());

        Convertor convertor = (Convertor) ctx.getBean("convertor");

        convertor.convert();

        MarketPlaceController market = (MarketPlaceController) ctx.getBean("market");

        market.newShare("APP", 100);
        market.newShare("APLLE", 102);
        market.newShare("CTX", 89);

        System.out.println(ctx.getBean("random"));
        System.out.println(ctx.getBean("random"));
        System.out.println(ctx.getBean("random"));

        System.out.println(ctx.getBean("A"));

        Storage storage = ctx.getBean(Storage.class);

        storage.put("rand");
        storage.put("leha");

        System.out.println(storage.get(0, Integer.class));
        System.out.println(storage.get(1, Student.class));


    }
}
