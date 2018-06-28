/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.persistencia.Transactional;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author jose
 */
@Named
public abstract class AbstractService<T> {

    @Inject
    EntityManager em;

    @Transactional
    public abstract T salvar(T novo);

    @Transactional
    public abstract T alterar(T altera);

    @Transactional
    public abstract boolean excluir(T exclui);

    public abstract List<T> listarTodos();

    public abstract T retornarPorId(int id);

}
