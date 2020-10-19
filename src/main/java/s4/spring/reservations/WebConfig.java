package s4.spring.reservations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	
	   @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {

	       registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/assets/js/");
	       registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/assets/css/");
           registry.addResourceHandler("/user-photos/**").addResourceLocations("file:user-photos/");
       }    
	    

}
