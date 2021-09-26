package spring.webflux.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/21/21
 */
@SpringBootApplication(scanBasePackages = {"spring.webflux.practice"})
public class SpringWebFluxPracticeApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringWebFluxPracticeApplication.class, args);
    }
}
