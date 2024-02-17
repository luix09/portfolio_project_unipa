package com.slfl.portfolio_project.model.response_factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class CustomResponse {
    private String code;
    private String message;
    private Object data;

    public CustomResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
