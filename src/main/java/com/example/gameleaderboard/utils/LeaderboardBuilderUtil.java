package com.example.gameleaderboard.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class LeaderboardBuilderUtil {

    @Autowired
    LinkedList<Integer> topScores;

    @Autowired
    Set<Integer> scoresSet;

    @Autowired
    Map<Integer, Set<String>> scoreToPlayersMap;

    public void buildTopScores(){
        if(Objects.equals(topScores.size(), scoresSet.size())){
            return;
        }
        PriorityQueue<Integer> topScoresAscending = buildMinHeap();
        reorderTopScores(topScoresAscending);
    }

    private PriorityQueue<Integer> buildMinHeap(){
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        for(Integer score:scoresSet){
            minHeap.add(score);
            if(minHeap.size()>5){
                minHeap.poll();
            }
        }
        return minHeap;
    }

    private void reorderTopScores(PriorityQueue<Integer> minHeap){
        topScores.clear();
        while(!minHeap.isEmpty()){
            topScores.addFirst(minHeap.poll());
        }
    }

    public List<String> getTopPlayerIds(){
        List<String> playerIdList = new ArrayList<>();
        for(Integer score: topScores){
            Set<String> playerIdSet = scoreToPlayersMap.get(score);
            if(playerIdSet.size()>(5-playerIdList.size())){
                int countOfPlayers = 5-playerIdList.size();
                for(String playerId:playerIdSet){
                    playerIdList.add(playerId);
                    countOfPlayers--;
                    if(countOfPlayers==0){
                        break;
                    }
                }
            }
            else{
                playerIdList.addAll(playerIdSet);
            }
            if(playerIdList.size()==5){
                break;
            }
        }
        return playerIdList;
    }
}
