/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.model.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author jose
 */
@Entity
@DiscriminatorValue(value = "2")
@NamedQueries({
    @NamedQuery(
            name = "listarTodosProfessores",
            query = "SELECT c FROM Professor c order by c.nome"
    )
    ,
        @NamedQuery(
            name = "retornarProfessorPorId",
            query = "SELECT c FROM Professor c where c.id = :idProfessor"
    )
})
public class Professor extends Pessoa implements Serializable{
    
    private String matriculaCref;
    
    private Date dataValidadeCref;
    
    @Transient
    public boolean isCrefValido(){
        if(Calendar.getInstance().after(dataValidadeCref)){
            return false;
        }
        return true;
    }

    /**
     * @return the matriculaCref
     */
    @Column(name="matricula_cref")
    public String getMatriculaCref() {
        return matriculaCref;
    }

    /**
     * @param matriculaCref the matriculaCref to set
     */
    public void setMatriculaCref(String matriculaCref) {
        this.matriculaCref = matriculaCref;
    }

    /**
     * @return the dataValidadeCref
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "data_validade_cref")
    public Date getDataValidadeCref() {
        return dataValidadeCref;
    }

    /**
     * @param dataValidadeCref the dataValidadeCref to set
     */
    public void setDataValidadeCref(Date dataValidadeCref) {
        this.dataValidadeCref = dataValidadeCref;
    }
    
}
