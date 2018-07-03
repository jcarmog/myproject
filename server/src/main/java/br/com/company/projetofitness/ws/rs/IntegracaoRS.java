/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.ws.rs;

import br.com.company.projetofitness.model.entities.Aluno;
import br.com.company.projetofitness.model.entities.Professor;
import br.com.company.projetofitness.services.PessoaService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jcarmo
 */
@Path("/integracao")
public class IntegracaoRS {
    
    @Inject
    private PessoaService pessoaService;
    
    @GET
    @Path("/aluno/{idAluno}")
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno retornarAlunoPeloId(@PathParam("idAluno") int idAluno){
        return pessoaService.retornarAlunoPorId(idAluno);
    }
    
    @GET
    @Path("/professor/{idProfessor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor retornarProfessorPeloId(@PathParam("idProfessor") int idProfessor){
        return pessoaService.retornarProfessorPorId(idProfessor);
    }
    
}
