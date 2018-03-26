package com.konantech.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mam")
@Data
public class MamProfiles {


    private MamEx mamex = new MamEx();
    private Session session = new Session();
    private Insert insert = new Insert();

    @Data
    public class MamEx {
        String ssologin;
        String oun_ex_getworkflowlist;
    }

    @Data
    public class Session {
        String search;
    }

    @Data
    public class Insert {
        String userInfo;
    }

}