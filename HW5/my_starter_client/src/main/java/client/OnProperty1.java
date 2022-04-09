package client;

import org.springframework.beans.factory.InitializingBean;

public class OnProperty1 implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello from OnProperty1");
    }
}
