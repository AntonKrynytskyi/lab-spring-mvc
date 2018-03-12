package spittr.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import spittr.data.SpittleRepository;
import spittr.data.SpittleRepositoryImpl;

@Configuration
@EnableWebMvc
@ComponentScan(value = {"spittr.web", "spittr.data"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolve = new InternalResourceViewResolver();

        resolve.setPrefix("/WEB-INF/views/");
        resolve.setSuffix(".jsp");
        resolve.setExposeContextBeansAsAttributes(true);
        resolve.setViewClass(JstlView.class);

        return resolve;
    }

    @Bean
    public SpittleRepository spittleRepository() {
        return new SpittleRepositoryImpl();
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource =
                new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }


    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
