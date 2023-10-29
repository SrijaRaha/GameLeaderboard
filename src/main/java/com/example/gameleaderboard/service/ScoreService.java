package com.example.gameleaderboard.service;

import com.example.gameleaderboard.dtos.LeaderboardDto;
import com.example.gameleaderboard.dtos.PlayerScoreDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {
    void processScore(PlayerScoreDto playerScoreDto);
    List<LeaderboardDto> getTopScores();

    void resetLeaderboard();
}
