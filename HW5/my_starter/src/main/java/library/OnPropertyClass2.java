package library;

import org.springframework.beans.factory.InitializingBean;

public class OnPropertyClass2 implements InitializingBean, OnPropertyInterface{

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello from OnPropertyClass2");
    }
}
