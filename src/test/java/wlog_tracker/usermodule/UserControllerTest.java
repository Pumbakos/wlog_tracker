package wlog_tracker.usermodule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import wlog_tracker.usermodule.controllers.UserController;
import wlog_tracker.usermodule.model.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    private Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a z").create();
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private UserController userController;

    @Autowired
    private MockMvc mock;

    @SneakyThrows
    @Test
    @DisplayName("Send GET under /user/all")
    public void getUsers() {
        User completeUser = UserGenerator.createCompleteUser();
        User additionalCompleteUser = UserGenerator.createCompleteUser();

        completeUser.setId(1L);
        additionalCompleteUser.setId(2L);

        List<User> users = Arrays.asList(completeUser, additionalCompleteUser);

        Mockito.when(userController.getAll()).thenReturn(users);

        String contentAsString = mock.perform(get("/user/all"))
                .andReturn().getResponse().getContentAsString();

        List<User> resultUser = mapper.readValue(contentAsString, new TypeReference<>() {
        });

        Assert.assertEquals(completeUser.getPesel(), resultUser.get(0).getPesel());
        Assert.assertEquals(additionalCompleteUser.getPesel(), resultUser.get(1).getPesel());
    }

    @SneakyThrows
    @Test
    @DisplayName("Send GET with proper values under /user")
    public void getUser() {
        User completeUser = UserGenerator.createCompleteUser();

        Mockito.when(userController.get(Mockito.anyLong())).thenReturn(completeUser);

        String resultActions = mock.perform(get("/user/1"))
                .andReturn().getResponse().getContentAsString();

        User result = mapper.readValue(resultActions, User.class);

        Assert.assertEquals(completeUser.getName(), result.getName());
        Assert.assertEquals(completeUser.getPesel(), result.getPesel());
    }

    @SneakyThrows
    @Test
    @DisplayName("Send POST with proper values under /user")
    public void postUserWithProperValues() {
        User completeUser = UserGenerator.createCompleteUser();
        String json = gson.toJson(completeUser);

        Mockito.when(userController.save(Mockito.any(User.class))).thenReturn(completeUser);

        String responseContent = mock.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse().getContentAsString();

        User result = mapper.readValue(responseContent, User.class);

        Assert.assertEquals(completeUser.getName(), result.getName());
        Assert.assertEquals(completeUser.getPesel(), result.getPesel());
    }

    @SneakyThrows
    @Test
    @DisplayName("Send POST with blank User under /user")
    public void postBlankUser() {
        User blankUser = UserGenerator.createBlankUser();
        String json = gson.toJson(blankUser);

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
        User emptyUser = UserGenerator.createEmptyValuesUser();
        String json = gson.toJson(emptyUser);

        Mockito.when(userController.save(Mockito.nullable(User.class))).thenReturn(null);

        int contentLength = mock.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse().getContentLength();

        Assert.assertEquals(contentLength, 0);
    }

    @SneakyThrows
    @Test
    @DisplayName("Send PUT with proper values under /user")
    public void updateUserWithProperValues() {
        User completeUser = UserGenerator.createCompleteUser();
        String json = gson.toJson(completeUser);

        Mockito.when(userController.update(Mockito.any(User.class), Mockito.anyLong())).thenReturn(completeUser);

        String contentAsString = mock.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse().getContentAsString();

        User result = mapper.readValue(contentAsString, User.class);

        Assert.assertEquals(completeUser.getName(), result.getName());
        Assert.assertEquals(completeUser.getPesel(), result.getPesel());
    }

    @SneakyThrows
    @Test
    @DisplayName("Send PUT with all null values under /user")
    public void updateUserWithAllNullValues() {
        User blankUser = UserGenerator.createBlankUser();
        String json = gson.toJson(blankUser);

        Mockito.when(userController.update(Mockito.nullable(User.class), Mockito.anyLong())).thenReturn(null);

        int contentLength = mock.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse().getContentLength();

        Assert.assertEquals(contentLength, 0);
    }

    @SneakyThrows
    @Test
    @DisplayName("Send PUT with empty string values under /user")
    public void updateUserWithEmptyStringValues() {
        User emptyUser = UserGenerator.createEmptyValuesUser();
        String json = gson.toJson(emptyUser);

        Mockito.when(userController.update(Mockito.any(User.class), Mockito.anyLong())).thenReturn(null);

        int contentLength = mock.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse().getContentLength();

        Assert.assertEquals(contentLength, 0);
    }

    @SneakyThrows
    @Test
    @DisplayName("Send DELETE with proper values under /user")
    public void deleteExistingUser() {
        User completeUser = UserGenerator.createCompleteUser();

        Mockito.when(userController.delete(Mockito.anyLong())).thenReturn(completeUser);

        String contentAsString = mock.perform(delete("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        User response = mapper.readValue(contentAsString, User.class);

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