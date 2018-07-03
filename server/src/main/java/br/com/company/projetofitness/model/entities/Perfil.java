/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author jose
 */
@Entity
@Table(name="tb_perfil")
@NamedQueries({
    @NamedQuery(
            name = "listarTodosPerfis",
            query = "SELECT c FROM Perfil c order by c.descricao"
    ),
        @NamedQuery(
            name = "retornarPerfilPorId",
            query = "SELECT c FROM Perfil c where c.id = :idPerfil"
    )
        ,
        @NamedQuery(
            name = "limparAcessosPerfil",
            query = "delete from Acessos where id.perfil.id = :idPerfil"
    )
})
public class Perfil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    @OneToMany(mappedBy = "id.perfil")
    private List<Acessos> acessos;
    @Transient
    private List<ItemMenu> menus;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the menus
     */
    public List<ItemMenu> getMenus() {
        for(Acessos a : getAcessos()){
            menus.add(a.getId().getItemMenu());
        }
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(List<ItemMenu> menus) {
        this.menus = menus;
    }

    /**
     * @return the acessos
     */
    public List<Acessos> getAcessos() {
        return acessos;
    }

    /**
     * @param acessos the acessos to set
     */
    public void setAcessos(List<Acessos> acessos) {
        this.acessos = acessos;
    }
}
