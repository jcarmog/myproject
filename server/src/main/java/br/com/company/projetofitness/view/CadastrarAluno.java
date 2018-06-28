/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view;

import br.com.company.projetofitness.model.entities.Aluno;
import br.com.company.projetofitness.persistencia.Transactional;
import br.com.company.projetofitness.services.AlunoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
  
    private List<Aluno> alunos;

    @PostConstruct
    public void init() {
        alunos = alunoService.listarTodos();
        System.out.println("View carregada");
    }

    public void salvar() {
        try {
            if (aluno.getId() == 0) {
                alunoService.salvar(aluno);
            } else {
                alunoService.alterar(aluno);
            }

            addMensagem("Salvo com Sucesso.", FacesMessage.SEVERITY_INFO);
            limparCampos();

        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void excluir() {
        try {
            alunoService.excluir(aluno);
            addMensagem("Excluido com Sucesso.", FacesMessage.SEVERITY_INFO);
            limparCampos();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limparCampos() {
        aluno = new Aluno();
    }

    public void onRowSelect(SelectEvent event) {
        aluno = (Aluno) event.getObject();
    }

    public void onRowSelect(UnselectEvent event) {
        aluno = (Aluno) event.getObject();
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

    public List<Aluno> getAlunos() {
        return alunoService.listarTodos();
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

}
