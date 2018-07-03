/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.services;

import br.com.company.projetofitness.model.entities.ItemMenu;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author jose
 */
@Named
public class ItemMenuService extends AbstractService<ItemMenu> {

    @Override
    public List<ItemMenu> listarTodos() {
        Query q = em.createQuery("FROM ItemMenu");
        List<ItemMenu> menus = q.getResultList();
        return menus;
    }

    @SuppressWarnings("JPQLValidation")
    public List<ItemMenu> retornaTodosPorMenu(int menu) throws Exception {
        List<ItemMenu> itens = new ArrayList<>();
        itens = em.createQuery(
                "FROM ItemMenu WHERE menu.id = :custName")
                .setParameter("custName", menu)
                .getResultList();

        return itens;
    }
    @Override
    public ItemMenu salvar(ItemMenu novo){
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        return null;
    }

    public ItemMenu alterar(ItemMenu novo)  {
        em.getTransaction().begin();
        em.merge(novo);
        em.getTransaction().begin();
        return null;
    }

    @Override
    public boolean excluir(ItemMenu delete)  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public ItemMenu retornarPorId(int id) {
        return em.find(ItemMenu.class, id);
    }

}
