/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.model.entities.Acessos;
import br.com.company.projetofitness.model.entities.Perfil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jose
 */
public class PerfilService extends AbstractService<Perfil> {

    @Override
    public Perfil salvar(Perfil novo) {
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        return null;
    }

    public Acessos salvarAcesso(Acessos novo) throws Exception {
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        return null;
    }

    public Acessos alterarAcesso(Acessos acesso) throws Exception {
        em.merge(acesso);
        return null;
    }

    public List<Acessos> ListaAcessoPerfil(Perfil perfil) throws Exception {
        Criteria c = ((Session) em.getDelegate()).createCriteria(Acessos.class);
        Disjunction dis = Restrictions.disjunction();
        dis.add(Restrictions.eq("id.perfil.id", perfil.getId()));
        c.add(dis);

        return c.list();
    }

    @Override
    public boolean excluir(Perfil delete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Perfil alterar(Perfil perfil) {
        em.merge(perfil);
        return null;
    }

    @Override
    public List<Perfil> listarTodos() {
        return (List<Perfil>) em.createNamedQuery("listarTodosPerfis").getResultList();
    }

    @Override
    public Perfil retornarPorId(int id) {
        return em.find(Perfil.class, id);
    }

    public void limparAcessosPerfil(int id) {
        em.getTransaction().begin();
        em.createNamedQuery("limparAcessosPerfil").executeUpdate();
        em.getTransaction().commit();
    }

}
