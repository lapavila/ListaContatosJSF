/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.voffice.contatos.service;

import br.com.voffice.contatos.dao.UsuarioDAOMysql;
import br.com.voffice.contatos.entity.Usuario;
import java.util.List;

/**
 *
 * @author treinamento
 */
public class UsuarioService {
    UsuarioDAOMysql dao = new UsuarioDAOMysql();
    
    public List<Usuario> findAll() {
        return dao.findAll();
    }
    
    public Usuario findById(Long id) {
        return dao.findById(id);
    }
    
    public Usuario save(Usuario usuario) {
        return dao.save(usuario);
    }
    
    public Usuario findByUsername(String username) {
        return dao.findByUsername(username);
    }
    
    public Usuario login(String username, String senha) throws UsuarioNotFoundException {
        Usuario u = dao.findByUsername(username);
        if (u != null && u.validaPassword(senha)) {
            return u;
        } else {
            throw new UsuarioNotFoundException("Usuário ou senha Invalidos");
        }
    }
}
