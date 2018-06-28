/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view;

import br.com.company.projetofitness.model.entities.Aluno;
import br.com.company.projetofitness.persistencia.Transactional;
import br.com.company.projetofitness.services.AlunoService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class CadastrarAluno extends PrincipalView {

    @Inject
    private AlunoService alunoService;

    @Inject
    private Aluno aluno;

    @PostConstruct
    public void init() {
        System.out.println("View carregada");
    }

    public void salvar() {
        alunoService.salvar(aluno);
    }

    /**
     * @param alunoService the alunoService to set
     */
    public void setAlunoService(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

}
