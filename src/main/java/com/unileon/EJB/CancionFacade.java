/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Cancion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LuisAngel
 */
@Stateless
public class CancionFacade extends AbstractFacade<Cancion> implements CancionFacadeLocal {

    @PersistenceContext(unitName = "practicainso2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CancionFacade() {
        super(Cancion.class);
    }
    
}
