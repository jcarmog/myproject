/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.model.entities.Menu;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jose
 */
@Named
public class MenuService extends AbstractService<Menu> {

    @Override
    public List<Menu> listarTodos() {
        Query q = em.createQuery("FROM Menu");
        List<Menu> menus = q.getResultList();
        return menus;
    }

    public List<Menu> retornaTodosOrderBy() throws Exception {

        CriteriaQuery<Menu> criteriaQuery = em.getCriteriaBuilder().createQuery(Menu.class);
        Root<Menu> from = criteriaQuery.from(Menu.class);
        CriteriaQuery<Menu> select = criteriaQuery.select(from);
        criteriaQuery.orderBy(em.getCriteriaBuilder().asc(from.get("ordemExibicao")));
        List<Menu> menus = em.createQuery(criteriaQuery).getResultList();
        return menus;
    }

    @Override
    public Menu salvar(Menu novo) {
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public boolean excluir(Menu delete) {
       try {
            em.getTransaction().begin();
            em.remove(em.getReference(Menu.class, delete.getId()));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Menu alterar(Menu altera) {
        em.getTransaction().begin();
        em.merge(altera);
        em.getTransaction().begin();
        return null;
    }

    @Override
    public Menu retornarPorId(int id) {
        return em.find(Menu.class, id);
    }

}
