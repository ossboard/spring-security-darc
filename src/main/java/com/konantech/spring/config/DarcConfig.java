package com.konantech.spring.config;

import _1._0._0._127.darc_wsdl.DarcPortType;
import com.konantech.spring.darcLib.service.DarcPort;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DarcConfig {

    @Value("${darc.url}")
    private String address;

    @Bean(name = "darcPortType")
    public DarcPortType darcPortType() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(DarcPortType.class);
        jaxWsProxyFactoryBean.setAddress(address);
        return (DarcPortType) jaxWsProxyFactoryBean.create();
    }

    @Bean(name = "darcPort")
    public DarcPort darcPort() {
        return new DarcPort(darcPortType());
    }
}
