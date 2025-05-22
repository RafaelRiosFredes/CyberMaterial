package com.farancibia.msvc.inventarios.msvc_inventarios.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorDTO {
    private Integer status;
    private String message;

    private Map<String, String> errors;

    @Override
    public String toString() {
        return "ErrorDTO{" + "status=" + status + ", message=" + message + ", errors=" + errors + '}';
    }

}
