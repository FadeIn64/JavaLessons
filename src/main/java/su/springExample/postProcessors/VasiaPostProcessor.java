package su.springExample.postProcessors;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import su.reflection.FieldCollector;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VasiaPostProcessor implements BeanPostProcessor {
    Map<String, Object> originals = new HashMap<>();
    public static int i = 0;
    public static int j = 0;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Set<Field> fields = FieldCollector.getAllFields(bean);

        for (Field field : fields) {
            if (field.getName().equals("name") && field.getType() == String.class){
                if(originals.containsKey(beanName)) break;
                originals.put(beanName, bean);
                break;
            }
        }
        System.out.println("Before "+(++i) + "\t" + beanName);

        return bean;
    }

    @SneakyThrows
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After "+(++j) + "\t" + beanName);
        if(!originals.containsKey(beanName)) return bean;
        Object obj = originals.get(beanName);
        Field name = obj.getClass().getDeclaredField("name");
        name.setAccessible(true);
        if (name.get(obj) == null)
            name.set(obj, "Vasia");


        return bean;
    }
}
