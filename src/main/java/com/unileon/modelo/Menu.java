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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author LuisAngel
 */
@Entity
@Table(name="menus")
public class Menu implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idMenu;
    
    @Column (name = "nombre")
    private String nombre;
    
    @Column (name = "tipo")
    private String tipo;
    
    @Column (name = "estado")
    private boolean estado;
    
    @Column (name = "url")
    private String url;
    
    @JoinColumn(name="idRol")
    @ManyToOne
    private Rol rol;
    
    @JoinColumn(name="idMenu_Menu")
    @ManyToOne
    private Menu idMenu_Menu;
    
    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Menu getIdMenu_Menu() {
        return idMenu_Menu;
    }

    public void setIdMenu_Menu(Menu idMenu_Menu) {
        this.idMenu_Menu = idMenu_Menu;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.idMenu;
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
        final Menu other = (Menu) obj;
        if (this.idMenu != other.idMenu) {
            return false;
        }
        return true;
    }
    
    
    
    
    @Override
    public String toString() {
        return "Menu{" + "idMenu=" + idMenu + ", nombre=" + nombre + ", tipo=" + tipo + ", estado=" + estado + ", url=" + url + ", rol=" + rol + ", idMenu_Menu=" + idMenu_Menu + '}';
    }
    
    

    
    
    
    
    
}
