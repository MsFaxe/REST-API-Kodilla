package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "title", "content");
        Task task2 = new Task(2L, "title2", "content2");
        List<Task> tasks = Arrays.asList(task, task2);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertNotNull(taskDtoList);
        assertEquals(2, taskDtoList.size());
    }

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals("title", task.getTitle());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title", "content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals("title", taskDto.getTitle());
    }
}
