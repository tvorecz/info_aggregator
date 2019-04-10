package epam.labs.dzmitry.zorych.config;

import epam.labs.dzmitry.zorych.mediator.CommonServiceMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
    public CommonServiceMediator serviceMediator;

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
