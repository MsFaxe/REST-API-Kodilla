package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
    @Autowired
//    private TrelloService trelloService;
    private TrelloFacade trelloFacade;
    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        // GET request
        return trelloFacade.fetchTrelloBoards(); //trelloService ->trelloFacade

//        trelloBoards.stream()
//                .filter(e -> e.getId() != null && e.getName().contains("Kodilla") )
//                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto); //trelloService.createTrelloCard(trelloCardDto) ->trelloFacade
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloCard")
    public void GetTrelloCards() {
        CreatedTrelloCardDto trelloCards = trelloClient.getTrelloCards();
        System.out.println(trelloCards.getId() + " - "/* + trelloCardDto.getBadges()*/);

//            trelloBoardDto.getLists().forEach(trelloList ->
//                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));

    }
}
