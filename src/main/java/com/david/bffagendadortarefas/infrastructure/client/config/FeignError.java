package com.david.bffagendadortarefas.infrastructure.client.config;

import com.david.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.david.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.david.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import com.david.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.david.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String errorMessage = ErrorMessage(response);

        switch (response.status()) {
            case 409:
                return new ConflictException("Erro: " + errorMessage);
            case 403:
                return new ResourceNotFoundException("Erro: " + errorMessage);
            case 401:
                return new UnauthorizedException("Erro: " + errorMessage);
            case 400:
                return new IllegalArgumentException("Erro: " + errorMessage);
            default:
                return new BusinessException("Erro: " + errorMessage);
        }
    }

    private String ErrorMessage(Response response) {
        try {
            if (Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
