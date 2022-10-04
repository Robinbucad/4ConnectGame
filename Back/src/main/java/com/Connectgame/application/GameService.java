package com.Connectgame.application;

import com.Connectgame.domain.Game;
import com.Connectgame.domain.Player;
import com.Connectgame.infraestructure.controller.dto.input.PlayerInputDTO;
import com.Connectgame.infraestructure.controller.dto.output.GameOutputDTO;
import com.Connectgame.model.GamePlay;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameService {

    Mono<GameOutputDTO> createGame(PlayerInputDTO player);
    Flux<Game> getGames();
    Mono<Object> connectToGame(PlayerInputDTO player2, int gameId);
    Mono<Game> gameLogic(GamePlay gamePlay);

}
