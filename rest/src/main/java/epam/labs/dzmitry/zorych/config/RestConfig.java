package epam.labs.dzmitry.zorych.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "epam.labs.dzmitry.zorych.controller")
public class RestConfig implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/WEB-INF/").addResourceLocations("/WEB-INF/");
//    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
//        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        return dispatcherServlet;
//    }
//
//    @Bean
//    public ContextLoaderListener contextLoaderListener() {
//        return new ContextLoaderListener();
//    }
}
