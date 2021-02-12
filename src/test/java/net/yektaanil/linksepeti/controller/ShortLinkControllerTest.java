package net.yektaanil.linksepeti.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import net.yektaanil.linksepeti.dto.ShortLinkInputDTO;
import net.yektaanil.linksepeti.service.ShortLinkService;
import net.yektaanil.linksepeti.util.ShortLinkUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ActiveProfiles("test")
class ShortLinkControllerTest {

    private static final String URI = "/api/v1/urlshortener";
    private static final String GET_URL_END_POINT = URI + "/{hashcode}";
    private static final String CREATE_END_POINT = URI + "/create";

    @MockBean
    private ShortLinkService ShortLinkService;

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(context)
                        .apply(documentationConfiguration(restDocumentationContextProvider))
                        .alwaysDo(
                                document(
                                        "{method-name}",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint())))
                        .build();
    }

    @Test
    void testCreateHashedUrl() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post(CREATE_END_POINT)
                        .content(ShortLinkUtil.asJsonString(ShortLinkUtil.getShortLinkDTO()))
                        .characterEncoding("UTF-8").accept(
                                MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void invalidCreateInput() throws Exception {
        ShortLinkInputDTO shortLinkInputDTO = ShortLinkUtil.getShortLinkDTO();
        shortLinkInputDTO.setUrl("not_valid_url");
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_END_POINT)
                .content(ShortLinkUtil.asJsonString(shortLinkInputDTO))
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void testGetUrlByHashCode() throws Exception {
        when(ShortLinkService.getByHashCode(anyString()))
                .thenReturn("www.google.com");
        mockMvc.perform(MockMvcRequestBuilders.get(GET_URL_END_POINT, "123")
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isTemporaryRedirect())
                .andReturn();
    }

}
