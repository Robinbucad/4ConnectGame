package com.Connectgame.infraestructure.controller.dto.output;

import com.Connectgame.domain.FourConnect;
import com.Connectgame.domain.Game;
import com.Connectgame.domain.GameStatus;
import lombok.Data;

@Data
public class GameOutputDTO {

    private int id;
    private String player1;
    private String player2;
    private GameStatus gameStatus;
    private int [][] board;
    private FourConnect winner;

    public GameOutputDTO(Game game){
        setId(game.getId());
        setPlayer1(game.getPlayer1());
        setPlayer2(game.getPlayer2());
        setGameStatus(game.getGameStatus());
        setBoard(game.getBoard());
        setWinner(game.getWinner());
    }

    public GameOutputDTO(){}

}
