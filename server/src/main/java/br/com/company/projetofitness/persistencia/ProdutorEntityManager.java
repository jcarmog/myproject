/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.persistencia;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author josecarmo
 */
@ApplicationScoped
public class ProdutorEntityManager implements Serializable {
    
 
    @PersistenceUnit
    private EntityManagerFactory factory;
 
    @RequestScoped
    @Produces
    public EntityManager createEntityManager() {
        return factory.createEntityManager();
    }
 
    public void closeEntityManager(@Disposes EntityManager manager) {
        if (manager.isOpen()) {
            manager.close();
        }
    }
}
