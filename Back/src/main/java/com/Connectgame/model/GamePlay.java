package com.Connectgame.model;

import com.Connectgame.domain.FourConnect;
import lombok.Data;

@Data
public class GamePlay {

    private FourConnect type;
    private int column;
    private int gameId;

}
