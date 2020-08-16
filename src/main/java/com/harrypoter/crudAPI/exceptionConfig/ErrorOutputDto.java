/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.exceptionConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

/**
 *
 * @author JORDAN QUEIROGA
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorOutputDto {

    private Integer codeStatus;
    private String statusMessage;
    private String httpMethod;
    private String message;
    private String error;
    private String detalhe;
    private String path;

    public int getCodeStatus() {
        return codeStatus;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public String getPath() {
        return path;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ErrorOutputDto error;

        Builder() {
            this.error = new ErrorOutputDto();
        }

        public Builder addStatus(HttpStatus status) {
            this.error.codeStatus = status.value();
            this.error.statusMessage = status.getReasonPhrase();
            return this;
        }

        public Builder addHttpMethod(String method) {
            this.error.httpMethod = method;
            return this;
        }

        public Builder addMessage(String erro) {
            this.error.message = erro;
            return this;
        }

        public Builder addErroCause(String erroCause) {
            this.error.error = erroCause;
            return this;
        }

        public Builder addDetalhe(String detalhe) {
            this.error.detalhe = detalhe;
            return this;
        }

        public Builder addPath(String path) {
            this.error.path = path;
            return this;
        }

        public ErrorOutputDto build() {
            return this.error;
        }
    }
}
