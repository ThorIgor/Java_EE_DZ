package library;

import org.springframework.beans.factory.InitializingBean;

public class OnBeanClass implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello from OnBeanClass");
    }
}
