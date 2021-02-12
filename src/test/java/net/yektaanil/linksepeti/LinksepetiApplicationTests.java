package net.yektaanil.linksepeti;

import net.yektaanil.linksepeti.config.ModelMapperConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ModelMapperConfig.class)
@SpringBootTest
@ActiveProfiles("test")
class LinksepetiApplicationTests {

    @Test
    void contextLoads() {
    }
}
