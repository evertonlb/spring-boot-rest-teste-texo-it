package com.teste;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.teste.controller.MovieController;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringApplicationTests {


    private MockMvc mockMvc;
	
	@Autowired
	private MovieController movieController;
	
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.movieController).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getWorstMovieTest() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.get("/worstMovie").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect( jsonPath("$.min.*.producer", hasItem( is( "Joel Silver" ))))
           .andExpect( jsonPath("$.max.*.producer", hasItem( is( "Matthew Vaughn" ))));
    }

 
}
