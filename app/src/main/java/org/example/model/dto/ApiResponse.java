package org.example.model.dto;

import java.time.Instant;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @Getter
    private boolean success;

    @Getter
    private String message;

    @Getter
    private int status;

    @Getter 
    private T data;

    @Getter
    private Instant timestamp;

    @Getter
    Map<String,String> errors;

    @Getter
    private PageMetaDto page;

    public ApiResponse(){
        this.timestamp = Instant.now();
    }

    public static <T> ApiResponse<T> success(T data,String message,int status){
        ApiResponse<T> res = new ApiResponse<>();

        res.data = data;
        res.message = message;
        res.status = status;
        res.success = true;
        return res;
    }
    public static <T> ApiResponse<T> success(T data,String message,int status,PageMetaDto page){
        ApiResponse<T> res = new ApiResponse<>();

        res.data = data;
        res.message = message;
        res.status = status;
        res.success = true;
        res.page = page;
        return res;
    }

    public static <T> ApiResponse<T> success(T data,String message){
        ApiResponse<T> res = new ApiResponse<>();

        res.data = data;
        res.message = message;
        res.status = 200;
        res.success = true;
        return res;
    }

    public static <T> ApiResponse<T> success(T data,String message,PageMetaDto page){
        ApiResponse<T> res = new ApiResponse<>();

        res.data = data;
        res.message = message;
        res.status = 200;
        res.success = true;
        res.page = page;
        return res;
    }


    public static <T> ApiResponse<T> error(String message,Map<String,String> errors,int status){
        ApiResponse<T> res = new ApiResponse<>();

        res.errors = errors;
        res.message = message;
        res.status = status;
        res.success = false;
        return res;
    }

    public static <T> ApiResponse<T> error(String message,int status){
        ApiResponse<T> res = new ApiResponse<>();

        res.message = message;
        res.status = status;
        res.success = false;
        return res;
    }

    public static <T> ApiResponse<T> badRequest(String message,Map<String,String> errors){
        ApiResponse<T> res = new ApiResponse<>();

        res.errors = errors;
        res.message = message;
        res.status = 400;
        res.success = false;
        return res;
    }

    public static <T> ApiResponse<T> badRequest(String message){
        ApiResponse<T> res = new ApiResponse<>();
        res.message = message;
        res.status = 400;
        res.success = false;
        return res;
    }



    
}