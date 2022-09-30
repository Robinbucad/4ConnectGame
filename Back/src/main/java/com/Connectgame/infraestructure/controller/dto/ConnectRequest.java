package com.Connectgame.infraestructure.controller.dto;

import com.Connectgame.infraestructure.controller.dto.input.PlayerInputDTO;
import lombok.Data;

@Data
public class ConnectRequest {

    PlayerInputDTO player2;
    int gameId;

}
