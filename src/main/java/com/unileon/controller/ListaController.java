/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ContenidoListaFacadeLocal;
import com.unileon.EJB.ListaFacadeLocal;
import com.unileon.EJB.PersonaFacadeLocal;
import com.unileon.modelo.ContenidoLista;
import com.unileon.modelo.Lista;
import com.unileon.modelo.Persona;
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
public class ListaController implements Serializable {

    @Inject
    private Lista lista;

    @Inject
    private Persona persona;
    private List<Persona> listaDePersonas;

    private List<ContenidoLista> contenidoLista;

    @EJB
    private ContenidoListaFacadeLocal contenidoListaEJB;

    @EJB
    private PersonaFacadeLocal personaEJB;

    @EJB
    private ListaFacadeLocal listaEJB;

    private List<Lista> arrayDeListas;

    private Usuario usuario;

    @Inject
    private ListasPersonalesController objetoLPC;

    @PostConstruct
    public void init() {
        arrayDeListas = listaEJB.findAll();
        listaDePersonas = personaEJB.findAll();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        persona = usuario.getPersona();
        contenidoLista = contenidoListaEJB.findAll();

    }

    public List<ContenidoLista> getContenidoLista() {
        return contenidoLista;
    }

    public void setContenidoLista(List<ContenidoLista> contenidoLista) {
        this.contenidoLista = contenidoLista;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void crearLista() {
        try {

            lista.setPersona(persona);
            listaEJB.create(lista);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se creo la lista"));

            objetoLPC.init();
        } catch (Exception e) {

        }
    }

    public void eliminarLista() {
        try {
            for (Lista l : arrayDeListas) {
                if (l.getIdLista() == lista.getIdLista()) {
                    lista = l;
                    break;
                }
            }

            for (ContenidoLista cl : contenidoLista) {
                if (cl.getLista().getIdLista() == lista.getIdLista()) {
                    contenidoListaEJB.remove(cl);
                }
            }

            listaEJB.remove(lista);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se elimino la lista"));
            arrayDeListas = listaEJB.findAll();
        } catch (Exception e) {
            System.out.println("Error al eliminar lista " + e.getMessage());
        }
    }

}
