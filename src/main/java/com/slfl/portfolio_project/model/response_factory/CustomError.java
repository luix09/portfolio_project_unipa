package com.slfl.portfolio_project.model.response_factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class CustomError {
    private String code;
    private String message;
}
