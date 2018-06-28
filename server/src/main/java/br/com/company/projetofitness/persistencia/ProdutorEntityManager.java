/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.persistencia;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author josecarmo
 */
public class ProdutorEntityManager {

    @Produces
    @ApplicationScoped
    @Geral
    public EntityManagerFactory criarFactory() {
        return Persistence.createEntityManagerFactory("dbstrongerPU");
    }
/*
    @Produces
    @ApplicationScoped @Infra
    public EntityManagerFactory criarFactoryInfra() {
        return Persistence.createEntityManagerFactory("infra.pu");
    }
*/
    @Produces
    @Geral
    @RequestScoped
    public EntityManager criarEntityManager(@Geral EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
    /*
    @Produces
    @RequestScoped @Infra
    public EntityManager criarEntityManagerInfra(@Infra EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
*/

    public void fecharEntityManager(@Disposes @Geral EntityManager em) {
        em.close();
    }

}
