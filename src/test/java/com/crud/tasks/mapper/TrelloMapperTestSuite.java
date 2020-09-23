package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> listDto = Arrays.asList(new TrelloListDto("1", "toDo", true));
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "name", listDto);
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(trelloBoardDto);
        //When
        List<TrelloBoard> boardList = trelloMapper.mapToBoards(boardDtoList);
        //Then
        assertEquals(1, boardList.size());
        assertEquals(trelloBoardDto.getName(), boardList.get(0).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> listDto = Arrays.asList(new TrelloList("1", "toDo", true));
        TrelloBoard trelloBoard = new TrelloBoard("1", "name", listDto);
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(trelloBoard);
        //When
        List<TrelloBoardDto> boardDtoList = trelloMapper.mapToBoardsDto(boardList);
        //Then
        assertEquals(1, boardDtoList.size());
        assertEquals(trelloBoard.getName(), boardDtoList.get(0).getName());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("1", "toDo", true));
        //When
        List<TrelloList> lists = trelloMapper.mapToList(listDtos);
        //Then
        String name = lists.get(0).getName();

        assertEquals(1, lists.size());
        assertEquals("toDo", name);
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("1", "toDo", true));
        //When
        List<TrelloListDto> listDtos = trelloMapper.mapToListDto(list);
        //Then
        String name = listDtos.get(0).getName();

        assertEquals(1, listDtos.size());
        assertEquals("toDo", name);
    }

    @Test
    public void testMapToCart() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "descr", "top", "123");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
    }

    @Test
    public void testMapToCartDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "descr", "top", "123");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
    }
}