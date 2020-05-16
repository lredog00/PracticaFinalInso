/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.CancionFacadeLocal;
import com.unileon.EJB.ContenidoListaFacadeLocal;
import com.unileon.EJB.ListaFacadeLocal;
import com.unileon.modelo.Cancion;
import com.unileon.modelo.ContenidoLista;
import com.unileon.modelo.Lista;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
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
public class ContenidoListaController implements Serializable{
    
    @Inject
    private ContenidoLista contenidoLista;
    private List<ContenidoLista> listaDeContenidosDeListas;
    
    @Inject
    private Lista lista;
    private List<Lista> arrayListas;
    
    @Inject
    private Cancion cancion;
    private List<Cancion> listaDeCanciones;
    
    private List<Cancion> cancionesDeUnaLista;
    
    
    @EJB
    private ListaFacadeLocal listaEJB;
    
    @EJB
    private CancionFacadeLocal cancionEJB;
    
    @EJB
    private ContenidoListaFacadeLocal contenidoListaEJB;

    @PostConstruct
    public void init(){
        listaDeCanciones = cancionEJB.findAll();
        arrayListas = listaEJB.findAll();
        listaDeContenidosDeListas = contenidoListaEJB.findAll();
    }
    public ContenidoLista getContenidoLista() {
        return contenidoLista;
    }

    public void setContenidoLista(ContenidoLista contenidoLista) {
        this.contenidoLista = contenidoLista;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public List<Lista> getArrayListas() {
        return arrayListas;
    }

    public void setArrayListas(List<Lista> arrayListas) {
        this.arrayListas = arrayListas;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public List<Cancion> getListaDeCanciones() {
        return listaDeCanciones;
    }

    public void setListaDeCanciones(List<Cancion> listaDeCanciones) {
        this.listaDeCanciones = listaDeCanciones;
    }

    public List<ContenidoLista> getListaDeContenidosDeListas() {
        return listaDeContenidosDeListas;
    }

    public void setListaDeContenidosDeListas(List<ContenidoLista> listaDeContenidosDeListas) {
        this.listaDeContenidosDeListas = listaDeContenidosDeListas;
    }

    public List<Cancion> getCancionesDeUnaLista() {
        return cancionesDeUnaLista;
    }

    public void setCancionesDeUnaLista(List<Cancion> cancionesDeUnaLista) {
        this.cancionesDeUnaLista = cancionesDeUnaLista;
    }
    
    public void addCancionToLista(){
        try {
            List<Cancion> listCan = null;
            int indice = 0;
            for(Cancion c: listaDeCanciones){
                if(c.getIdCancion() == cancion.getIdCancion()){
                    cancion = c;
                   
                }
            }
            for(Lista l : arrayListas){
                if(l.getIdLista() == lista.getIdLista()){
                    lista = l;
                    break;
                }
            }
            
           
                contenidoLista.setCancion(cancion);
                contenidoLista.setLista(lista);
                contenidoListaEJB.create(contenidoLista);
                
            
        } catch (Exception e) {
            System.out.println("error al añadir "+e.getMessage());
        }
    }
    
    public void establecerCancionesLista(Lista lista){
        cancionesDeUnaLista = new ArrayList<>();
        for(ContenidoLista c: listaDeContenidosDeListas){
            if(c.getLista().getIdLista()==lista.getIdLista()){
                cancionesDeUnaLista.add(c.getCancion());
            }
        }
        
    }
    
    
    
}
