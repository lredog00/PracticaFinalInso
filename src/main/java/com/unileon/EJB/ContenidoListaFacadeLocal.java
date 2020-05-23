/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.ContenidoLista;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LuisAngel
 */
@Local
public interface ContenidoListaFacadeLocal {

    void create(ContenidoLista contenidoLista);

    void edit(ContenidoLista contenidoLista);

    void remove(ContenidoLista contenidoLista);

    ContenidoLista find(Object id);

    List<ContenidoLista> findAll();

    List<ContenidoLista> findRange(int[] range);

    int count();
    
}
