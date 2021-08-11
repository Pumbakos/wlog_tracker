package wlog_tracker.taskmodule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = {TaskController.class})
public class TaskControllerTest {
    private GsonJsonProvider gsonJsonProvider = new GsonJsonProvider();
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private TaskController taskController;

    @Autowired
    public MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void getExistingTask() {
        Task completeTaskHigh = new TaskGenerator().createCompleteTaskHigh();

        Mockito.when(taskController.get(Mockito.anyLong())).thenReturn(completeTaskHigh);

        String content = mockMvc.perform(get("/task/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        Task taskRes = mapper.readValue(content, Task.class);

        Assert.assertEquals(completeTaskHigh.getName(), taskRes.getName());
        Assert.assertEquals(completeTaskHigh.getDescription(), taskRes.getDescription());
    }

    @Test
    @SneakyThrows
    public void saveCompleteTask() {
        Gson gson = new Gson();

        Task completeTaskHigh = new TaskGenerator().createCompleteTaskHigh();
        String taskJson = gsonJsonProvider.toJson(completeTaskHigh);

        Mockito.when(taskController.save(Mockito.any(Task.class))).thenReturn(completeTaskHigh);

        String content = mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(content().json(taskJson))
                .andReturn().getResponse().getContentAsString();

        Task result = gson.fromJson(content, Task.class);

        Assert.assertEquals(completeTaskHigh.getName(), result.getName());
//        Assert.assertEquals(completeTaskHigh.getDescription(), result.getDescription());
    }

    @Test
    @SneakyThrows
    public void saveBlankTask() {
        Task completeTaskLow = new TaskGenerator().createBlankTask();
        String json = gsonJsonProvider.toJson(completeTaskLow);

        Mockito.when(taskController.save(Mockito.any(Task.class))).thenReturn(null);

        int responseLength = mockMvc.perform(post(("/task"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse().getContentLength();

        Assert.assertEquals(responseLength, 0);
    }

    @SneakyThrows
    @Test
    public void saveEmptyTask() {
        Task request = new TaskGenerator().createEmptyTask();
        String json = gsonJsonProvider.toJson(request);

        Mockito.when(taskController.save(Mockito.any(Task.class))).thenReturn(null);

        MockHttpServletResponse resultActions = mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse();

        Assert.assertEquals(resultActions.getContentLength(), 0);
    }

    @SneakyThrows
    @Test
    public void updateCompleteTask() {
        Task completeTaskLow = new TaskGenerator().createCompleteTaskLow();
        String taskJson = gsonJsonProvider.toJson(completeTaskLow);

        Mockito.when(taskController.update(Mockito.any(Task.class), Mockito.anyLong()))
                .thenReturn(completeTaskLow);

        String content = mockMvc.perform(put("/task/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(content().string(taskJson))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        Task result = mapper.readValue(content, Task.class);

        Assert.assertEquals(completeTaskLow.getName(), result.getName());
        Assert.assertEquals(completeTaskLow.getDescription(), result.getDescription());
    }

    @SneakyThrows
    @Test
    public void updateEmptyTask() {
        Task updateEmptyTask = new TaskGenerator().createEmptyTask();
        String response = gsonJsonProvider.toJson(updateEmptyTask);

        Mockito.when(taskController.update(Mockito.any(Task.class), Mockito.anyLong())).thenReturn(null);

        MockHttpServletResponse resultActions = mockMvc.perform(put("/task/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(response)).andReturn().getResponse();

        Assert.assertEquals(resultActions.getContentLength(), 0);
    }
}
