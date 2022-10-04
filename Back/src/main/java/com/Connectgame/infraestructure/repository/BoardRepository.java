package com.Connectgame.infraestructure.repository;

import com.Connectgame.domain.Game;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BoardRepository extends ReactiveCrudRepository<Game, Integer> {
    Mono<Game> findByPlayer1(String player1);
    Mono<Game> findByPlayer2(String player2);

}
