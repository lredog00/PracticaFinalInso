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
    
    @PostConstruct
    public void inicio(){
       
        listaCanciones=cancionEJB.findAll();
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
        }catch(Exception e){
            System.out.println( "error al insertar la cancion" + e.getMessage());
        }
        
    }
    
     public void eliminarCancion(){
        
       try{
            for (Cancion c:listaCanciones) {
                if(c.getIdCancion()==can.getIdCancion()){
                    can=c;
                    break;
                }
            }
            cancionEJB.remove(can);
        }catch(Exception e){
            System.out.println("error "+e.getMessage());
        }
    }
     
     
     
     
}
