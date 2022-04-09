package client;

import org.springframework.beans.factory.InitializingBean;

public class OnMissingProperty2 implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello from OnMissingProperty2");
    }
}
