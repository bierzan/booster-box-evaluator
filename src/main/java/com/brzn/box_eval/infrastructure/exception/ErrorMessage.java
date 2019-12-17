package com.brzn.box_eval.infrastructure.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
 class ErrorMessage {
    private String message;
    private LocalDateTime timestamp;
}
