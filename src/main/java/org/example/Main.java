package org.example;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import su.springExample.marketplace.MarketPlaceController;
import su.springExample.persons.Student;
import su.springExample.persons.marks.Estimation;
import su.springExample.storage.Storage;
import su.springExample.streams.Convertor;
import su.springExample.trafficlight.TraficLight;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String CONNECTION = "jdbc:h2:C:\\Users\\1\\IdeaProjects\\JavaSpringReflection\\src\\main\\resources\\JDBC\\office";

    @SneakyThrows
    public static void main(String[] args) {

        Class.forName("org.h2.Driver");


        UUID uuid = UUID.randomUUID();

        try (Connection con = DriverManager.getConnection(CONNECTION)){
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM departments join employees on departments.id = department");

            while (rs.next()){
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) +
                        "\t" + rs.getString(3) + "\t" + rs.getString(4));
            }


        }

        Student a = new Student("a", "a", new Estimation(5), new Estimation(5));
        Student b = new Student("b", "b", new Estimation(4), new Estimation(5));
        Student c = new Student("c", "c", new Estimation(3), new Estimation(4));
        Student d = new Student("d", "d", new Estimation(4), new Estimation(5));

        var res =  Stream.of(a, b, c, d)
                .collect(Collectors.groupingBy(x->interval((int)x.getAverage()), Collectors.toList()));

        System.out.println(res);



        ApplicationContext ctx = new AnnotationConfigApplicationContext("su");

//        System.out.println("hello");
//
//        System.out.println(ctx.getBean("hello"));
//
//        System.out.println(ctx.getBean("rand"));
//        System.out.println(ctx.getBean("rand"));
//
//        System.out.println(ctx.getBean("date"));
//        System.out.println(ctx.getBean("date"));
//
//
//        System.out.println(ctx.getBean("min"));
//        System.out.println(ctx.getBean("max"));
//
////        for(int i = 0; i <= 10; i++){
////            System.out.println(((MyRand)ctx.getBean("myrand")).next());
////        }
//
//        System.out.println(ctx.getBean("bestReview"));
//
//        TraficLight traficLight = (TraficLight) ctx.getBean("traficlight");
//
//        System.out.println(traficLight.next());
//        System.out.println(traficLight.next());
//        traficLight.on();
//        System.out.println(traficLight.next());
//        System.out.println(traficLight.next());
//        System.out.println(traficLight.next());
//
//        Convertor convertor = ctx.getBean("convertor", Convertor.class);
//
//        convertor.convert();
//
//        MarketPlaceController market = (MarketPlaceController) ctx.getBean("market");
//
//        market.newShare("APP", 100);
//        market.newShare("APLLE", 102);
//        market.newShare("CTX", 89);
//
//        System.out.println("----------------------");
//        System.out.println(ctx.getBean("random"));
//        System.out.println(ctx.getBean("random"));
//        System.out.println(ctx.getBean("random"));
//        System.out.println("----------------------");
//
//        System.out.println(ctx.getBean("A"));
//
//        Storage storage = ctx.getBean(Storage.class);
//
//        storage.put("rand");
//        storage.put("leha");
//
//        System.out.println(storage.get(0, Integer.class));
//        System.out.println(storage.get(1, Student.class));


    }


    static String interval(int x){
        if(x >= 5) return "Отличники";
        if(x == 4) return "Хорошисты";
        if(x == 3) return "Троешники";
        return "Мусор";
    }
}
