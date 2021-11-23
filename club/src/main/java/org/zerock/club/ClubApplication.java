package org.zerock.club;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@ComponentScan(basePackages={"org.zerock.club.controller"})
@SpringBootApplication
@EnableJpaAuditing
public class ClubApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClubApplication.class, args);
    }

}
