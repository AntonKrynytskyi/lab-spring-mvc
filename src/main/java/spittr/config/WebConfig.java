package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
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

        return resolve;
    }

    @Bean
    public SpittleRepository spittleRepository() {
        return new SpittleRepositoryImpl();
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
