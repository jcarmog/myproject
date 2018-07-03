/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.model.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author jose
 */
@Entity
@DiscriminatorValue(value = "1")
@NamedQueries({
    @NamedQuery(
            name = "listarTodosAlunos",
            query = "SELECT c FROM Aluno c order by c.nome"
    )
    ,
        @NamedQuery(
            name = "retornarAlunoPorId",
            query = "SELECT c FROM Aluno c where c.id = :idAluno"
    )
})
public class Aluno extends Pessoa implements Serializable {

}
