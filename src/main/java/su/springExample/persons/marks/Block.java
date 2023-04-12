package su.springExample.persons.marks;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
public class Block implements Mark{
    private List<Mark> marks = new ArrayList<>();

    public Block(Mark... marks){
        this.marks.addAll(List.of(marks));
    }

    @Override
    public double average() {

        double res = 0;

        for (Mark mark : marks){
            res+= mark.average();
        }

        return res/marks.size();
    }
}
