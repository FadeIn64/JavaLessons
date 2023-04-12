package su.springExample.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import su.springExample.persons.marks.Mark;

import java.util.function.Predicate;

@Component("studentBuilder")
@Lazy
public class StudentBuilder {

    private Predicate<Double> rule;

    @Autowired
    private void setRule(@Qualifier("rule") Predicate<Double> rule){
        this.rule = rule;
    }

    public Student build(String name, String surname, Mark... marks){
        return new Student(name, surname, rule, marks);
    }
}
