package su.springExample.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Storage {

    private final static List<String> storage = new ArrayList<>();

    @Autowired
    private ApplicationContext ctx;

    public void put(String string){
        storage.add(string);
    }

    public <T> Object get(int index, Class<T> type){
       return null;
    }
}
