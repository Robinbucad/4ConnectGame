package com.Connectgame.infraestructure.controller;


import com.Connectgame.application.GameService;
import com.Connectgame.domain.Game;
import com.Connectgame.infraestructure.controller.dto.ConnectRequest;
import com.Connectgame.infraestructure.controller.dto.input.PlayerInputDTO;
import com.Connectgame.infraestructure.controller.dto.output.GameOutputDTO;
import com.Connectgame.model.GamePlay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/game")
@CrossOrigin
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping
    private Flux<Game> getGames(){
        return gameService.getGames();
    }


    @PostMapping("/start")
    public Mono<GameOutputDTO> start(@RequestBody PlayerInputDTO player){
        log.info("start new game request {}",player);
        return gameService.createGame(player);
    }

    @PostMapping("/connect")
    public Mono<Object> connect(@RequestBody ConnectRequest request){
        log.info("connect request: {}",request);
        return gameService.connectToGame(request.getPlayer2(),request.getGameId());
    }

    @PostMapping("/gameplay")
    public Mono<Game> gameplay(@RequestBody GamePlay request){
        log.info("gameplay: {}",request);

        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + request.getGameId(),gameService.gameplay(request));
        return gameService.gameplay(request);
    }

}
