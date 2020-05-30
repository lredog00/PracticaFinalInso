/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.CancionFacadeLocal;
import com.unileon.EJB.ContenidoListaFacadeLocal;
import com.unileon.modelo.Cancion;
import com.unileon.modelo.ContenidoLista;
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
public class CancionController implements Serializable{
    @Inject
    private Cancion can;
    private List<Cancion> listaCanciones;
    
        
    private List<ContenidoLista> listaContenido;
    
    @EJB
    private ContenidoListaFacadeLocal contenidoListaEJB;
    
    @EJB
    private CancionFacadeLocal cancionEJB;
    
    private String accion; //Valor 'R' para nuevo 'E' para eliminar y 'M' pata modificar
    
    @PostConstruct
    public void inicio(){
        listaCanciones=cancionEJB.findAll();
        listaContenido = contenidoListaEJB.findAll();
        accion="R";
    }

    public List<ContenidoLista> getListaContenido() {
        return listaContenido;
    }

    public void setListaContenido(List<ContenidoLista> listaContenido) {
        this.listaContenido = listaContenido;
    }
    
    
    
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public Cancion getCan() {
        return can;
    }

    public void setCan(Cancion can) {
        this.can = can;
    }
    
    public void insertarCancion(){
        
        try{
            cancionEJB.create(can);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Canción creada"));
            listaCanciones=cancionEJB.findAll();
        }catch(Exception e){
            System.out.println( "error al insertar la cancion" + e.getMessage());
        }
        
    }
    
     public void eliminarCancion(){
        
       try{
           
           for(ContenidoLista c : listaContenido){
               if(c.getCancion().getIdCancion() == can.getIdCancion()){
                   contenidoListaEJB.remove(c);
               }
           }
           cancionEJB.remove(can);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se elimino la canción "));
            
           listaCanciones=cancionEJB.findAll();
            
        }catch(Exception e){
            System.out.println("error eliminar cancion"+e.getMessage());
        }
    }
    
      public void modificarCancion(){
        
       try{
           cancionEJB.edit(can);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Canción modificada"));
           listaCanciones=cancionEJB.findAll();
        }catch(Exception e){
            System.out.println("error al modificar Cancion"+e.getMessage());
        }
    }
     
    public void establecerCancionModificar(Cancion c){
        can = c;
        accion="M";
    }
    
    public void establecerCancionEliminar(Cancion c){
        can = c;
        accion="E";
    }
    public void establecerCancion(Cancion c){
        can = c;
    }
     
     
     @Override
    public String toString() {
        return "CancionController{" + "can=" + can + ", listaCanciones=" + listaCanciones + ", cancionEJB=" + cancionEJB + ", accion=" + accion + '}';
    }
        
}
