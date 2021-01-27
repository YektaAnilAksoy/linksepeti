package net.yektaanil.linksepeti.config;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashidsConfig {

  @Value("${hashids.salt}")
  private String salt;

  @Bean(name = "hashids")
  public Hashids hashids() {
    return new Hashids(salt);
  }
}
