/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author LuisAngel
 */
@Entity
@Table(name="listas")
public class Lista implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int IdLista;
    
    @Column(name="nombreLista")
    private String nombreLista;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Temporal(TemporalType.DATE)
    @Column(name="fecha", insertable = false)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name="idPersona")
    private Persona persona;
    
    @ManyToOne
    @JoinColumn(name="idCancion")
    private Cancion cancion;

    public int getIdLista() {
        return IdLista;
    }

    public void setIdLista(int IdLista) {
        this.IdLista = IdLista;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.IdLista;
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
        final Lista other = (Lista) obj;
        if (this.IdLista != other.IdLista) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lista{" + "IdLista=" + IdLista + ", nombreLista=" + nombreLista + ", descripcion=" + descripcion + ", fecha=" + fecha + ", persona=" + persona + ", cancion=" + cancion + '}';
    }
    
}
