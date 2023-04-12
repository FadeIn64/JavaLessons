package su.anotations;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;

public class DefaultHandler {

    private static Map<Class<?>, Object> map;


    static {
        Start();
    }

    @SneakyThrows
    private static void Start(){
        map = new HashMap<>();
        Class<?> clConfig = DefaultConfig.class;
        Object config = clConfig.newInstance();
        List<Field> fields = new ArrayList<>(List.of(clConfig.getDeclaredFields()));

        for(Field f : fields){

        }
    }

    @SneakyThrows
    public static void reset(Object o){
        Set<Field> fields = new HashSet<>();
        fields.addAll(Set.of(o.getClass().getFields()));
        fields.addAll(Set.of(o.getClass().getDeclaredFields()));

//        if(!o.getClass().isAnnotationPresent(Default.class))
//            fields.stream().filter(x -> x.isAnnotationPresent(Default.class));

        for(Field field : fields){
            if(field.getType().isPrimitive()) continue;
            if(!o.getClass().isAnnotationPresent(Default.class) && !field.isAnnotationPresent(Default.class))
                continue;
            field.setAccessible(true);
            field.set(o, map.get(field.getType()));
            field.setAccessible(false);
        }
    }
}
