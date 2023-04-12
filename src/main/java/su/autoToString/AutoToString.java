package su.autoToString;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface AutoToString{

    default String getString() throws IllegalAccessException{
        Class<?> cl = this.getClass();
        String res = cl.getName()+ "{";

        Set<Field> fieldSet = new HashSet<>(List.of(cl.getDeclaredFields()));
        fieldSet.addAll(List.of(cl.getFields()));

        for(Field f : fieldSet){
            f.setAccessible(true);
            res += f.getName() + ": " + f.get(this) + "; ";
            f.setAccessible(false);
        }

        res += "};";
        return res;
    }

}
