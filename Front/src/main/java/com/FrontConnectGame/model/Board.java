package com.FrontConnectGame.model;

import lombok.Data;

@Data
public class Board {

    private Integer id;
    private String player1;
    private String player2;
    private boolean status;
}
