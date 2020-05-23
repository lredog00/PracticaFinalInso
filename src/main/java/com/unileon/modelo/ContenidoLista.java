/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author LuisAngel
 */
@Entity
@Table(name="contenidoListas")
public class ContenidoLista implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int IdContenidoLista;
    
    @OneToOne
    @JoinColumn(name="idCanciones")
    private Cancion cancion;
    
    @ManyToOne
    @JoinColumn(name="idLista")
    private Lista lista;

    public int getIdContenidoLista() {
        return IdContenidoLista;
    }

    public void setIdContenidoLista(int IdContenidoLista) {
        this.IdContenidoLista = IdContenidoLista;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.IdContenidoLista;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContenidoLista other = (ContenidoLista) obj;
        if (this.IdContenidoLista != other.IdContenidoLista) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "ContenidoLista{" + "IdContenidoLista=" + IdContenidoLista + ", cancion=" + cancion + ", lista=" + lista + '}';
    }
    
    
    
}
