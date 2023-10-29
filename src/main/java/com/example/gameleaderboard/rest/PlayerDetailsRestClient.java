package com.example.gameleaderboard.rest;

import com.example.gameleaderboard.dtos.PlayerDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PlayerDetailsRestClient {

    @Autowired
    Map<String, PlayerDetailsDto> playerTable;

    public List<PlayerDetailsDto> getPlayerDetails(List<String> playerIdList){
        List<PlayerDetailsDto> playerDetailsDtoList = new ArrayList<>();
        for(String playerId:playerIdList){
            if(playerTable.containsKey(playerId)){
                playerDetailsDtoList.add(playerTable.get(playerId));
            }
            else{
                playerDetailsDtoList.add(PlayerDetailsDto.builder()
                        .playerName("placeholder_name")
                        .playerId(playerId)
                        .build());
            }
        }
        return playerDetailsDtoList;
    }
}
