package com.Connectgame.utils.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    private int httpCode;
    private String msgError;
    private Date date;


    public ErrorMessage(int httpCode,String msgError, Date date){
        this.httpCode = httpCode;
        this.msgError = msgError;
        this.date = date;
    }

    public ErrorMessage(){}
}
