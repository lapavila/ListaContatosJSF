/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.voffice.contatos.dao;

import br.com.voffice.contatos.entity.Cliente;
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
public class ClienteDAOMysql {

    public ClienteDAOMysql() {
    }

    public List<Cliente> findAll() {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = ConnectionManager.getConexao();
            prepStmt = conn.prepareStatement("select * from cliente");
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                clientes.add(new Cliente(rs.getLong("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("telefone")));
            }
        } catch (SQLException  | GlobalcodeException ex) {
            Logger.getLogger(ClienteDAOMysql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionManager.closeAll(conn, prepStmt, rs);
        }
        return clientes;
    }

    public Cliente findById(Long id) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            conn = ConnectionManager.getConexao();
            prepStmt = conn.prepareStatement("select * from cliente where id = ?");
            prepStmt.setLong(1, id);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(rs.getLong("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("telefone"));
            }
        } catch (SQLException e) {
        } catch (GlobalcodeException ex) {
            Logger.getLogger(ClienteDAOMysql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionManager.closeAll(conn, prepStmt, rs);
        }
        return cliente;
    }

    public Cliente findByTelefone(String telefone) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            conn = ConnectionManager.getConexao();
            prepStmt = conn.prepareStatement("select * from cliente where telefone = ?");
            prepStmt.setString(1, telefone);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(rs.getLong("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("telefone"));
            }
        } catch (SQLException e) {
        } catch (GlobalcodeException ex) {
            Logger.getLogger(ClienteDAOMysql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionManager.closeAll(conn, prepStmt, rs);
        }
        return cliente;
    }

    public Cliente save(Cliente cliente) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement prepStmt = null;
        String strSql;
        if (cliente.getId() == null) {
            strSql = "insert into Cliente (endereco, nome, telefone) values (?, ?, ?)";
        } else {
            strSql = "update Cliente set endereco = ?, nome = ?, telefone = ? where id = ?";
        }
        try {
            conn = ConnectionManager.getConexao();
            prepStmt = conn.prepareStatement(strSql);
            prepStmt.setString(1, cliente.getEndereco());
            prepStmt.setString(2, cliente.getNome());
            prepStmt.setString(3, cliente.getTelefone());
            if (cliente.getId() != null) {
                prepStmt.setLong(4, cliente.getId());
            }
            prepStmt.execute();
        } catch (SQLException | GlobalcodeException e) {
            Logger.getLogger(ClienteDAOMysql.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionManager.closeAll(conn, prepStmt, rs);
        }
        return cliente;
    }
}
