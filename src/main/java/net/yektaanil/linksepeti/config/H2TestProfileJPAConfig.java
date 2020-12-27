package net.yektaanil.linksepeti.config;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// @Configuration
// @EnableJpaRepositories(basePackages = {"net.yektaanil.linksepeti.repository"})
public class H2TestProfileJPAConfig {

    // @Bean
    // @Profile("test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        return dataSource;
    }
}
