package su.springExample;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import su.orm.Convertor;
import su.springExample.marketplace.MarketPlaceController;
import su.springExample.marketplace.Robot;
import su.springExample.marketplace.SimpleRobot;
import su.springExample.persons.Student;
import su.springExample.persons.marks.Estimation;
import su.springExample.trafficlight.Color;
import su.springExample.trafficlight.Colorable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

@Configuration
public class Config {


    @Bean
    String hello(){
        return "hello";
    }

    @Bean("rand")
    @Lazy
    @Scope("prototype")
    int rand(){
        return (new Random()).nextInt(0,99);
    }

    @Bean("rule")
    @Qualifier("rule")
    Predicate<Double> rule(){
        return x -> x>=2 && x<=5;
    }

    @Bean
    @Qualifier("min")
    int min(){
        return 0;
    }

    @Qualifier("max")
    @Bean
    int max(){
        return 5;
    }

    @Bean
    Review good(){
        return new Review("good", 4);
    }

    @Bean
    Review mean(){
        return new Review("mean", 3);
    }

//    @Bean("random")
//    Integer random__(){
//        return 2;
//    }

    @Bean
    Review randomReview(@Qualifier("random") MyRand mark){
        return new Review("to close", mark.next());
    }

    @Bean("bestReview")
    @Lazy
    Review bestReview(List<Review> reviews){
        if (reviews.size() == 0) return null;

        Review max = reviews.get(0);

        for (int i = 1; i < reviews.size(); i++){
            if(max.mark < reviews.get(i).mark)
                max = reviews.get(i);
        }

        return max;
    }

    @Bean("redColor")
    @Qualifier("redColor")
    @Lazy
    Colorable red(@Qualifier("greenColor") @Lazy Colorable green){
        return new Color("red", green);
    }



    @Bean("greenColor")
    @Qualifier("greenColor")
    @Lazy
    Colorable green(@Qualifier("redColor") @Lazy Colorable red){
        return new Color("green", red);
    }


    @Bean("blackColor")
    @Qualifier("blackColor")
    @Lazy
    Colorable black(@Qualifier("blackColor") @Lazy Colorable black){
        return new Color("black", black);
    }


    @Bean("leha")
    Student leha(){
        return new Student("Leha", "Simonov", new Estimation(4), new Estimation(4));
    }


    @Bean
    Robot robot1(){
        return new SimpleRobot("APLLE");
    }

    @Bean
    Robot robot2(){
        return new SimpleRobot("CTX");
    }


    @Bean("market")
    MarketPlaceController marketPlaceController(List<Robot> robots){
        return new MarketPlaceController(robots);
    }

    @Bean
    @Qualifier("connectionString")
    String connectionString(){
        return "jdbc:h2:D:\\Projects\\java\\src\\main\\resources\\JDBC\\office";
    }

    @SneakyThrows
    @Bean
    Connection connection(@Qualifier("connectionString") String connectionString){
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(connectionString);
    }

    @Bean
    Convertor<Integer> integerConvertor(){
        return Integer::parseInt;
    }

    @Bean
    Convertor<String> stringConvertor(){
        return String::valueOf;
    }

}
