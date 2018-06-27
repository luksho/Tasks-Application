package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_task", "test_content");

        //When
        Task testTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals("test_task", testTask.getTitle());
        assertNotNull(testTask.getId());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "test_task", "test_content");

        //When
        TaskDto testTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("test_task", task.getTitle());
        assertNotNull(task.getId());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        Task task = new Task(1L, "test_task", "test_content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals("test_content", taskDtoList.get(0).getContent());
        assertEquals(1, taskDtoList.size());
    }
}