package su.springExample.beanFactoryPostProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomBeanFactoryPostProcessor implements FactoryBean<Integer> {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if(beanFactory.containsBean("random")) {
            System.out.println("yes");
            return;
        }

        beanFactory.registerSingleton("random", (new Random()).nextInt(0,99));

    }

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

