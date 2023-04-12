package su.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MethodCollector {
    public static Set<Method> getAllMethods(Object obj){
        Set<Method> res = new HashSet<>(Set.of(obj.getClass().getMethods()));
        res.addAll(Set.of(obj.getClass().getDeclaredMethods()));
        return res;
    }
}
