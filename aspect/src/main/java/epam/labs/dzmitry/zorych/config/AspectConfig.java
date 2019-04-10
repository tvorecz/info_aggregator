package epam.labs.dzmitry.zorych.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy()
@ComponentScan(basePackages = "epam.labs.dzmitry.zorych.aspect")
public class AspectConfig {
//    @Bean
//    public AspectLogger aspectLogger() {
//        return new AspectLogger();
//    }
}
