package com.Connectgame.domain;


import com.Connectgame.infraestructure.controller.dto.input.PlayerInputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "players")
@Data

public class Player {

    @Id
    private Integer id;
    private String name;


    public Player(PlayerInputDTO playerInputDTO){
        setName(playerInputDTO.getName());
    }

    public Player(){}
}
