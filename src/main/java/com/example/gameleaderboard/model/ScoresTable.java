package com.example.gameleaderboard.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class ScoresTable {

    @Bean(name = "scoresSet")
    public Set<Integer> scoresSet(){
        log.info("Initializing scores table");
        return new HashSet();
    }
}
