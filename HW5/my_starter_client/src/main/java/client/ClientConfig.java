package client;

import library.OnPropertyClass1;
import library.OnPropertyClass2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("library")
@Configuration
public class ClientConfig {

    @Bean
    @ConditionalOnBean(OnPropertyClass1.class)
    public OnProperty1 onProperty1() {return new OnProperty1();}

    @Bean
    @ConditionalOnMissingBean(OnPropertyClass1.class)
    public OnMissingProperty1 onMissingProperty1() {return new OnMissingProperty1();}

    @Bean
    @ConditionalOnBean(OnPropertyClass2.class)
    public OnProperty2 onProperty2() {return new OnProperty2();}

    @Bean
    @ConditionalOnMissingBean(OnPropertyClass2.class)
    public OnMissingProperty2 onMissingProperty2() {return new OnMissingProperty2();}

}
