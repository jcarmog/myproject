/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.cadastros;

import br.com.company.projetofitness.model.entities.ItemMenu;
import br.com.company.projetofitness.model.entities.Menu;
import br.com.company.projetofitness.persistencia.Transactional;
import br.com.company.projetofitness.services.ItemMenuService;
import br.com.company.projetofitness.services.MenuService;
import br.com.company.projetofitness.view.PrincipalView;
import br.com.company.projetofitness.view.datamodel.MenuItemDataModel;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class CadastroMenuItemView extends PrincipalView {

    @Inject
    private ItemMenuService itemMenuService;
    @Inject
    private MenuService menuService;
    private List<Menu> menus;
    @Inject
    private ItemMenu itemMenu;
    private List<ItemMenu> itens;
    private ItemMenu selectedItemMenu;
    private Menu selectedMenu;
    private String idMenuSelected;
    private MenuItemDataModel itemDataModel;

    @PostConstruct
    public void init() {
        try {
            limparCampos();
        } catch (Exception ex) {
            addMensagem(ex.getLocalizedMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    @Transactional
    public void salvar() {

        try {
            itemMenu.setMenu(menuService.retornarPorId(Integer.parseInt(idMenuSelected)));
            if (itemMenu.getId() == 0) {

                itemMenuService.salvar(itemMenu);
                addMensagem("Salvo com Sucesso.", FacesMessage.SEVERITY_INFO);
            } else {
                itemMenuService.alterar(itemMenu);
            }
            itemMenu = new ItemMenu();
            selectedItemMenu = new ItemMenu();
        } catch (Exception e) {
            addMensagem(e.getLocalizedMessage(), FacesMessage.SEVERITY_ERROR);

        }

    }

    @Transactional
    public void excluir() {
        try {

            itemMenu = itemMenuService.retornarPorId(itemMenu.getId());
            itemMenuService.excluir(itemMenu);
            addMensagem("Item removido com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                addMensagem("Não é possível excluir registro. Violação de Chave Estrangeira.", FacesMessage.SEVERITY_ERROR);
            } else {
                addMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
            }

        }
    }

    public void limparCampos() {
        idMenuSelected = "";

        setMenus(menuService.listarTodos());
        setItemMenu(new ItemMenu());
        itemDataModel = new MenuItemDataModel(itemMenuService.listarTodos());
        selectedMenu = new Menu();
        selectedItemMenu = new ItemMenu();
        itemMenu = new ItemMenu();
    }

    public void onChangeMenu() throws Exception {
        selectedMenu = menuService.retornarPorId(itemMenu.getMenu().getId());
        itens = itemMenuService.retornaTodosPorMenu(itemMenu.getMenu().getId());
        itemMenu = new ItemMenu();
    }

    public void onRowSelect(SelectEvent event) {
        if (event.getObject() != null) {
            setSelectedItemMenu((ItemMenu) event.getObject());
            setItemMenu((ItemMenu) event.getObject());
            setIdMenuSelected(String.valueOf(getSelectedItemMenu().getMenu().getId()));
        }
        //idMenuSelected = String.valueOf(getSelectedItemMenu().getId());
    }

    public void onRowUnselect(UnselectEvent event) {
        setSelectedItemMenu((ItemMenu) event.getObject());
    }

    /**
     * @return the menus
     */
    public List<Menu> getMenus() {
        return menuService.listarTodos();
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    /**
     * @return the itemMenu
     */
    public ItemMenu getItemMenu() {
        return itemMenu;
    }

    /**
     * @param itemMenu the itemMenu to set
     */
    public void setItemMenu(ItemMenu itemMenu) {
        this.itemMenu = itemMenu;
    }

    /**
     * @return the selectedMenu1
     */
    public Menu getSelectedMenu() {
        return selectedMenu;
    }

    /**
     * @param selectedMenu1 the selectedMenu1 to set
     */
    public void setSelectedMenu(Menu selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    /**
     * @return the itemDataModel
     */
    public MenuItemDataModel getItemDataModel() {
        return itemDataModel;
    }

    /**
     * @param itemDataModel the itemDataModel to set
     */
    public void setItemDataModel(MenuItemDataModel itemDataModel) {
        this.itemDataModel = itemDataModel;
    }

    /**
     * @return the idMenuSelected
     */
    public String getIdMenuSelected() {
        return idMenuSelected;
    }

    /**
     * @param idMenuSelected the idMenuSelected to set
     */
    public void setIdMenuSelected(String idMenuSelected) {
        this.idMenuSelected = idMenuSelected;
    }

    /**
     * @return the selectedItemMenu
     */
    public ItemMenu getSelectedItemMenu() {
        return selectedItemMenu;
    }

    /**
     * @param selectedItemMenu the selectedItemMenu to set
     */
    public void setSelectedItemMenu(ItemMenu selectedItemMenu) {
        this.selectedItemMenu = selectedItemMenu;
    }

    /**
     * @return the itens
     */
    public List<ItemMenu> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<ItemMenu> itens) {
        this.itens = itens;
    }

}
