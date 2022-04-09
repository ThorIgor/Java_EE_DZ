package library;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan
@Configuration(proxyBeanMethods = false)
public class Config {


    @Bean
    @ConditionalOnMissingBean(OnMissingBeanClass.class)
    public OnMissingBeanClass testInterfaceImpl(){return new OnMissingBeanClass();}

    @Bean
    @ConditionalOnBean(OnMissingBeanClass.class)
    public OnBeanClass onBeanClass() {return new OnBeanClass();}

    @Bean
    @ConditionalOnProperty(prefix="something", name="property1")
    public OnPropertyClass1 onPropertyClass1() {return new OnPropertyClass1();}

    @Bean
    @ConditionalOnProperty(prefix="something", name="property2")
    public OnPropertyClass2 onPropertyClass2() {return new OnPropertyClass2();}
}
