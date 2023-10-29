package com.example.gameleaderboard.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class PlayerToScoreTable {

    @Bean(name = "playerToScoresMap")
    public Map<String, Set<Integer>>  playerToScoresMap(){
        log.info("Initializing playerToScoresMap");
        return new HashMap<>();
    }

}
