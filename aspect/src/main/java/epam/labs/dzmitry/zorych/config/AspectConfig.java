package epam.labs.dzmitry.zorych.config;

import epam.labs.dzmitry.zorych.aspect.AspectLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy()
public class AspectConfig {
    @Bean
    public AspectLogger aspectLogger() {
        return new AspectLogger();
    }
}
