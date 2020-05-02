/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Lista;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LuisAngel
 */
@Local
public interface ListaFacadeLocal {

    void create(Lista lista);

    void edit(Lista lista);

    void remove(Lista lista);

    Lista find(Object id);

    List<Lista> findAll();

    List<Lista> findRange(int[] range);

    int count();
    
}
