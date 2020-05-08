/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.RolFacadeLocal;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Persona;
import com.unileon.modelo.Rol;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
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
public class UsuarioController implements Serializable{
    @Inject
    private Usuario usuario;
    private List<Usuario> listaDeUsuarios;
    
    @Inject
    private Persona persona;
    
    @Inject
    private Rol rol;
    private List<Rol> listaDeRoles;
    
    
    @EJB
    private RolFacadeLocal rolEJB;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void inicio(){
        listaDeRoles = rolEJB.findAll();
        listaDeUsuarios = usuarioEJB.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getListaDeRoles() {
        return listaDeRoles;
    }

    public void setListaDeRoles(List<Rol> listaDeRoles) {
        this.listaDeRoles = listaDeRoles;
    }

    public List<Usuario> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
        this.listaDeUsuarios = listaDeUsuarios;
    }
    
    public void insertarUsuario(){
        try{
            /**Para que me coja los datos del rol**/
            for(Rol r : listaDeRoles){
                if(r.getIdRol()==rol.getIdRol()){
                    rol=r;
                    break;
                }
            }
            usuario.setRol(rol);            
            usuario.setPersona(persona);           
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró"));
            listaDeUsuarios = usuarioEJB.findAll();
        }catch(Exception e){
            System.out.println( "error al insertar el usuario" + e.getMessage());
        }
    }
    
    public void eliminarUsuario(){
        try{
            
            for (Usuario u:listaDeUsuarios) {
                if(u.getIdUsuario()==usuario.getIdUsuario()){
                    usuario=u;
                    break;
                }
            }
            
            usuarioEJB.remove(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se elimino el Usuario"));
            listaDeUsuarios = usuarioEJB.findAll();
        }catch(Exception e){
            System.out.println( "error al insertar el usuario" + e.getMessage());
        }
    }
   
}
