package com.Connectgame.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FourConnect {
    RED(1),GREEN(2), EMPTY(0);

    private Integer value;
}
