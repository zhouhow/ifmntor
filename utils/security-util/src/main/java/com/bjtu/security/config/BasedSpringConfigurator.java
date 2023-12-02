package com.bjtu.security.config;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.websocket.server.ServerEndpointConfig;
import org.springframework.context.ApplicationContextAware;
@Component
public class BasedSpringConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        BasedSpringConfigurator.applicationContext = applicationContext;
    }
    @Override
    public <T> T getEndpointInstance(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
