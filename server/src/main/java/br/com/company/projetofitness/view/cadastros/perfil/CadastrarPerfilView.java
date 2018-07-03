/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.cadastros.perfil;

import br.com.company.projetofitness.model.entities.Perfil;
import br.com.company.projetofitness.services.PerfilService;
import br.com.company.projetofitness.view.PrincipalView;
import br.com.company.projetofitness.view.datamodel.PerfilDataModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author josecarmo
 */
@ManagedBean
@ViewScoped
public class CadastrarPerfilView extends PrincipalView {

    @Inject
    private PerfilService perfilService;
    private Perfil perfil;
    private Perfil selectedPerfil;
    private PerfilDataModel dataModel;

    @PostConstruct
    public void init() {
        List<Perfil> perfis = new ArrayList<>();
        try {
            perfis = perfilService.listarTodos();
        } catch (Exception ex) {
            Logger.getLogger(CadastrarPerfilView.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataModel = new PerfilDataModel(perfis);
        perfil = new Perfil();
    }

    public void salvar() {
        try {
            if (perfil != null && perfil.getId() == 0) {
                perfilService.salvar(perfil);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Salvo com Sucesso."));
            } else {
                perfilService.alterar(perfil);
            }
            init();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement st : e.getStackTrace()) {
                sb.append(st.toString()).append("\n");

            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), sb.toString()));
        }
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the dataModel
     */
    public PerfilDataModel getDataModel() {
        return dataModel;
    }

    /**
     * @param dataModel the dataModel to set
     */
    public void setDataModel(PerfilDataModel dataModel) {
        this.dataModel = dataModel;
    }

    /**
     * @return the selectedPerfil
     */
    public Perfil getSelectedPerfil() {
        return selectedPerfil;
    }

    /**
     * @param selectedPerfil the selectedPerfil to set
     */
    public void setSelectedPerfil(Perfil selectedPerfil) {
        this.selectedPerfil = selectedPerfil;
    }

    @Override
    public void limparCampos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onRowSelect(SelectEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onRowUnselect(UnselectEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
