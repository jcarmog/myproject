/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.model.entities.chaves;


import br.com.company.projetofitness.model.entities.ItemMenu;
import br.com.company.projetofitness.model.entities.Perfil;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author josecarmo
 */
@Embeddable
public class PKPerfilMenu implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil", updatable = true)
    @ForeignKey(name="fk_acesso_perfil")
    private Perfil perfil;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu", updatable = true)
    @Cascade(CascadeType.EVICT)
    @ForeignKey(name="fk_acesso_menu")
    private ItemMenu itemMenu;

    public PKPerfilMenu() {
    }

    public PKPerfilMenu(Perfil perfil, ItemMenu itemMenu) {
        this.perfil = perfil;
        this.itemMenu = itemMenu;
    }
    

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the itemMenu
     */
    public ItemMenu getItemMenu() {
        return itemMenu;
    }

    /**
     * @param itemMenu the itemMenu to set
     */
    public void setItemMenu(ItemMenu itemMenu) {
        this.itemMenu = itemMenu;
    }
    
    
}
