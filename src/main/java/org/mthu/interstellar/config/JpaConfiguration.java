package org.mthu.interstellar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages= {"org.mthu.interstellar.repository"})
public class JpaConfiguration {

}
