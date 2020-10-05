package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository repository;

    @Test
    public void testGetAllTasks() {
        //Given
        when(repository.findAll()).thenReturn(new ArrayList<>());
        //When

        //Then
        assertEquals(0, dbService.getAllTasks().size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testFindTaskById() {
        //Given
        Task task = new Task(1L, "title", "content");

        when(dbService.findTaskById(any())).thenReturn(java.util.Optional.of(task));
        //When
        Task foundTask = dbService.findTaskById(1L).get();
        //Then
        assertEquals("title", foundTask.getTitle());
        assertEquals("content", foundTask.getContent());
        assertEquals(Optional.of(1L), Optional.of(foundTask.getId()));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "title", "content");
        //When
        dbService.saveTask(task);
        //Then
        verify(repository, times(1)).save(task);
    }

    @Test
    public void testDeleteTask() {
        //Given

        //When
        dbService.delete(1L);
        //Then
        verify(repository, times(1)).deleteById(1L);
    }
}