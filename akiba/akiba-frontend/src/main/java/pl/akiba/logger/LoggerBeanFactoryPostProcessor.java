/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.akiba.logger;

import java.lang.reflect.Field;
import java.util.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = beanFactory.getBean(name);
            if (bean.getClass().isAnnotationPresent(Loggable.class)) {
                try {
                    Field field = bean.getClass().getDeclaredField("logger");
                    field.setAccessible(true);
                    field.set(bean, Logger.getLogger(bean.getClass().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}