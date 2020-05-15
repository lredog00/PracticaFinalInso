/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Menu;
import com.unileon.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LuisAngel
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "practicainso2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    @Override
    public List<Menu> obtenerMenusUsuario(Usuario us){
        
        String consulta = "FROM Menu m WHERE m.rol.idRol=:param";
        Query query = em.createQuery(consulta);
        query.setParameter("param", us.getRol().getIdRol());
        
        List<Menu> resultado = query.getResultList();
        if(resultado.isEmpty()){
            //System.out.println("Este usuario no tiene menus");
             return null;
        }else{
            /*System.out.println("Se han enviado menus para el usuario");
            for(Menu m : resultado){
                System.out.println("\t Nombre menu: "+m.getNombre());
            }*/
            return resultado; 
        }
    }
    
}
