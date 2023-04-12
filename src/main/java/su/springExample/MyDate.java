package su.springExample;

import lombok.ToString;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

@ToString
@Component("date")
@Lazy
@Scope("prototype")
public class MyDate {
    Date date;
    private static Date lastDate;

    static {
        lastDate = new Date();
    }

    private MyDate(Date date){
        this.date = date;
        lastDate = date;
    }

    public MyDate(){
        this(new Date(System.nanoTime()));
    }
}
