package su.springExample;

import com.sun.source.tree.DoWhileLoopTree;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import su.springExample.marketplace.MarketPlaceController;
import su.springExample.marketplace.Robot;
import su.springExample.marketplace.SimpleRobot;
import su.springExample.persons.Student;
import su.springExample.persons.StudentBuilder;
import su.springExample.persons.marks.Estimation;
import su.springExample.streams.*;
import su.springExample.trafficlight.Color;
import su.springExample.trafficlight.Colorable;

import java.io.File;
import java.util.Arrays;
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


    @Bean("convertor")
    Convertor convertor(@Qualifier("downloader") Downloader d,
                        @Qualifier("handler") Handler h,
                        @Qualifier("uploader") Uploader u){
        return new Convertor<>(d, h, u);
    }

    @Bean
    @Qualifier("downloader")
    Downloader downloader(Downloader d){
        return d;
    }

    @Bean
    @Qualifier("uploader")
    Uploader uploader(Uploader u){
        return u;
    }

    @Bean
    @Qualifier("handler")
    Handler handler(Handler h){
        return h;
    }

    @Bean
    Downloader stringDownloader(String connect){
        return new StringDownloader(connect);
    }

    @Bean
    Handler stringHandler(){
        return new StringHandler();
    }

    @Bean
    Uploader stringUploader(){
        return new StringUploader();
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

}
