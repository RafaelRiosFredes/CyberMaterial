package com.rrios.msvc.productos.msvc_productos.dtos;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter@Setter
@Schema(description = "Mensaje de error")
public class ErrorDTO {
    @Schema(description = "COdigo de error",example = "200")
    private Integer status;

    @Schema(description = "Fecha del error",example = "2025-11-13")
    private Date date;

    private Map<String, String> errors;

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", date=" + date +
                ", errors=" + errors +
                '}';
    }
}
