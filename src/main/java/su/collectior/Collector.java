package su.collectior;

import su.anotations.Invoke;
import su.reflection.MethodCollector;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class Collector {

    public static Map<String, Object> collect(Class<?>... cls){
        try {
            Object[] obj = new Object[cls.length];
            for (int i = 0; i < cls.length; i++){
                obj[i] = cls[i].newInstance();
            }
            return collect(obj);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> collect(Object... obj){
        Map<String,Object> res = new HashMap<>();
            for (Object o : obj){
                Set<Method> ms = MethodCollector.getAllMethods(o);
                for(Method m : ms){
                    m.setAccessible(true);
                    try {
                        if (m.isAnnotationPresent(Invoke.class)){
                            res.put(m.getName(), m.invoke(o));
                            Parameter[] parameters = m.getParameters();
                            Object[] args = new Object[parameters.length];
                        }
                    }
                    catch (Exception e){
                        throw new RuntimeException();
                    }
                }
            }
        return res;
    }

}
