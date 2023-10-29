package com.example.gameleaderboard.service.implementation;

import com.example.gameleaderboard.dtos.LeaderboardDto;
import com.example.gameleaderboard.dtos.PlayerDetailsDto;
import com.example.gameleaderboard.dtos.PlayerScoreDto;
import com.example.gameleaderboard.rest.PlayerDetailsRestClient;
import com.example.gameleaderboard.service.ScoreService;
import com.example.gameleaderboard.utils.LeaderboardBuilderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service
@Slf4j
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    Map<Integer, Set<String>> scoreToPlayersMap;

    @Autowired
    LinkedList<Integer> topScores;

    @Autowired
    Set<Integer> scoresSet;

    @Autowired
    LeaderboardBuilderUtil leaderboardBuilderUtil;

    @Autowired
    PlayerDetailsRestClient playerDetailsRestClient;

    private void addOrUpdatePlayerToScore(PlayerScoreDto playerScoreDto) {
        Set<String> playerSet;
        if(scoreToPlayersMap.containsKey(playerScoreDto.getScore())){
            playerSet = scoreToPlayersMap.get(playerScoreDto.getScore());
        }
        else{
            playerSet = new HashSet();
        }
        playerSet.add(playerScoreDto.getPlayerId());
        scoreToPlayersMap.put(playerScoreDto.getScore(), playerSet);
    }

    @Override
    public void processScore(PlayerScoreDto playerScoreDto){
        if(topScores.isEmpty()){
            leaderboardBuilderUtil.buildTopScores();
        }
        addOrUpdatePlayerToScore(playerScoreDto);
        scoresSet.add(playerScoreDto.getScore());
        updateLeaderBoard(playerScoreDto.getScore());
        log.info("topScores {}", topScores.toString());
        log.info("scoresSet {}",scoresSet.toString());
        log.info("scoresToPlayerMap {}", scoreToPlayersMap.toString());
    }

    @Override
    public List<LeaderboardDto> getTopScores() {
        if(topScores.size()<5){
            leaderboardBuilderUtil.buildTopScores();
        }
        List<String> playerIdList = leaderboardBuilderUtil.getTopPlayerIds();
        List<PlayerDetailsDto> playerDetailsList = playerDetailsRestClient.getPlayerDetails(playerIdList);
        List<LeaderboardDto> leaderboardDtoList = new ArrayList<>();

        for(int listIterator=0; listIterator< topScores.size(); listIterator++){
            leaderboardDtoList.add(LeaderboardDto.builder()
                    .score(topScores.get(listIterator))
                    .playerName(playerDetailsList.get(listIterator).getPlayerName())
                    .build());
        }

        return leaderboardDtoList;

    }

    @Override
    public void resetLeaderboard() {
        topScores.clear();
    }

    private void updateLeaderBoard(Integer score){
        if(topScores.size()<5 || topScores.get(4) < score){
            int position = getScorePosition(score);
            if(position != -1) {
                topScores.add(position, score);
            }
        }
        if(topScores.size() == 6){
            topScores.removeLast();
        }
    }

    private int getScorePosition(Integer score){

        int position = 0;
        while(position<topScores.size()){
            if(Objects.equals(score, topScores.get(position))){
                return -1;
            }
            if(score> topScores.get(position)){
                return position;
            }
            position++;
        }
        return position;

    }
}
