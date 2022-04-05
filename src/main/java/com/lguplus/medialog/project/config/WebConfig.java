package com.lguplus.medialog.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {


	
//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setMaxUploadSize(200*1024*1024);
//		resolver.setDefaultEncoding("utf-8");
//		return resolver;
//	}


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(lnbInterceptor())
//                .addPathPatterns("/view/**")
//                .excludePathPatterns("/test/**/")
//                .excludePathPatterns("/users/login");
//    }
//    
//    @Bean
//    public HandlerInterceptor lnbInterceptor() {
//    	return new LnbInterceptor();
//    }

}
