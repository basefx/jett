package com.dovgopolyy.jett.server.search;


import com.dovgopolyy.jett.server.ServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ServerEndToEndTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void testEndToEnd() throws Exception {

        final String[] results = new String[1];

        mvc.perform(post("/search/document")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("EndToEnd TestCase"))

                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(result -> results[0] = result.getResponse().getContentAsString());



        mvc.perform(get("/search/document/" + results[0])
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string("EndToEnd TestCase"));

        mvc.perform(get("/search?tokens=testcase")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string("["+results[0]+"]"));


    }
}
