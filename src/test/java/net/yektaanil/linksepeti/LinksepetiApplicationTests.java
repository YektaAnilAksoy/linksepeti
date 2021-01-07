package net.yektaanil.linksepeti;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import net.yektaanil.linksepeti.config.ModelMapperConfig;

@ContextConfiguration(classes = ModelMapperConfig.class)
@SpringBootTest
@ActiveProfiles("test")
class LinksepetiApplicationTests {

    @Test
    void contextLoads() {}

}
