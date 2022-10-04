package com.Connectgame.domain;

import com.Connectgame.infraestructure.controller.dto.input.GameInputDTO;
import com.Connectgame.infraestructure.controller.dto.input.PlayerInputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@Table(name = "games")
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String player1;
    private String player2;
    private GameStatus gameStatus;
    private int [][] board;
    private FourConnect winner;

    public Game (PlayerInputDTO player){
        setPlayer1(player.getName());
        setPlayer2(null);
        setGameStatus(GameStatus.NEW);
        setBoard(new int[7][6]);
        setWinner(null);
    }

    public Game(){}

}
