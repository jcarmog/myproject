/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.model.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author josecarmo
 */
@Entity
@Table(name="tb_menu")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length=30)
    private String nome;
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<ItemMenu> itens = new LinkedList<ItemMenu>();
    private int ordemExibicao;

    public Menu() {
    }

        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the itens
     */
    public List<ItemMenu> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<ItemMenu> itens) {
        this.setItens(itens);
    }

    /**
     * @return the ordemExibicao
     */
    public int getOrdemExibicao() {
        return ordemExibicao;
    }

    /**
     * @param ordemExibicao the ordemExibicao to set
     */
    public void setOrdemExibicao(int ordemExibicao) {
        this.ordemExibicao = ordemExibicao;
    }
    
    
}
