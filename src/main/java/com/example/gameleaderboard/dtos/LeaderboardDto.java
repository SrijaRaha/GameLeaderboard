package com.example.gameleaderboard.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaderboardDto {
    String playerName;
    Integer score;
}
