package su.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class FieldCollector {
    public static Set<Field> getAllFields(Object obj){
        Set<Field> res = new HashSet<>(Set.of(obj.getClass().getFields()));
        res.addAll(Set.of(obj.getClass().getDeclaredFields()));
        return res;
    }
}
