package com.Connectgame.infraestructure.repository;

import com.Connectgame.domain.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PlayersRepository extends ReactiveCrudRepository<Player,Integer> {

    Mono<Player> findByName(String name);

}
