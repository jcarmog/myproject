/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.cadastros.perfil;

import br.com.company.projetofitness.model.entities.Acessos;
import br.com.company.projetofitness.model.entities.ItemMenu;
import br.com.company.projetofitness.model.entities.Perfil;
import br.com.company.projetofitness.model.entities.chaves.PKPerfilMenu;
import br.com.company.projetofitness.services.ItemMenuService;
import br.com.company.projetofitness.services.PerfilService;
import br.com.company.projetofitness.view.PrincipalView;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author josecarmo
 */
@ManagedBean
@ViewScoped
public class AssociarMenuPerfilView extends PrincipalView {

    @Inject
    private PerfilService perfilService;
    @Inject
    private ItemMenuService itemMenuService;

    private List<Perfil> perfis;
    private Perfil selectedPerfil;
    private List<ItemMenu> menus;
    private DualListModel<ItemMenu> listModel;

    @PostConstruct
    public void init() {

        try {
            inicializarCampos();
        } catch (Exception ex) {
            Logger.getLogger(AssociarMenuPerfilView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvar() {
        try {
            perfilService.limparAcessosPerfil(selectedPerfil.getId());
            List<ItemMenu> itens = getListModel().getTarget();
            Object[] valores = itens.toArray();
            PKPerfilMenu pkpm = null;
            for (Object s : valores) {
                ItemMenu menu = itemMenuService.retornarPorId(Integer.parseInt(s.toString()));
                pkpm = new PKPerfilMenu(selectedPerfil, menu);

                Acessos a = new Acessos(pkpm);
                perfilService.salvarAcesso(a);
            }
            inicializarCampos();
            addMensagem("Acessos configurados com sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void transfer() {
        getListModel().getTarget();
    }

    public void onChangeMenu() throws Exception {
        List<ItemMenu> source = getListModel().getSource();

        List<Acessos> as = perfilService.ListaAcessoPerfil(selectedPerfil);
        List<ItemMenu> target = new ArrayList<ItemMenu>();
        List<ItemMenu> source2 = new ArrayList<ItemMenu>();
        for (Acessos a : as) {
            target.add(a.getId().getItemMenu());
        }

        for (ItemMenu menu : getListModel().getSource()) {

            if (!target.contains(menu)) {
                source2.add(menu);
            }

        }
        setListModel(new DualListModel<ItemMenu>(source2, target));

    }

    /**
     * @return the listModel
     */
    public DualListModel<ItemMenu> getListModel() {
        return listModel;
    }

    /**
     * @return the perfis
     */
    public List<Perfil> getPerfis() {
        return perfis;
    }

    /**
     * @param perfis the perfis to set
     */
    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
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

    /**
     * @return the menus
     */
    public List<ItemMenu> getMenus() {
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(List<ItemMenu> menus) {
        this.menus = menus;
    }

    /**
     * @param listModel the listModel to set
     */
    public void setListModel(DualListModel<ItemMenu> listModel) {
        this.listModel = listModel;
    }

    private void inicializarCampos() throws Exception {
        perfis = perfilService.listarTodos();
        List<ItemMenu> itens = itemMenuService.listarTodos();
        List<ItemMenu> target = new ArrayList<ItemMenu>();
        setListModel(new DualListModel<ItemMenu>(itens, target));
        menus = new ArrayList<ItemMenu>();
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
