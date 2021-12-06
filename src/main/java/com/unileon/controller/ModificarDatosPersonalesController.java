/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;


import com.unileon.EJB.PersonaFacadeLocal;
import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Persona;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author LuisAngel
 */
@Named
@SessionScoped

public class ModificarDatosPersonalesController implements Serializable{
   
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @EJB
    private PersonaFacadeLocal personaEJB;
    
    private Persona persona;
    
    @Inject
    private Usuario usuario;
    
    private String password; //Contraseña actual
    private String passwordNueva; //Contraseña nueva 
    
    //Me traigo toda la clase con los valores que tenia eso es la inyeccion de dependencias
    @Inject
    private ListarYFiltrarUsuariosController usuarios;    
    
    @PostConstruct
    public void init(){
       usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
       persona = usuario.getPersona();
       password="";
       passwordNueva="";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordNueva() {
        return passwordNueva;
    }

    public void setPasswordNueva(String passwordNueva) {
        this.passwordNueva = passwordNueva;
    }
    
    public void actualizarPersona(){
         try {
            personaEJB.edit(persona);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Datos Modificados"));
        } catch (Exception e) {
             System.out.println("Error al actualizar el usuario "+e.getMessage());
        }
 
    }
    
    public void actualizarUsuario(){
        try {
            
            if(password.compareTo(usuario.getPassword())==0){
                if(passwordNueva.compareTo("")==0){
                    passwordNueva=password;
                }
                usuario.setPassword(passwordNueva);
                usuarioEJB.edit(usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Datos de Usuario modificados"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contraseña actual no es correcta"));
            }
            
            
        } catch (Exception e) {
            System.out.println("Error al actualizar el usuario "+e.getMessage());
        }
    }



}

    
   
