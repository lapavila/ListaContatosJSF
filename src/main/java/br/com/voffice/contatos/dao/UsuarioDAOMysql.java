/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.voffice.contatos.dao;

import br.com.voffice.contatos.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avila
 */
public class UsuarioDAOMysql {

    public UsuarioDAOMysql() {
    }

    public List<Usuario> findAll() {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = ConnectionManager.getConexao();
            prepStmt = conn.prepareStatement("select * from Usuario");
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("username"), rs.getString("password"), rs.getString("ramal")));
            }
        } catch (SQLException  | GlobalcodeException ex) {
            Logger.getLogger(ClienteDAOMysql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionManager.closeAll(conn, prepStmt, rs);
        }
        return usuarios;
    }
    
    public Usuario findById(Long id) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            conn = ConnectionManager.getConexao();
            prepStmt = conn.prepareStatement("select * from Usuario where id = ?");
            prepStmt.setLong(1, id);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("username"), rs.getString("password"), rs.getString("ramal"));
            }
        } catch (SQLException | GlobalcodeException ex) {
            Logger.getLogger(ClienteDAOMysql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionManager.closeAll(conn, prepStmt, rs);
        }
        return usuario;
    }
    
    public Usuario save(Usuario usuario) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement prepStmt = null;
        String strSql;
        if (usuario.getId() == null) {
            strSql = "insert into Usuario (nome, username, password, ramal) values (?, ?, ?, ?)";
        } else {
            strSql = "update Usuario set nome = ?, username = ?, password = ? , ramal = ? where id = ?";
        }
        try {
            conn = ConnectionManager.getConexao();
            prepStmt = conn.prepareStatement(strSql);
            prepStmt.setString(1, usuario.getNome());
            prepStmt.setString(2, usuario.getUsername());
            prepStmt.setString(3, usuario.getPassword());
            prepStmt.setString(4, usuario.getRamal());
            if (usuario.getId() != null) {
                prepStmt.setLong(5, usuario.getId());
            }
            prepStmt.execute();
        } catch (SQLException | GlobalcodeException e) {
            Logger.getLogger(ClienteDAOMysql.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionManager.closeAll(conn, prepStmt, rs);
        }
        return usuario;
    }
    
    public Usuario findByUsername(String username) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            conn = ConnectionManager.getConexao();
            prepStmt = conn.prepareStatement("select * from Usuario where username = ?");
            prepStmt.setString(1, username);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("username"), rs.getString("password"), rs.getString("ramal"));
            }
        } catch (SQLException | GlobalcodeException ex) {
            Logger.getLogger(ClienteDAOMysql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionManager.closeAll(conn, prepStmt, rs);
        }
        return usuario;
    }
}
