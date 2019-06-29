package tvs.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import tvs.web.SpringStart;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={SpringStart.class}, loader=AnnotationConfigWebContextLoader.class)
//@ActiveProfiles("dev")
public class TestSpringController {
	private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
    	System.err.println("====================");
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	
//	@BeforeClass
//    public static void before() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @After
    public void afterEachTest() {
    }
    
    @Test
    public void testStartController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello.htm")).andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/tests/welcome")).andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/counterIncr/?num=10")).andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/counterIncr/matrix/42;id=15;nom=thomas")).andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies/list")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
