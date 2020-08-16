/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.exceptionConfig;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class BadRequesException extends RuntimeException {

    public BadRequesException(String mensagem) {
        super(mensagem);
    }
}
