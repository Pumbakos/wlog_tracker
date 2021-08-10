package wlog_tracker.usermodule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    private GsonJsonProvider gsonJsonProvider = new GsonJsonProvider();
    private ObjectMapper mapper= new ObjectMapper();

    @MockBean
    private UserController userController;

    @Autowired
    private MockMvc mock;

    @SneakyThrows
    @Test
    @DisplayName("Send GET under /user/all")
    public void getUsers() {
        User completeUser = new UserGenerator().createCompleteUser();
        User additionalCompleteUser = new UserGenerator().createCompleteUser();

        completeUser.setId(1L);
        additionalCompleteUser.setId(2L);

        List<User> users = Arrays.asList(completeUser, additionalCompleteUser);
        String json = gsonJsonProvider.toJson(users);

        Mockito.when(userController.getAll()).thenReturn(users);

        String responseContent = mock.perform(get("/user/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        Assert.assertEquals(json, responseContent);
    }

    @SneakyThrows
    @Test
    @DisplayName("Send GET with proper values under /user")
    public void getUser() {
        User completeUser = new UserGenerator().createCompleteUser();

        Mockito.when(userController.get(Mockito.anyLong())).thenReturn(completeUser);

        String resultActions = mock.perform(get("/user/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        User result = mapper.readValue(resultActions, User.class);

        Assert.assertEquals(completeUser.getName(), result.getName());
        Assert.assertEquals(completeUser.getPesel(), result.getPesel());
    }

    @SneakyThrows
    @Test
    @DisplayName("Send POST with proper values under /user")
    public void postUserWithProperValues() {
        User completeUser = new UserGenerator().createCompleteUser();
        String json = gsonJsonProvider.toJson(completeUser);

        Mockito.when(userController.save(Mockito.any(User.class))).thenReturn(completeUser);

        String responseContent = mock.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(content().json(json))
                .andReturn().getResponse().getContentAsString();

        User result = mapper.readValue(responseContent, User.class);

        Assert.assertEquals(completeUser.getName(), result.getName());
        Assert.assertEquals(completeUser.getPesel(), result.getPesel());
    }

    @SneakyThrows
    @Test
    @DisplayName("Send POST with blank User under /user")
    public void postBlankUser() {
        User blankUser = new UserGenerator().createBlankUser();
        String json = gsonJsonProvider.toJson(blankUser);

        Mockito.when(userController.save(Mockito.nullable(User.class))).thenReturn(null);

        MockHttpServletResponse resultResponse = mock.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse();

        Assert.assertEquals(resultResponse.getContentLength(), 0);
    }

    @SneakyThrows
    @Test
    @DisplayName("Send POST with empty String values under /user")
    public void postUserWithEmptyStringValues() {
        User emptyUser = new UserGenerator().createEmptyValuesUser();
        String json = gsonJsonProvider.toJson(emptyUser);

        Mockito.when(userController.save(Mockito.any(User.class))).thenReturn(null);

        MockHttpServletResponse resultResponse = mock.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse();

        Assert.assertEquals(resultResponse.getContentLength(), 0);
    }

    @SneakyThrows
    @Test
    @DisplayName("Send PUT with proper values under /user")
    public void updateUserWithProperValues() {
        User completeUser = new UserGenerator().createCompleteUser();
        String response = gsonJsonProvider.toJson(completeUser);

        Mockito.when(userController.update(Mockito.any(User.class), Mockito.anyLong())).thenReturn(completeUser);

        String responseContent = mock.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(response))
                .andExpect(content().json(response))
                .andReturn().getResponse().getContentAsString();

        User result = mapper.readValue(responseContent, User.class);

        Assert.assertEquals(completeUser.getName(), result.getName());
        Assert.assertEquals(completeUser.getPesel(), result.getPesel());
    }

    @SneakyThrows
    @Test
    @DisplayName("Send PUT with all null values under /user")
    public void updateUserWithAllNullValues() {
        User blankUser = new UserGenerator().createBlankUser();
        String response = gsonJsonProvider.toJson(blankUser);

        Mockito.when(userController.update(Mockito.nullable(User.class), Mockito.anyLong())).thenReturn(null);

        int contentLength = mock.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(response)).andReturn().getResponse().getContentLength();

        Assert.assertEquals(contentLength, 0);
    }

    @SneakyThrows
    @Test
    @DisplayName("Send PUT with empty string values under /user")
    public void updateUserWithEmptyStringValues() {
        User emptyUser = new UserGenerator().createEmptyValuesUser();
        String response = gsonJsonProvider.toJson(emptyUser);

        Mockito.when(userController.update(Mockito.any(User.class), Mockito.anyLong())).thenReturn(null);

        MockHttpServletResponse resultResponse = mock.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(response)).andReturn().getResponse();

        Assert.assertEquals(resultResponse.getContentLength(), 0);
    }

    @SneakyThrows
    @Test
    @DisplayName("Send DELETE with proper values under /user")
    public void deleteExistingUser() {
        User completeUser = new UserGenerator().createCompleteUser();

        Mockito.when(userController.delete(Mockito.anyLong())).thenReturn(completeUser);

        String responseContent = mock.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON)).
                andReturn().getResponse().getContentAsString();

        User response = mapper.readValue(responseContent, User.class);

        Assert.assertEquals(response.getName(), completeUser.getName());
        Assert.assertEquals(response.getPesel(), completeUser.getPesel());
    }

    @SneakyThrows
    @Test
    @DisplayName("Send DELETE with not existing ID under /user")
    public void deleteNotExistingUser() {
        Mockito.when(userController.delete(Mockito.anyLong())).thenReturn(null);

        int contentLength = mock.perform(delete("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)).
                andReturn().getResponse().getContentLength();

        Assert.assertEquals(contentLength, 0);
    }
}