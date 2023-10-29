package com.example.gameleaderboard.controllers;

import com.example.gameleaderboard.dtos.LeaderboardDto;
import com.example.gameleaderboard.dtos.PlayerScoreDto;
import com.example.gameleaderboard.service.PlayerService;
import com.example.gameleaderboard.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController()
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @Autowired
    PlayerService playerService;

    @PostMapping("/player/{playerId}/score/{score}")
    public PlayerScoreDto addScore(@PathVariable String playerId, @PathVariable Integer score){
        PlayerScoreDto playerScoreDto = PlayerScoreDto.builder()
                .playerId(playerId)
                .score(score)
                .build();


        scoreService.processScore(playerScoreDto);
        playerService.addOrUpdateScoreToPlayer(playerScoreDto);
        return playerScoreDto;


    }

    @GetMapping("/leaderboard/getTopScores")
    public List<LeaderboardDto> getLeaderboard(){
        return scoreService.getTopScores();
    }

    @GetMapping("/leaderboard/reset")
    public void resetLeaderboard(){
        scoreService.resetLeaderboard();
    }
}
