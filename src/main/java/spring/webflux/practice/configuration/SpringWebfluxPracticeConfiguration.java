package spring.webflux.practice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@Configuration
public class SpringWebfluxPracticeConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
