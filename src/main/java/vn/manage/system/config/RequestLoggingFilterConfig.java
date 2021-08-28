package vn.manage.system.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {

  @Bean
  public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
    CommonsRequestLoggingFilter commonsRequestLoggingFilter = new CommonsRequestLoggingFilter();

    commonsRequestLoggingFilter.setIncludeQueryString(true);
    commonsRequestLoggingFilter.setIncludePayload(true);
    commonsRequestLoggingFilter.setMaxPayloadLength(10000);
    commonsRequestLoggingFilter.setIncludeHeaders(false);
    commonsRequestLoggingFilter.setAfterMessagePrefix("\nREQUEST DATA: ");

    return commonsRequestLoggingFilter;
  }

  @Bean
  public FilterRegistrationBean<CustomUrlFilter> filterRegistrationBean() {
    FilterRegistrationBean<CustomUrlFilter> registrationBean = new FilterRegistrationBean<>();
    CustomUrlFilter customURLFilter = new CustomUrlFilter();

    registrationBean.setFilter(customURLFilter);
    registrationBean.setOrder(2);
    return registrationBean;
  }
}
