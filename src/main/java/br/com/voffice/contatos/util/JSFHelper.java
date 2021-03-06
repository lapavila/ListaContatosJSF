/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.voffice.contatos.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author treinamento
 */
public class JSFHelper {
    public static ExternalContext getExternalContext() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        return ctx.getExternalContext();
    }
    
    public static String getRequestParameter(String name) {
        return getExternalContext().getRequestParameterMap().get(name);
    }
    
    public static Object getRequestAttribute(String name) {
        return getExternalContext().getRequestMap().get(name);
    }
    
    public static Object getSessionAttribute(String name) {
        return getExternalContext().getSessionMap().get(name);
    }
    
    public static Object getApplicationAttribute(String name) {
        return getExternalContext().getApplicationMap().get(name);
    }
    
    public static void addError(String id, String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_ERROR, id, summary, detail);
    }
    
    public static void addInfo(String id, String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_INFO, id, summary, detail);
    }    
    
    public static void addWarn(String id, String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_WARN, id, summary, detail);
    } 
    
    public static void addMessage(FacesMessage.Severity severity, String id, String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(id, mensagem);
    }
}
