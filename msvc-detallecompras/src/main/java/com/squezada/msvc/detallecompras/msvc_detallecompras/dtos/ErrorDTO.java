package com.squezada.msvc.detallecompras.msvc_detallecompras.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Map;

@Getter
@Setter
public class ErrorDTO {
    private Integer status;
    private Date date;

    private Map<String, String> errors;

    @Override
    public String toString() {
        return"{"+
                "status=" + status +
                ", date=" + date +
                ", errors=" + errors +
                '}';
    }

}
