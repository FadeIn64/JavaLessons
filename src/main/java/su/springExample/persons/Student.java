package su.springExample.persons;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import su.springExample.persons.marks.Mark;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@ToString
public class Student {

    final String name;
    final String surname;
    private List<Mark> marks = new ArrayList<>();
    @Autowired
    Predicate<Double> rule;

    public Student(String name, String surname, Predicate<Double> rule, Mark... marks){
        this.name = name;
        this.surname = surname;
        this.rule = rule;
        this.addMark(marks);
    }

    @Autowired
    public Student(String name, String surname, Mark... marks){
        this(name, surname, x -> true, marks);
    }


    @PostConstruct
    private void init(){
        for(Mark mark : marks)
            if(!rule.test(mark.average()))
                throw new IllegalArgumentException("Mark not correct");
    }


    void addMark(Mark... marks){
        for(Mark mark : marks){
            if (!rule.test(mark.average()))
                throw new IllegalArgumentException("Mark not correct");
            this.marks.add(mark);
        }
    }

}
