package com.Connectgame.infraestructure.controller.dto.input;

import com.Connectgame.domain.FourConnect;
import com.Connectgame.domain.GameStatus;
import lombok.Data;

@Data
public class GameInputDTO {

    private String player1;
    private String player2;
    private GameStatus gameStatus;
    private int [][] board;
    private FourConnect winner;


}
