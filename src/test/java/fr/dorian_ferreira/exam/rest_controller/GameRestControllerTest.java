package fr.dorian_ferreira.exam.rest_controller;

import fr.dorian_ferreira.exam.route.UrlRoute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GameRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "enzo.clement@gmail.com", roles = "[\"ROLE_USER\"]")
    public void testCreateLoggedSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.BASE_GAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCreateInJsonFromDate(
                        true,
                        true,
                        true,
                        5,
                        50,
                        2L
                )));

        resultActions
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testCreateAnonymousFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.BASE_GAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCreateInJsonFromDate(
                        true,
                        true,
                        true,
                        5,
                        50,
                        2L
                )));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "enzo.clement@gmail.com", roles = "[\"ROLE_USER\"]")
    public void testGetListGameSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(UrlRoute.BASE_GAME)
                .contentType(MediaType.APPLICATION_JSON)
                );

        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.entity").exists());
    }

    @Test
    public void testGetListGameAnonymousFail() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(UrlRoute.BASE_GAME)
                .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().is4xxClientError());
    }

    private String getCreateInJsonFromDate(Boolean hasPan, Boolean hasZoom, Boolean hasMove, Integer round, Integer maximumTime, Long mapId) {
        return "{\n" +
                "  \"hasPan\": " + hasPan + ",\n" +
                "  \"hasZoom\": " + hasZoom + ",\n" +
                "  \"hasMove\": " + hasMove + ",\n" +
                "  \"maximumTime\": " + maximumTime + ",\n" +
                "  \"round\": " + round + ",\n" +
                "  \"mapId\": " + mapId + "\n" +
                "}";
    }

}
