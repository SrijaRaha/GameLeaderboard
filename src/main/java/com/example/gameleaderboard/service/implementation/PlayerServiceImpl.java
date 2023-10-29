package com.example.gameleaderboard.service.implementation;

import com.example.gameleaderboard.dtos.PlayerDetailsDto;
import com.example.gameleaderboard.dtos.PlayerScoreDto;
import com.example.gameleaderboard.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    Map<String, Set<Integer>> playerToScoresMap;

    @Autowired
    Set<Integer> scoresSet;

    @Autowired
    Map<Integer, Set<String>> scoreToPlayersMap;

    @Autowired
    LinkedList<Integer> topScores;

    @Autowired
    Map<String, PlayerDetailsDto> playerTable;

    @Override
    public void addOrUpdateScoreToPlayer(PlayerScoreDto playerScoreDto) {
        Set<Integer> scores;
        if(playerToScoresMap.containsKey(playerScoreDto.getPlayerId())){
            scores = playerToScoresMap.get(playerScoreDto.getPlayerId());
        }
        else{
            scores = new HashSet();
        }
        scores.add(playerScoreDto.getScore());
        playerToScoresMap.put(playerScoreDto.getPlayerId(), scores);
        log.info("playerToScoresMap {}", playerToScoresMap);
    }

    @Override
    public void resetPlayer(String playerId) {
        if(playerToScoresMap.containsKey(playerId)){
            Set<Integer> scores = playerToScoresMap.get(playerId);
            for(Integer score:scores){
                Set<String> playerIdList = scoreToPlayersMap.get(score);
                if(playerIdList.size()==1){
                    scoresSet.remove(score);
                    scoreToPlayersMap.remove(score);
                    topScores.remove(score);
                }
                else{
                    playerIdList.remove(playerId);
                    scoreToPlayersMap.put(score, playerIdList);
                }
            }
            playerToScoresMap.remove(playerId);
        }
    }

    @Override
    public void addPlayer(PlayerDetailsDto playerDetailsDto) {
        playerTable.put(playerDetailsDto.getPlayerId(), playerDetailsDto);
    }
}
