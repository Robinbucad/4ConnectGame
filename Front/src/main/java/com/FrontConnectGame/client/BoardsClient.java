package com.FrontConnectGame.client;

import com.FrontConnectGame.model.Board;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(url = "http://localhost:8081/api/v0/boards", name = "boards-back")
public interface BoardsClient {

    @GetMapping
    public Flux<Board> getBoards();

}
