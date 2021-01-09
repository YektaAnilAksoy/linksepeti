package net.yektaanil.linksepeti.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
public class ShortLinkControllerTest {

    private static final String URI = "/api/v1/urlshortener";

    private MockMvc mockMvc;

    private static final String GET_URL_END_POINT = URI + "/";
    private static final String CREATE_END_POINT = URI + "/create";
}
