package com.ireslab.emc;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.service.CreateAcountService;
import com.ireslab.emc.serviceImpl.CreateAccountServiceImpl;

@Configuration
public class ApplicationBeanConfiguration {

	private static final String MESSAGES_PROPERTIES_FILE = "classpath:messages_de";

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {

		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename(MESSAGES_PROPERTIES_FILE);
		reloadableResourceBundleMessageSource.setCacheSeconds(200);
		reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
		return reloadableResourceBundleMessageSource;
	}

	@Bean(name = "objectWriter")
	public ObjectWriter getObjectWriter() {
		return new ObjectMapper().writerWithDefaultPrettyPrinter();
	}

	/**
	 * @return
	 */
	@Bean
	public RestTemplate getRestTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(Collections.singletonList(new CustomHttpRequestInterceptor()));
		return restTemplate;
	}

	/**
	 * @author User
	 *
	 */
	class CustomHttpRequestInterceptor implements ClientHttpRequestInterceptor {

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
				throws IOException {
			request.getHeaders().add("User-Agent", "Mozilla/5.0");
			return execution.execute(request, body);
		}

	}

	public static void main(String[] args) {

	}

	@Bean
	public CreateAcountService getCreateAccountService() {
		return new CreateAccountServiceImpl();
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	//
	
	/*@Bean
	public EmailService getEmailService() {
		return new EmailServiceImpl();
	}*/

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("noreply.electra@gmail.com");
		mailSender.setPassword("IresLab$12345");
		//mailSender.setUsername(username);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.user", "SANTOSH");

		return mailSender;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				/* registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST",
				 "OPTIONS")
				.allowedHeaders("Access-Control-Allow-Headers",
				 "Origin, X-Requested-With, Content-Type, Accept,userId,companyId")
				 .exposedHeaders("header1", "header2")
				 .allowCredentials(false).maxAge(3600);*/

				registry.addMapping("/**");

			}
		};
	}
	
	/*@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludeClientInfo(true);
	    loggingFilter.setIncludeQueryString(true);
	    loggingFilter.setIncludePayload(true);
	    return loggingFilter;
	}
*/
}
