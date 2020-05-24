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
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
public class ListasPersonalesController implements Serializable{
    
    
     @Inject
    private ContenidoLista contenidoLista;
    private List<ContenidoLista> listaDeContenidosDeListas;
    
    @Inject
    private Lista lista;
    private List<Lista> arrayListas;
    private List<Lista> listasPersonales=new ArrayList<>();
    private List<Lista> listasPersonalesAux;
    
    @Inject
    private Cancion cancion;
    private List<Cancion> listaDeCanciones;
    private List<Cancion> cancionesDeUnaLista;
    
    private Usuario usuario;
    
    @EJB
    private ListaFacadeLocal listaEJB;
    
    @EJB
    private CancionFacadeLocal cancionEJB;
    
    @EJB
    private ContenidoListaFacadeLocal contenidoListaEJB;

    @PostConstruct
    public void init(){
       
        arrayListas = listaEJB.findAll();
        listasPersonalesAux = listaEJB.findAll();
        listaDeContenidosDeListas = contenidoListaEJB.findAll();
        //Sacar la listas del usuario que esta conectado
       obtenerListas(listasPersonalesAux);
      
    }

    public ContenidoLista getContenidoLista() {
        return contenidoLista;
    }

    public void setContenidoLista(ContenidoLista contenidoLista) {
        this.contenidoLista = contenidoLista;
    }

    public List<ContenidoLista> getListaDeContenidosDeListas() {
        return listaDeContenidosDeListas;
    }

    public void setListaDeContenidosDeListas(List<ContenidoLista> listaDeContenidosDeListas) {
        this.listaDeContenidosDeListas = listaDeContenidosDeListas;
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

    public List<Lista> getListasPersonales() {
        return listasPersonales;
    }

    public void setListasPersonales(List<Lista> listasPersonales) {
        this.listasPersonales = listasPersonales;
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

    public List<Cancion> getCancionesDeUnaLista() {
        return cancionesDeUnaLista;
    }

    public void setCancionesDeUnaLista(List<Cancion> cancionesDeUnaLista) {
        this.cancionesDeUnaLista = cancionesDeUnaLista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void obtenerListas(List<Lista> listaAux) {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaDeCanciones = cancionEJB.findAll();
         for(Lista l: listaAux){
            if(l.getPersona().getIdPersona()==usuario.getPersona().getIdPersona()){
                listasPersonales.add(l);
            }
        }
       
    }
    
    
}