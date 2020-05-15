/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ListaFacadeLocal;
import com.unileon.EJB.PersonaFacadeLocal;
import com.unileon.modelo.Lista;
import com.unileon.modelo.Persona;
import java.io.Serializable;
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
public class ListaController implements Serializable{
    @Inject
    private Lista lista;
    
    @Inject
    private Persona persona;
    private List<Persona> listaDePersonas;
    
    @EJB
    private PersonaFacadeLocal personaEJB;
    
    @EJB
    private ListaFacadeLocal listaEJB;
    
    private List<Lista> arrayDeListas;
    
    @PostConstruct
    public void init(){
        arrayDeListas= listaEJB.findAll();
        listaDePersonas = personaEJB.findAll();
    }

    public List<Persona> getListaDePersonas() {
        return listaDePersonas;
    }

    public void setListaDePersonas(List<Persona> listaDePersonas) {
        this.listaDePersonas = listaDePersonas;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Lista> getArrayDeListas() {
        return arrayDeListas;
    }

    public void setArrayDeListas(List<Lista> arrayDeListas) {
        this.arrayDeListas = arrayDeListas;
    }
    
    
    public void crearLista(){
        try {
             for(Persona p : listaDePersonas){
                if(p.getIdPersona()==persona.getIdPersona()){
                    persona=p;
                    break;
                }
            }
             
            lista.setPersona(persona);
            listaEJB.create(lista);
        } catch (Exception e) {
            
        }
    }
   
    
    
}
