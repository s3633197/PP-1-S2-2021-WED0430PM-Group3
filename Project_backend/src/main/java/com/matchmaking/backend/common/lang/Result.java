package com.matchmaking.backend.common.lang;


import lombok.Data;

@Data
public class Result {

    private String message;

    private Object data;

    private long statusCode;


    protected Result(long statusCode,String message,Object data) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }


    public static Result success(Object data){
        return new Result(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMessage(),data);
    }

    public static Result success(Object data,String message){
        return new Result(StatusCode.SUCCESS.getCode(),message,data);
    }

    public static Result create(Object data){
        return new Result(StatusCode.CREATE.getCode(), StatusCode.CREATE.getMessage(), data);
    }

    public static Result create(Object data,String message){
        return new Result(StatusCode.CREATE.getCode(), message, data);
    }

    public static Result failed(Object data){
        return new Result(StatusCode.BAD.getCode(),StatusCode.BAD.getMessage(),data);
    }
    public static Result failed(Object data,String message){
        return new Result(StatusCode.BAD.getCode(),message,data);
    }



}
