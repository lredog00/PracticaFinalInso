/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LuisAngel
 */
@Entity
@Table(name="canciones")

public class Cancion implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int IdCancion;
    
    @Column(name="nombre")
    private String nombre;

    @Column(name="autor")
    private String autor;
    
    @Column(name="genero")
    private String genero;
     
    @Column(name="descripcion")
    private String descripcion;

    public void setIdCancion(int IdCancion) {
        this.IdCancion = IdCancion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCancion() {
        return IdCancion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.IdCancion;
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
        final Cancion other = (Cancion) obj;
        if (this.IdCancion != other.IdCancion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cancion{" + "IdCancion=" + IdCancion + ", nombre=" + nombre + ", autor=" + autor + ", genero=" + genero + ", descripcion=" + descripcion + '}';
    }

    
    
}
