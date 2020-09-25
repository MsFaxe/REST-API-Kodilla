package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTestSuite {
    @InjectMocks
    private TrelloController trelloController;
    @Mock
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyBoard() {
        //Given
        when(trelloController.getTrelloBoards()).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloController.getTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtoList);
        assertEquals(0, trelloBoardDtoList.size());
    }

    @Test
    public void shouldCreateNewCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        when(trelloFacade.createCard(any())).thenReturn(new CreatedTrelloCardDto("id", "name", "shortUrl"));
        //When
        CreatedTrelloCardDto newCard = trelloController.createdTrelloCard(trelloCardDto);
        //Then
        assertNotNull(newCard);
        assertEquals("name", newCard.getName());
    }
}