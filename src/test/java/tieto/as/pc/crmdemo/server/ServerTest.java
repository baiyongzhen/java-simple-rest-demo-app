package tieto.as.pc.crmdemo.server;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tieto.as.pc.crmdemo.util.CrmConsts;
import tieto.as.pc.crmdemo.webserver.Server;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


// NOTE: Spring Rest Controller test reads application.properties file
// from /main/webapp directory!
@SpringJUnitWebConfig(Server.class)
@AutoConfigureMockMvc
@ComponentScan(basePackages = {"tieto"})
public class ServerTest {

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setup() {
        logger.debug(CrmConsts.LOG_ENTER);
        logger.debug(CrmConsts.LOG_EXIT);
    }


    @Test
    void getInfoTest() throws Exception {
        logger.debug(CrmConsts.LOG_ENTER);
        HashMap<String, String> expectedResult = new HashMap<>();

        expectedResult.put("info", "Try /customer/<number>");
        // We need to remove the stupid backslashes which JSONObject hard codes.
        String expectedResultJson = new JSONObject(expectedResult).toString().replace("\\", "");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/info")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8);
        MvcResult mvcResult = this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResultJson))
                .andReturn();

        logger.trace("Content: " + mvcResult.getResponse().getContentAsString());
        logger.debug(CrmConsts.LOG_EXIT);
    }


    @Test
    void getCustomerByIdOkTest() throws Exception {
        logger.debug(CrmConsts.LOG_ENTER);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/customer/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8);
        MvcResult mvcResult = this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.ret").value("ok"))
                .andExpect(jsonPath("$.customer.id").value("1"))
                .andExpect(jsonPath("$.customer.firstName").value("Kari"))
                .andReturn();
        logger.trace("Content: " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getCustomerByIdNotFoundTest() throws Exception {
        logger.debug(CrmConsts.LOG_ENTER);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/customer/4")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8);
        MvcResult mvcResult = this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.ret").value("failed"))
                .andExpect(jsonPath("$.msg").value("Could not find customer with id 4"))
                .andReturn();
        logger.trace("Content: " + mvcResult.getResponse().getContentAsString());
    }

}
