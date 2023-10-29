package com.example.gameleaderboard.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class ScoreToPlayerTable {

    @Bean(name = "scoreToPlayersMap")
    public Map<Integer, Set<String>> scoreToPlayersMap(){
        log.info("Initializing scoreToPlayersMap");
        return new HashMap<>();
    }
}
