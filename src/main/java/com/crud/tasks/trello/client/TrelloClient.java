package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBadgesDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.api.username}")
    private String trelloUsername;
    @Value("${trello.app.idCard}")
    private String trelloIdCard;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = buildURL();
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

//        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
//                "https://api.trello.com/1/members/joannajanusiewicz/boards?fields=name,id&key=8e6da55a8373c5da47fbd3ff9a9d19a5&token=3508528787bff09d586c17d5c8a2c60b2cdfb04721c137641b26835d564f1bf1",
//                TrelloBoardDto[].class);

        if (Optional.ofNullable(boardsResponse).isPresent()) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }

    public URI buildURL() {
         return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint)
                .path("/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();
    }

    public CreatedTrelloCard createNewCard (TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }

    public CreatedTrelloCard getTrelloCards() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards/")
                .path(/*trelloIdCard*/ "5f292ebf3ddec97f20d33f1e")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "badges").build().encode().toUri();

        return restTemplate.getForObject(url, CreatedTrelloCard.class);
        
        //https://api.trello.com/1/cards/
        // 5f292ebf3ddec97f20d33f1e
        // ?key=8e6da55a8373c5da47fbd3ff9a9d19a5
        // &token=3508528787bff09d586c17d5c8a2c60b2cdfb04721c137641b26835d564f1bf1
        // &fields=badges
    }
}
