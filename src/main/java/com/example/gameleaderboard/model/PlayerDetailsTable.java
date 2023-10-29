package com.example.gameleaderboard.model;

import com.example.gameleaderboard.dtos.PlayerDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class PlayerDetailsTable {

    @Bean(name = "playerTable")
    public Map<String, PlayerDetailsDto> playerTable(){
        log.info("Initializing player details table");
        return new HashMap();
    }
}
