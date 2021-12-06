/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;


import com.unileon.EJB.MenuFacadeLocal;
import com.unileon.modelo.Menu;
import com.unileon.modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;


/**
 *
 * @author LuisAngel
 */

@Named
@SessionScoped
public class MenuController implements Serializable{
    
    @EJB
    private MenuFacadeLocal menuEJB;
     
    private MenuModel modelo = new DefaultMenuModel();
    private List<Menu> listaMenusDisponibles;
    private Usuario usuario;
    
   
    
    @PostConstruct
    public void init(){
        
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaMenusDisponibles = menuEJB.obtenerMenusUsuario(usuario);
        
        obtenerMenu();
    }
      
    public MenuModel obtenerMenu(){
        
        
        for(Iterator<Menu> iterator = listaMenusDisponibles.iterator(); iterator.hasNext();){
            Menu menuActual = iterator.next();
            if(menuActual.getTipo().equals("I")){
                DefaultMenuItem item = new DefaultMenuItem(menuActual.getNombre());
                if(menuActual.getUrl()!=null){
                    item.setUrl(menuActual.getUrl());                   
                }else{
                    item.setUrl("/");                   
                }
                 if(menuActual.getIdMenu_Menu() == null){
                     modelo.addElement(item);
                }
            }
            if(menuActual.getTipo().equals("S")){
                DefaultSubMenu subMenu = new DefaultSubMenu(menuActual.getNombre());
                
                
                //Buscar los hijos
                for(Menu m : listaMenusDisponibles){
                    //Recorremos toda la lista 
                    if(m.getIdMenu_Menu()!=null){
                        //TIENE PADRE
                        if(m.getIdMenu_Menu().getIdMenu() == menuActual.getIdMenu()){
                            //Ademas el id de ese menu corresponde con el id actual ==> ES HIJO
                            DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                            
                            if(m.getUrl()!=null){
                                item.setUrl(m.getUrl());
                                 
                            } else {
                                item.setUrl("/");
                               
                            }
                            subMenu.addElement(item);
                        }
                    }
                }
                modelo.addElement(subMenu);
            }         
        }
        return modelo;        
    }
    /*public String destruirSesion(){
        String dir="./../../index.xhtml?faces-redirect=true";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return dir;       
        
    }*/
    
    public void destruirSesion() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("./../../");
    }
    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }
       
}
    
    
    

