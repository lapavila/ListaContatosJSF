/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.voffice.contatos.controller;

import br.com.voffice.contatos.dao.UsuarioDAOMysql;
import br.com.voffice.contatos.entity.Usuario;
import br.com.voffice.contatos.service.UsuarioNotFoundException;
import br.com.voffice.contatos.service.UsuarioService;
import br.com.voffice.contatos.util.JSFHelper;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author treinamento
 */
@Named
@SessionScoped
public class UsuarioController implements Serializable {
    
    private Usuario usuario;
    
    private UsuarioService service;

    public UsuarioController() {
        this.usuario = new Usuario();
        service = new UsuarioService();
    }

    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String efetuarLogin() {
        try {
            Usuario u = service.login(usuario.getUsername(), usuario.getPassword());
            JSFHelper.addInfo(null, "Login", "usuario identificado com sucesso");
        } catch (UsuarioNotFoundException ex) {
            JSFHelper.addError(null, "Login", ex.getMessage());
        }
        return null;
    }
    
}
