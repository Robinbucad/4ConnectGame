package com.Connectgame.infraestructure.controller.dto.output;

import com.Connectgame.domain.Player;
import lombok.Data;

@Data
public class PlayerOutputDTO {
    private Integer id;
    private String name;

    public PlayerOutputDTO (Player player){
        setId(player.getId());
        setName(player.getName());
    }

    public PlayerOutputDTO(){}
}
