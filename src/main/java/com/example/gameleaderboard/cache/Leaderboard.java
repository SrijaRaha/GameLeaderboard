package com.example.gameleaderboard.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Slf4j
@Component
public class Leaderboard {

    @Bean(name = "topScores")
    public LinkedList<Integer> topScores(){
        log.info("Initializing leaderboard");
        return new LinkedList();
    }

}
