package spittr.web;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import spittr.Spitter;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpitterControllerTest {

    @Test
    public void testRegistrationForm() throws Exception {
        SpittleRepository repository = mock(SpittleRepository.class);
        SpitterController controller = new SpitterController(repository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/spitter/register"))
                .andExpect(view().name("registerForm"));
    }

    @Ignore
    @Test
    public void shouldProcessRegistration() throws Exception {
        SpittleRepository repository = mock(SpittleRepository.class);
        Spitter unsaved =
                new Spitter("jbauer", "24hours", "Jack", "Bauer");
        Spitter saved =
                new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer");
        when(repository.save(unsaved)).thenReturn(saved);
        SpitterController controller =
                new SpitterController(repository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/spitter/register")
                .param("firstName", "Jack")
                .param("lastName", "Bauer")
                .param("username", "jbauer")
                .param("password", "24hours"))
                .andExpect(redirectedUrl("/spitter/jbauer"));
        verify(repository, atLeastOnce()).save(unsaved);
    }
}