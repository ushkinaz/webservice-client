package ru.sid0renk0.webtest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTests {

    @Autowired
    private WebApplicationContext server;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(server).build();
    }

    @Test
    public void registerClient() throws Exception {
        request(register("anton", "pass"))
            .andExpect(xpath("/response/result-code").string("0"));
    }

    @Test
    public void doubleRegisterClient() throws Exception {
        request(register("anton", "pass"))
            .andExpect(xpath("/response/result-code").string("0"));

        request(register("anton", "pass1"))
            .andExpect(xpath("/response/result-code").string("1"));
    }

    @Test
    public void getBalance() throws Exception {
        request(register("anton", "pass"))
            .andExpect(xpath("/response/result-code").string("0"));

        request(balance("anton", "pass"))
            .andExpect(xpath("/response/result-code").string("0"))
            .andExpect(xpath("/response/extra[@name='balance']/text()").string("0.00"));
    }

    @Test
    public void getBalanceWrongPass() throws Exception {
        request(register("енот", "тот"))
            .andExpect(xpath("/response/result-code").string("0"));

        request(balance("енот", "нонетот"))
            .andExpect(xpath("/response/result-code").string("4"))
            .andExpect(xpath("/response/extra[@name='balance']").doesNotExist());
    }

    @Test
    public void userDoesNotExist() throws Exception {
        request(balance("anton", "pass"))
            .andExpect(xpath("/response/result-code").string("3"));
    }

    @Test
    public void emptyRequest() throws Exception {
        request("")
            .andExpect(xpath("/response/result-code").string("2"));
    }

    private ResultActions request(String body) throws Exception {
        return mockMvc.perform(post("/clients")
            .accept(APPLICATION_XML)
            .contentType(APPLICATION_XML)
            .content(body)
        );
    }

    private String balance(String login, String password) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<request>\n" +
            "  <request-type>GET-BALANCE</request-type>\n" +
            "  <extra name=\"login\">" + login + "</extra>\n" +
            "  <extra name=\"password\">" + password + "</extra>\n" +
            "</request>";
    }

    private String register(String login, String password) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<request>\n" +
            "  <request-type>CREATE-AGT</request-type>\n" +
            "  <extra name=\"login\">" + login + "</extra>\n" +
            "  <extra name=\"password\">" + password + "</extra>\n" +
            "</request>";
    }
}
