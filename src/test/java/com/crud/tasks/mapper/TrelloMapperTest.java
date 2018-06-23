package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloMapperTest {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "trello list1", false);
        trelloListsDto.add(trelloListDto1);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "board1", trelloListsDto);
        trelloBoardsDto.add(trelloBoardDto);

        //When
        List<TrelloBoard> testTrelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        int size = testTrelloBoards.size();
        String id = testTrelloBoards.get(0).getId();
        boolean isClosed = testTrelloBoards.get(0).getLists().get(0).isClosed();

        //Then
        assertEquals(size, 1);
        assertEquals(id, "1");
        assertEquals(isClosed, false);
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "trello list1", false);
        trelloLists.add(trelloList1);

        TrelloBoard trelloBoard = new TrelloBoard("1", "board1", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List<TrelloBoardDto> testTrelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        int size = testTrelloBoardsDto.size();
        String id = testTrelloBoardsDto.get(0).getId();
        boolean isClosed = testTrelloBoardsDto.get(0).getLists().get(0).getIsClosed();

        //Then
        assertEquals(size, 1);
        assertEquals(id, "1");
        assertEquals(isClosed, false);
    }

    @Test
    public void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "trello list1", false);
        trelloListsDto.add(trelloListDto1);

        //When
        List<TrelloList> testTrelloList = trelloMapper.mapToList(trelloListsDto);
        String id = testTrelloList.get(0).getId();
        boolean isClosed = testTrelloList.get(0).isClosed();

        //Then
        assertEquals(id, "1");
        assertEquals(isClosed, false);
    }

    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "trello list1", false);
        trelloLists.add(trelloList1);

        //When
        List<TrelloListDto> testTrelloListaDto = trelloMapper.mapToListDto(trelloLists);
        String id = testTrelloListaDto.get(0).getId();
        boolean isClosed = testTrelloListaDto.get(0).getIsClosed();

        //Then
        assertEquals(id, "1");
        assertEquals(isClosed, false);
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Name", "Description", "1", "1");

        //When
        TrelloCardDto testTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        String name = testTrelloCardDto.getName();
        String listId = testTrelloCardDto.getListId();

        //Then
        assertEquals(name, "Name");
        assertEquals(listId, "1");
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name", "Description", "1", "1");

        //When
        TrelloCard testTrelloCard = trelloMapper.mapToCard(trelloCardDto);
        String name = testTrelloCard.getName();
        String listId = testTrelloCard.getListId();

        //Then
        assertEquals(name, "Name");
        assertEquals(listId, "1");
    }
}