package com.project.bookreview.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private Integer status;
    private String message;
    private Long timestamp;


}
