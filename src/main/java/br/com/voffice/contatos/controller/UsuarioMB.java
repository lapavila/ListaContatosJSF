/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.voffice.contatos.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author treinamento
 */
@Named
@SessionScoped
public class UsuarioMB implements Serializable {
    
    public String getUsername() {
        return "usuario de teste";
    }
}
