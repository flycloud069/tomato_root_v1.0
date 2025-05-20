package com.root.config;//package com.zyyx.zyyxcloud.zhyy.standard.biz.util;


import com.root.mapper.RespMegTypeSup;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * desc:
 * author: Administrator
 * date: 2020/11/4
 */
@Configuration
public class DefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        scanner.setBeanNameGenerator(new AnnotationBeanNameGenerator());
        // 定义需要扫描的注解 -- 自定义注解
        scanner.addIncludeFilter(new AnnotationTypeFilter(RespMegTypeSup.class));
        // 定义扫描的包
        scanner.scan("com.zyyx.zyyxcloud.zhyy.standard.biz");
    }

    @Override

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
