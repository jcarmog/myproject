/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.model.entities.Aluno;
import br.com.company.projetofitness.util.Util;
import java.util.List;
import javax.inject.Named;

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
            novo.setCep(Util.removerMascara(novo.getCep()));
            novo.setTelefone(Util.removerMascara(novo.getTelefone()));
            novo.setCelular(Util.removerMascara(novo.getCelular()));
            novo.setCpf(Util.removerMascara(novo.getCpf()));
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
            altera.setCep(Util.removerMascara(altera.getCep()));
            altera.setTelefone(Util.removerMascara(altera.getTelefone()));
            altera.setCelular(Util.removerMascara(altera.getCelular()));
            altera.setCpf(Util.removerMascara(altera.getCpf()));
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
            em.remove(em.getReference(Aluno.class, exclui.getId()));
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
