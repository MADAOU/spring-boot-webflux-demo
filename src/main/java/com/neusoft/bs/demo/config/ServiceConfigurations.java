package com.neusoft.bs.demo.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.neusoft.bs.demo.model.User;
import com.neusoft.bs.demo.services.RxUserService;
import com.neusoft.bs.demo.services.UserService;
import com.neusoft.bs.demo.services.impl.RemoteUserServiceImpl;
import com.neusoft.bs.demo.services.impl.RxRemoteUserServiceImpl;
import com.neusoft.bs.demo.services.impl.RxUserServiceImpl;
import com.neusoft.bs.demo.services.impl.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfigurations {

    private static final Logger log = LoggerFactory.getLogger(ServiceConfigurations.class);

    public static final String REMOTE_ENABLE = "1";

    private final Map<String, User> data = new ConcurrentHashMap<>();

    @Value("${services.user.remote.enable}")
    private String remoteEnable;

    @Value("${services.user.remote.url}")
    private String remoteUserServiceURL;

    @Bean
    public UserService userService(ApplicationContext context) {
        log.debug("services.user.remote.enable: {}", remoteEnable);
        log.debug("services.user.remote.url: {}", remoteUserServiceURL);

        if(REMOTE_ENABLE.equals(remoteEnable)) {
            log.debug("remote user service use.");
            return context.getBean("remoteUserService", UserService.class);
        } else {
            log.debug("local user service use.");
            return context.getBean("localUserService", UserService.class);
        }
    }

    @Bean
    public RxUserService rxUserService(ApplicationContext context) {
        if(REMOTE_ENABLE.equals(remoteEnable)) {
            log.debug("remote reactive user service use.");
            return context.getBean("remoteRxUserService", RxUserService.class);
        } else {
            log.debug("local reactive user service use.");
            return context.getBean("localRxUserService", RxUserService.class);
        }
    }

    @Bean
    public UserService localUserService(Map<String, User> dataStore) {
        return new UserServiceImpl(data);
    }

    @Bean
    public RxUserService localRxUserService(Map<String, User> dataStore) {
        return new RxUserServiceImpl(dataStore);
    }

    @Bean
    public UserService remoteUserService() {
        return new RemoteUserServiceImpl(remoteUserServiceURL);
    }

    @Bean
    public RxUserService remoteRxUserService() {
        return new RxRemoteUserServiceImpl(remoteUserServiceURL);
    }

    @Bean
    public Map<String, User> dataStore() {
        return data;
    }

}