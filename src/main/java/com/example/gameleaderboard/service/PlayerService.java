package com.example.gameleaderboard.service;

import com.example.gameleaderboard.dtos.PlayerDetailsDto;
import com.example.gameleaderboard.dtos.PlayerScoreDto;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    void addOrUpdateScoreToPlayer(PlayerScoreDto playerScoreDto);
    void resetPlayer(String playerId);
    void addPlayer(PlayerDetailsDto playerDetailsDto);
}
