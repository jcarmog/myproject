/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.model.entities.Aluno;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Query;
import org.hibernate.Criteria;

/**
 *
 * @author jose
 */
@Named
public class AlunoService extends AbstractService<Aluno> {

    @Override
    public Aluno salvar(Aluno novo) {
        em.getTransaction().begin();
        if (em.isOpen()) {
            em.persist(novo);
        }
        em.getTransaction().commit();
        return null;
    }

    @Override
    public Aluno alterar(Aluno altera) {
        Aluno a;
        try {
            em.getTransaction().begin();
            a= em.merge(altera);
            em.getTransaction().commit();
            return a;
        } catch (Exception e) {
            throw e;
        }
        
    }

    @Override
    public boolean excluir(Aluno exclui) {
        try {
            em.getTransaction().begin();
            em.remove(exclui);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Aluno> listarTodos() {
        return (List<Aluno>) em.createQuery("from Aluno").getResultList();
    }

    @Override
    public Aluno retornarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
