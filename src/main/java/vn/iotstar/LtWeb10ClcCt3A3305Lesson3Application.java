package vn.iotstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LtWeb10ClcCt3A3305Lesson3Application {

	public static void main(String[] args) {
		SpringApplication.run(LtWeb10ClcCt3A3305Lesson3Application.class, args);
	}

//	@Bean
//	public FilterRegistrationBean<CustomSitemeshFilter> sitemeshFilter() {
//		FilterRegistrationBean<CustomSitemeshFilter> filterRegistrationBean = new FilterRegistrationBean<CustomSitemeshFilter>();
//		filterRegistrationBean.setFilter(new CustomSitemeshFilter());
//		filterRegistrationBean.addUrlPatterns("/*");
//		return filterRegistrationBean;
//	}
}
