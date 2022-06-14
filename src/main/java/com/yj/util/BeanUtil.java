package com.yj.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * bean工具
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2022-05-06 15:47:01
 */
@Component
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public BeanUtil() {
    }

    public static <T> T getBean(Class<T> beanClass) throws BeansException {
        return applicationContext != null ? applicationContext.getBean(beanClass) : null;
    }

    public static <T> T getBean(String beanName, Class<T> beanClass) throws BeansException {
        return applicationContext != null ? applicationContext.getBean(beanName, beanClass) : null;
    }

    public static Map<String, Object> getBeansWithAnnotation(Class annotationClass) {
        return applicationContext.getBeansWithAnnotation(annotationClass);
    }

    /**
     * 将source的数据拷贝到target中为null的字段中
     *
     * @param source
     * @param target
     * @return
     * @author 邹敦宇
     * @date 2019-05-21 20:26:59
     **/
    public static void copyNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNoNullProperties(target));
    }

    /**
     * 将目标源中不为空的字段取出
     *
     * @param target
     * @return java.lang.String[]
     * @author 邹敦宇
     * @date 2019-05-21 20:27:30
     **/
    private static String[] getNoNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> noEmptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object value = srcBean.getPropertyValue(p.getName());
            if (value != null) {
                noEmptyName.add(p.getName());
            }
        }
        return noEmptyName.toArray(new String[0]);
    }

    /**
     * 主动向Spring容器中注册bean
     *
     * @param name     BeanName
     * @param clazz    注册的bean的类性
     * @param supplier 提供创建好的对象
     * @return 返回注册到容器中的bean对象
     * @author 邹敦宇
     * @date 2019-12-31 10:01:54
     **/
    public static <T> T registerBean(String name, Class<T> clazz, Supplier<T> supplier) {
        ConfigurableApplicationContext configurableApplicationContext =
                (ConfigurableApplicationContext) applicationContext;
        if (configurableApplicationContext.containsBean(name)) {
            Object bean = configurableApplicationContext.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz)) {
                return (T) bean;
            } else {
                throw new RuntimeException("BeanName 重复 " + name);
            }
        }

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz, supplier);
        BeanDefinition beanDefinition = builder.getRawBeanDefinition();

        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 主动向Spring容器中删除bean
     *
     * @param name BeanName
     * @return 返回注册到容器中的bean对象
     * @author 邹敦宇
     * @date 2019-12-31 10:01:54
     **/
    public static <T> void removeBean(String name) {
        ConfigurableApplicationContext configurableApplicationContext =
                (ConfigurableApplicationContext) applicationContext;
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();
        beanFactory.removeBeanDefinition(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtil.applicationContext = applicationContext;
    }
}
