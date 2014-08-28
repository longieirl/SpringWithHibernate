package com.opserver.simpleapp.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ImportResource({"classpath:applicationContext.xml"})
public class PersistenceXmlConfig {

    public PersistenceXmlConfig() {
        super();
    }
}
