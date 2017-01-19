package bloggame.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import bloggame.Blogger;
import bloggame.data.BloggerRepository;
import bloggame.web.BloggerController;

public class BloggerControllerTest {

  @Test
  public void shouldShowRegistration() throws Exception {
    BloggerRepository mockRepository = mock(BloggerRepository.class);
    BloggerController controller = new BloggerController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller).build();
    mockMvc.perform(get("/blogger/register"))
           .andExpect(view().name("registerForm"));
  }
  
  @Test
  public void shouldProcessRegistration() throws Exception {
    BloggerRepository mockRepository = mock(BloggerRepository.class);
    Blogger unsaved = new Blogger("uyshete", "24hours", "Jack", "Bill", "bill@acme.com");
    Blogger saved = new Blogger(24L, "uyshete", "24hours", "Jack", "Bill", "bill@acme.ccom");
    when(mockRepository.save(unsaved)).thenReturn(saved);
    
    BloggerController controller = new BloggerController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(post("/blogger/register")
           .param("firstName", "Jack")
           .param("lastName", "Bill")
           .param("username", "uyshete")
           .param("password", "24hours")
           .param("email", "bill@acme.com"))
           .andExpect(redirectedUrl("/blogger/jbauer"));
    
    verify(mockRepository, atLeastOnce()).save(unsaved);
  }

  @Test
  public void shouldFailValidationWithNoData() throws Exception {
    BloggerRepository mockRepository = mock(BloggerRepository.class);    
    BloggerController controller = new BloggerController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller).build();
    
    mockMvc.perform(post("/blogger/register"))
        .andExpect(status().isOk())
        .andExpect(view().name("registerForm"))
        .andExpect(model().errorCount(5))
        .andExpect(model().attributeHasFieldErrors(
            "spitter", "firstName", "lastName", "username", "password", "email"));
  }

}
