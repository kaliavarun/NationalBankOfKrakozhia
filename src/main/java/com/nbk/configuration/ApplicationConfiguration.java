package com.nbk.configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
  @Bean
  public Module datatypeHibernateModule() {
    var module = new Hibernate5Module();
    module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
    return module;
  }
}
