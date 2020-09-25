package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTestSuite {
    @InjectMocks
    private TaskController taskController;
    @Mock
    private DbService dbService;
    @Mock
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTaskList() {
        //Given
        List<Task> tasksList = new ArrayList<>();
        tasksList.add(new Task(1L, "title", "content"));
        when(dbService.getAllTasks()).thenReturn(tasksList);
        //When
        List<TaskDto> list = taskController.getTasks();
        //Then
        assertNotNull(list);
        list.forEach(taskDto -> {
            assertEquals("1L", taskDto.getId());
            assertEquals("title", taskDto.getTitle());
            assertEquals("content", taskDto.getContent());
        });
    }

    @Test
    public void shouldCreateTask() {
        //Given

        //When

        //Then

    }
}