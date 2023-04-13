package su.springExample.beanFactoryPostProcessors;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("random")
public class RandomBeanFactory implements FactoryBean<Integer> {

    @Autowired
    ConfigurableListableBeanFactory beanFactory;

    @Override
    public Integer getObject() throws Exception {
        return (new Random()).nextInt(0, 99);
    }

    @Override
    public Class<?> getObjectType() {
        return Integer.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}

