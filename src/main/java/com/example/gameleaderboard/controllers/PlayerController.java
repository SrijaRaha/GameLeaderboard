package com.example.gameleaderboard.controllers;

import com.example.gameleaderboard.dtos.PlayerDetailsDto;
import com.example.gameleaderboard.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/reset/player/{playerId}")
    public void resetPlayer(@PathVariable String playerId){
        playerService.resetPlayer(playerId);
    }

    @PostMapping("add/player/id/{playerId}/name/{playerName}")
    public PlayerDetailsDto addPlayer(@PathVariable String playerId, @PathVariable String playerName){
        PlayerDetailsDto playerDetailsDto = PlayerDetailsDto.builder()
                .playerId(playerId)
                .playerName(playerName)
                .build();
        playerService.addPlayer(playerDetailsDto);
        return playerDetailsDto;
    }

}
