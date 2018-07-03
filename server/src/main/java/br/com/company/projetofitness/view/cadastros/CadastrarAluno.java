/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.cadastros;

import br.com.company.projetofitness.model.entities.Aluno;
import br.com.company.projetofitness.model.entities.Cidade;
import br.com.company.projetofitness.model.entities.UnidadeFederacao;
import br.com.company.projetofitness.persistencia.Transactional;
import br.com.company.projetofitness.services.CidadeService;
import br.com.company.projetofitness.services.PessoaService;
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
public class CadastrarAluno extends PrincipalView {

    @Inject
    private PessoaService alunoService;
    @Inject
    private CidadeService cidadeService;
    @Inject
    private Aluno aluno;
    private Cidade cidade;

    private UnidadeFederacao uf;
    private Aluno selectedAluno;
    private List<Aluno> alunos;
    private List<Cidade> cidades;
    private List<UnidadeFederacao> ufs;

    private boolean skip;

    @PostConstruct
    public void init() {
        setNomeFuncao("Cadastrar Aluno");
        setAlunos(alunoService.listarAlunos());
        System.out.println("View carregada");
        //limparCampos();
    }

    @Transactional
    public void salvar() {
        try {
            if (getAluno().getId() == 0) {
                alunoService.salvar(getAluno());
            } else {
                alunoService.alterar(getAluno());
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
            alunoService.excluir(getAluno());
            addMensagem("Excluido com Sucesso.", FacesMessage.SEVERITY_INFO);
            limparCampos();
        } catch (Exception e) {
            throw e;
        }
    }

    public void buscarMunicipiosUF() {
        if (aluno.getUf() != null) {
            cidades = cidadeService.listarTodosPorUF(aluno.getUf().getId());
        }
    }

    public void limparCampos() {
        setAluno(new Aluno());
    }

    public void onRowSelect(SelectEvent event) {
        setAluno((Aluno) event.getObject());
        if (getAluno().getUf() != null) {
            buscarMunicipiosUF();
        }
    }

    public void onRowUnselect(UnselectEvent event) {
        setAluno((Aluno) event.getObject());
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

    public void setAlunoService(PessoaService alunoService) {
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
        return alunoService.listarAlunos();
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Aluno getSelectedAluno() {
        return selectedAluno;
    }

    public void setSelectedAluno(Aluno selectedAluno) {
        this.selectedAluno = selectedAluno;
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

    /**
     * @param cidadeService the cidadeService to set
     */
    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
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

}
