/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.CancionFacadeLocal;
import com.unileon.modelo.Cancion;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    @EJB
    private CancionFacadeLocal cancionEJB;
    
    private String accion; //Valor 'R' para nuevo 'E' para eliminar y 'M' pata modificar
    
    @PostConstruct
    public void inicio(){
        listaCanciones=cancionEJB.findAll();
        accion="R";
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
            listaCanciones=cancionEJB.findAll();
        }catch(Exception e){
            System.out.println( "error al insertar la cancion" + e.getMessage());
        }
        
    }
    
     public void eliminarCancion(){
        
       try{
           
            cancionEJB.remove(can);
            listaCanciones=cancionEJB.findAll();
            
        }catch(Exception e){
            System.out.println("error eliminar cancion"+e.getMessage());
        }
    }
    
      public void modificarCancion(){
        
       try{
           cancionEJB.edit(can);
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
     
     
     
}
