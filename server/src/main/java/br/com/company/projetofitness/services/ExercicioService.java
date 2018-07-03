/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.model.entities.Exercicio;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@Named
public class ExercicioService extends AbstractService<Exercicio> {

    @Override
    public Exercicio salvar(Exercicio novo) {
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        return novo;
    }

    @Override
    public Exercicio alterar(Exercicio altera) {
         try {
            
            em.getTransaction().begin();
            altera= em.merge(altera);
            em.getTransaction().commit();
            
            return altera;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean excluir(Exercicio exclui) {
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(Exercicio.class, exclui.getId()));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Exercicio> listarTodos() {
        return (List<Exercicio>) em.createNamedQuery("listarTodosExercicios").getResultList();
    }

    @Override
    public Exercicio retornarPorId(int id) {
        return em.find(Exercicio.class, id);
    }

}
