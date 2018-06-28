/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.model.entities.Aluno;
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
            em.persist(novo);
        }
        em.getTransaction().commit();
        return null;
    }
    
    @Override
    public Aluno alterar(Aluno altera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean excluir(Aluno exclui) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Aluno listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Aluno retornarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
