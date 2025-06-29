package com.lugo.beauty_api.exception;

public class InvalidTokenException extends BadRequestException{

    private static final String DESCRIPTION = "Token expired";

    public InvalidTokenException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
