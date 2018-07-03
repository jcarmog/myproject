/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.model.entities.Aluno;
import br.com.company.projetofitness.model.entities.Pessoa;
import br.com.company.projetofitness.model.entities.Professor;
import br.com.company.projetofitness.util.Util;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@Named
public class PessoaService extends AbstractService<Pessoa> {

    @Override
    public Pessoa salvar(Pessoa novo) {

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
    public Pessoa alterar(Pessoa altera) {
        Pessoa a;
        try {

            em.getTransaction().begin();
            altera.setCep(Util.removerMascara(altera.getCep()));
            altera.setTelefone(Util.removerMascara(altera.getTelefone()));
            altera.setCelular(Util.removerMascara(altera.getCelular()));
            altera.setCpf(Util.removerMascara(altera.getCpf()));
            a = em.merge(altera);
            em.getTransaction().commit();

            return a;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public boolean excluir(Pessoa exclui) {
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(Pessoa.class, exclui.getId()));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Pessoa> listarTodos() {
        return (List<Pessoa>) em.createQuery("from Pessoa").getResultList();
    }

    public List<Aluno> listarAlunos() {
        return (List<Aluno>) em.createNamedQuery("listarTodosAlunos").getResultList();
    }

    public Aluno retornarAlunoPorId(int id) {
        return (Aluno) em.find(Aluno.class, id);
    }

    public List<Professor> listarProfessores() {
        return (List<Professor>) em.createNamedQuery("listarTodosProfessores").getResultList();
    }

    public Professor retornarProfessorPorId(int id) {
        return (Professor) em.find(Professor.class, id);
    }

    @Override
    public Pessoa retornarPorId(int id) {
        return em.find(Pessoa.class, id);
    }

}
