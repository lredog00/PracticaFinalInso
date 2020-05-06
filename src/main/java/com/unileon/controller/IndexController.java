/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author LuisAngel
 */
@Named
@ViewScoped

public class IndexController implements Serializable{
    
    private Usuario usuario;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void inicio(){
        usuario = new Usuario();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String verificarUsuario(){
        
        String dir;
        Usuario usuarioAux = usuarioEJB.verificarUsuario(usuario);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioAux);
        if(usuarioAux==null){
            dir = "permisosinsuficientes.xhtml?faces-redirect=true";
        }else{
            if(usuarioAux.getRol().getTipoUsuario().equalsIgnoreCase("S")){
                dir = "/privado/administrador/principalAdmin.xhtml?faces-redirect=true";
            }else{
                dir = "/privado/usuario/principalUser.xhtml?faces-redirect=true";
            }
            
        } 
        
        return dir;
    }
}
