package com.gill.recipesharingapp.Models;

import lombok.Data;
import org.json.JSONObject;

@Data
public class Response {

    private String message;
    private String status;
    private JSONObject data;

    public Response set(String status, String message, JSONObject data) {
        this.status = status;
        this.message = message;
        this.data = data;
        return this;
    }

    public Response set(String message) {
        this.message = message;
        return this;
    }


    public Response set(String status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }


}
