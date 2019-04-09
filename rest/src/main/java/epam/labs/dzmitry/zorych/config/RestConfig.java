package epam.labs.dzmitry.zorych.config;

import epam.labs.dzmitry.zorych.mediator.CommonMediator;
import epam.labs.dzmitry.zorych.mediator.impl.ServiceMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@Import({ServiceConfig.class})
@ComponentScan(basePackages = "epam.labs.dzmitry.zorych.controller")
public class RestConfig implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/WEB-INF/").addResourceLocations("/WEB-INF/");
//    }


    @Autowired
    public CommonMediator serviceMediator;

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
