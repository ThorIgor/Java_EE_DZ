package library;

import org.springframework.beans.factory.InitializingBean;

public class OnMissingBeanClass implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello from OnMissingBeanClass");
    }
}
