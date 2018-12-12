package by.intexsoft.importexport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Class configure project
 */
@Configuration
@ComponentScan("by.intexsoft.importexport")
public class ProjectConfig {

    /**
     * Create a {@link PropertySourcesPlaceholderConfigurer}
     *
     * @return {@link PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
