/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author jcarmo
 */
@Entity
@Table(name="tb_cidade")
public class Cidade implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "uf",referencedColumnName = "id")
    @ForeignKey(name="fk_cidade_uf")
    private UnidadeFederacao uf;
    @Column(name = "cod_munic_ibge",length = 5)
    private String codMunicipio;
    @Column(name = "nome",length = 50)
    private String nome;

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
     * @return the uf
     */
    public UnidadeFederacao getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(UnidadeFederacao uf) {
        this.uf = uf;
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
     * @return the codMunicipio
     */
    public String getCodMunicipio() {
        return codMunicipio;
    }

    /**
     * @param codMunicipio the codMunicipio to set
     */
    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }
        
}
