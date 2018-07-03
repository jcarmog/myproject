/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.cadastros;

import br.com.company.projetofitness.model.entities.Exercicio;
import br.com.company.projetofitness.services.ExercicioService;
import br.com.company.projetofitness.view.PrincipalView;
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
public class CadastrarExercicios extends PrincipalView {

    @Inject
    private ExercicioService exercicioService;
    @Inject
    private Exercicio exercicio;

    private Exercicio selectedExercicio;

    public CadastrarExercicios() {
        setNomeFuncao("Cadastro de Exercício");
    }

    
    @Override
    @PostConstruct
    public void init() {
        setNomeFuncao("Cadastro de Exercício");
    }

    public void salvar() {
        try {
            if (exercicio.getId() == 0) {
                exercicioService.salvar(exercicio);
            } else {
                exercicioService.alterar(exercicio);
            }

            addMensagem("Salvo com Sucesso.", FacesMessage.SEVERITY_INFO);
            limparCampos();

        } catch (Exception e) {
            throw e;
        }
    }

    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Exercicio> getExercicios() {
        return getExercicioService().listarTodos();
    }

    /**
     * @return the exercicioService
     */
    public ExercicioService getExercicioService() {
        return exercicioService;
    }

    /**
     * @param exercicioService the exercicioService to set
     */
    public void setExercicioService(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    /**
     * @return the exercicio
     */
    public Exercicio getExercicio() {
        return exercicio;
    }

    /**
     * @param exercicio the exercicio to set
     */
    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    /**
     * @return the selectedExercicio
     */
    public Exercicio getSelectedExercicio() {
        return selectedExercicio;
    }

    /**
     * @param selectedExercicio the selectedExercicio to set
     */
    public void setSelectedExercicio(Exercicio selectedExercicio) {
        this.selectedExercicio = selectedExercicio;
    }

    @Override
    public void limparCampos() {
        exercicio = new Exercicio();
    }

    @Override
    public void onRowSelect(SelectEvent event) {
        exercicio = (Exercicio)event.getObject();
    }

    @Override
    public void onRowUnselect(UnselectEvent event) {
        exercicio = (Exercicio)event.getObject();
    }

}
