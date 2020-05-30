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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author LuisAngel
 */
@Named
@ViewScoped

public class ModificarUsuarioController implements Serializable{
   
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @Inject
    private Usuario usuario;
    
    //Me traigo toda la clase con los valores que tenia eso es la inyeccion de dependencias
    @Inject
    private ListarYFiltrarUsuariosController usuarios;    
    
    @PostConstruct
    public void init(){
        usuario = usuarios.getUsuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void actualizarUsuario(){
        try {
            usuarioEJB.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario modificado"));
        } catch (Exception e) {
             System.out.println("Error al actualizar el usuario "+e.getMessage());
        }
 
    }



}

    
   
