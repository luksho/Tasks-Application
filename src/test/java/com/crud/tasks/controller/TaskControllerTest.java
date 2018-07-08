package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testShouldGetTasks() throws Exception {
        //Given
        List<Task> tasks = new ArrayList<>();
        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(new TaskDto(1L, "title", "content"));
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);
        //When&&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].title", is("title")))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testShouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");
        when(dbService.getTask(anyLong())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        //When&&Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.title", is("title")));
    }

    @Test
    public void testShouldDeleteTask() throws Exception {
        //When&&Then
        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testShouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        //When&&Then
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testShouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        //When&&Then

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("content")));
    }
}


