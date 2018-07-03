/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.cadastros;

import br.com.company.projetofitness.model.entities.Aluno;
import br.com.company.projetofitness.model.entities.Cidade;
import br.com.company.projetofitness.model.entities.Professor;
import br.com.company.projetofitness.model.entities.UnidadeFederacao;
import br.com.company.projetofitness.persistencia.Transactional;
import br.com.company.projetofitness.services.PessoaService;
import br.com.company.projetofitness.services.CidadeService;
import br.com.company.projetofitness.view.PrincipalView;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class CadastrarProfessor extends PrincipalView {

    @Inject
    private PessoaService pessoaService;
    @Inject
    private CidadeService cidadeService;
    @Inject
    private Professor professor;
    private Cidade cidade;

    private UnidadeFederacao uf;
    private Professor selectedProfessor;
    private List<Professor> professores;
    private List<Cidade> cidades;
    private List<UnidadeFederacao> ufs;

    private boolean skip;

    @PostConstruct
    public void init() {
        setNomeFuncao("Cadastrar Professor");
        setProfessores(pessoaService.listarProfessores());
        System.out.println("View carregada");
        //limparCampos();
    }

    @Transactional
    public void salvar() {
        try {
            if (professor.getId() == 0) {
                pessoaService.salvar(professor);
            } else {
                pessoaService.alterar(professor);
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
            pessoaService.excluir(getProfessor());
            addMensagem("Excluido com Sucesso.", FacesMessage.SEVERITY_INFO);
            limparCampos();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void buscarMunicipiosUF(){
        if(getProfessor().getUf()!=null){
            setCidades(cidadeService.listarTodosPorUF(getProfessor().getUf().getId()));
        }
    }

    public void limparCampos() {
        setProfessor(new Professor());
    }

    public void onRowSelect(SelectEvent event) {
        setProfessor((Professor) event.getObject());
        if(getProfessor().getUf()!=null){
            buscarMunicipiosUF();
        }
    }

    public void onRowUnselect(UnselectEvent event) {
        setProfessor((Professor) event.getObject());
        setCidades(null);
    }

    public String onFlowProcess(FlowEvent event) {
        if (isSkip()) {
            setSkip(false);   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    /**
     * @param pessoaService the pessoaService to set
     */
    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    /**
     * @param cidadeService the cidadeService to set
     */
    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    

    /**
     * @return the cidade
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
     * @return the selectedProfessor
     */
    public Professor getSelectedProfessor() {
        return selectedProfessor;
    }

    /**
     * @param selectedProfessor the selectedProfessor to set
     */
    public void setSelectedProfessor(Professor selectedProfessor) {
        this.selectedProfessor = selectedProfessor;
    }

    /**
     * @return the professores
     */
    public List<Professor> getProfessores() {
        return pessoaService.listarProfessores();
    }

    /**
     * @param professores the professores to set
     */
    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    /**
     * @return the cidades
     */
    public List<Cidade> getCidades() {
        return cidades;
    }

    /**
     * @param cidades the cidades to set
     */
    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    /**
     * @return the ufs
     */
    public List<UnidadeFederacao> getUfs() {
        return cidadeService.listarTodosEstados();
    }

    /**
     * @param ufs the ufs to set
     */
    public void setUfs(List<UnidadeFederacao> ufs) {
        this.ufs = ufs;
    }

    /**
     * @return the skip
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     * @param skip the skip to set
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

   

}
