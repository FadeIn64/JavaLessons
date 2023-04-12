package su.springExample.persons.marks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Estimation implements  Mark {

    @Getter
    private int mark;

    @Override
    public double average() {
        return (double) mark;
    }
}
