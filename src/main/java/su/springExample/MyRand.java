package su.springExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component("myrand")
@Lazy
@Qualifier("random")
public class MyRand {

    private List<Integer> cash = new LinkedList<>();

    int min;
    int max;

    @Autowired
    public MyRand(@Qualifier("min") int min, @Qualifier("max") int max){
        this.min = min;
        this.max = max;

        for(int i = min; i <=max; i++){
            cash.add(i);
        }
    }

    public int next(){
        int i = (new Random()).nextInt(0, cash.size());
        int res = cash.get(i);
        cash.remove(i);

        if (cash.size() == 0)
            for(int j = min; j <=max; j++){
                cash.add(j);
            }

        return res;
    }
}
