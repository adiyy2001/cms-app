package com.Castro.cmsApp;

import com.Castro.cmsApp.security.SpringSecurityAuditorAware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CmsAppApplication {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

    public static void main(String[] args) {
        SpringApplication.run(CmsAppApplication.class, args);
    }

}
