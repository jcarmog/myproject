/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.model.entities;

import br.com.company.projetofitness.model.entities.chaves.PKPerfilMenu;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author jose
 */
@Entity
@Table(name = "tb_acessos")
public class Acessos implements Serializable{
    @EmbeddedId
    private PKPerfilMenu id;

    public Acessos() {
    }

    public Acessos(PKPerfilMenu id) {
        this.id = id;
    }
    
    

    /**
     * @return the id
     */
    public PKPerfilMenu getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(PKPerfilMenu id) {
        this.id = id;
    }
    
    
    
    
}
