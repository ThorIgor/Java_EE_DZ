package client;

import org.springframework.beans.factory.InitializingBean;

public class OnMissingProperty1 implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello from OnMissingProperty1");
    }
}
