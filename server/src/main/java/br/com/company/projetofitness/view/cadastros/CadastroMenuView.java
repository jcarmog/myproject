/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.cadastros;

import br.com.company.projetofitness.model.entities.Menu;
import br.com.company.projetofitness.persistencia.Transactional;
import br.com.company.projetofitness.services.MenuService;
import br.com.company.projetofitness.view.PrincipalView;
import br.com.company.projetofitness.view.datamodel.MenuDataModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author josecarmo
 */
@ManagedBean
@ViewScoped
public class CadastroMenuView extends PrincipalView {

    @Inject
    private MenuService menuService;
    private Menu selectedMenu;
    @Inject
    private Menu menu;
    private List<Menu> menus;
    private MenuDataModel menuDataModel;

    @PostConstruct
    public void init() {
        try {
            //validarPermissaoUsuario();
            menus = menuService.listarTodos();
        } catch (Exception ex) {
            Logger.getLogger(CadastroMenuView.class.getName()).log(Level.SEVERE, null, ex);
        }
        setMenuDataModel(new MenuDataModel(getMenus()));
        setMenu(new Menu());

    }

    @Transactional
    public void salvar() throws Exception {

        if (menu.getId() == 0) {
            menuService.salvar(menu);
            addMensagem("Salvo com Sucesso.", FacesMessage.SEVERITY_INFO);
            init();
        }else{
            menuService.alterar(menu);
        }

    }

    public void excluir() {
        try {
            menuService.excluir(menu);
            init();
            addMensagem("Excluído com Sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void onRowSelect(SelectEvent event) {
        setSelectedMenu((Menu) event.getObject());
        setMenu((Menu) event.getObject());
    }
    public void onRowUnselect(UnselectEvent event) {
        setSelectedMenu((Menu) event.getObject());
    }
    /**
     * @return the menu
     */
    public Menu getSelectedMenu() {
        return selectedMenu;
    }

    /**
     * @param menu the menu to set
     */
    public void setSelectedMenu(Menu menu) {
        this.selectedMenu = menu;
    }

    /**
     * @return the menus
     */
    public List<Menu> getMenus() {
        return menuService.listarTodos();
    }

    /**
     * @return the menuDataModel
     */
    public MenuDataModel getMenuDataModel() {
        return menuDataModel;
    }

    /**
     * @param menuDataModel the menuDataModel to set
     */
    public void setMenuDataModel(MenuDataModel menuDataModel) {
        this.menuDataModel = menuDataModel;
    }

    /**
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void limparCampos() {
       menu = new Menu();
    }

    
}
