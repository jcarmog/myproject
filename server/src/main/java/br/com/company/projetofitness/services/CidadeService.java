/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.model.entities.Cidade;
import br.com.company.projetofitness.model.entities.UnidadeFederacao;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 *
 * @author jose
 */
@Named
public class CidadeService extends AbstractService<Cidade> {

    @Override
    public Cidade salvar(Cidade novo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cidade alterar(Cidade altera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(Cidade exclui) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cidade> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cidade retornarPorId(int id) {
        return em.find(Cidade.class, id);
    }

    public List<Cidade> listarTodosPorUF(int idUf) {
        Query q = em.createNamedQuery("listarCidadesPorEstado");
        q.setParameter("id_uf", idUf);
        return (List<Cidade>)q.getResultList();
    }

    @SuppressWarnings("JPQLValidation")
    public List<UnidadeFederacao> listarTodosEstados() {
        return (List<UnidadeFederacao>) em.createQuery("from UnidadeFederacao order by nome").getResultList();
    }
     public UnidadeFederacao retornarUFPorId(int id) {
         return em.find(UnidadeFederacao.class, id);
    }

}
