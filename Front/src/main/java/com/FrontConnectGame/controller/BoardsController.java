package com.FrontConnectGame.controller;

import com.FrontConnectGame.client.BoardsClient;
import com.FrontConnectGame.model.Board;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

@Controller
@CrossOrigin
public class BoardsController {

     private final String titleGame = "4 CONNECT GAME";

    @Autowired
    BoardsClient boardsClient;


    @GetMapping("/")
    public String index(Model model){

        IReactiveDataDriverContextVariable reactiveDataDriverMode =
                new ReactiveDataDriverContextVariable(boardsClient.getBoards());

        model.addAttribute("titleGame",titleGame );
        model.addAttribute("boards",reactiveDataDriverMode);

        model.addAttribute("turn", "Turno");
        model.addAttribute("newGame", "Nueva partida");
        model.addAttribute("btnJoin", "Unirse");

        return "index";
    }

    @RequestMapping("/board/{id}")
    public String boardGame(Model model){

        model.addAttribute("titleGame",titleGame );

        model.addAttribute("turn", "Turno");
        model.addAttribute("newGame", "Nueva partida");
        model.addAttribute("btnJoin", "Unirse");

        return "board";
    }

}
