/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view;

import br.com.company.projetofitness.model.entities.ItemMenu;
import br.com.company.projetofitness.model.entities.Menu;
import br.com.company.projetofitness.services.ItemMenuService;
import br.com.company.projetofitness.services.MenuService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author jose
 */
@ManagedBean
@SessionScoped
public class ListarMenuPrincipalView extends PrincipalView {

    @Inject
    private MenuService menuService;
    @Inject
    private ItemMenuService itemMenuService;

    private List<Menu> menus;
    private MenuModel menuModel;
    private Menubar menubar;

    @PostConstruct
    public void init() {
        carregarMenuAdministrador();
        menubar = new Menubar();
        /*
        //validarSessaoUsuario();
        
        Perfil p = getUsuarioSessao().getPerfil();
        
        try {
            //List<Acessos> acessos = (List<Acessos>) getSessaoHibernate().createQuery("from Acessos where Perfil="+p.getId()).list();
            menus = menuService.retornaTodosOrderBy();
        } catch (Exception ex) {
            Logger.getLogger(ListarMenuPrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<ItemMenu> menusUsuario = new ArrayList<ItemMenu>();
        for (Acessos a : p.getAcessos()) {
            menusUsuario.add(a.getId().getItemMenu());
        }
        menuModel = new DefaultMenuModel();
        for (Menu m : getMenus()) {
            int cont = 0;
            UISubmenu submenu = new UISubmenu();
            submenu.setLabel(m.getNome());
            List<ItemMenu> itens = new ArrayList<>();
            try {
                itens = itemMenuService.retornaTodosPorMenu(String.valueOf(m.getId()));
            } catch (Exception ex) {
                Logger.getLogger(ListarMenuPrincipalView.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (ItemMenu i : itens) {
                for (ItemMenu ii : menusUsuario) {
                    if (ii.getId() == i.getId()) {
                        UIMenuItem item = new UIMenuItem();
                        item.setValue(i.getNome());
                        item.setUrl(i.getAcao());
                        submenu.getChildren().add(item);
                        cont++;
                        break;
                    }
                }

            }
            getMenuModel().addElement(submenu);
        }
         */
        menubar.setModel(menuModel);
    }

    /**
     * @return the menuModel
     */
    public MenuModel getMenuModel() {
        return menuModel;
    }

    /**
     * @param menuModel the menuModel to set
     */
    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    /**
     * @return the menus
     */
    public List<Menu> getMenus() {
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    /**
     * @return the menubar
     */
    public Menubar getMenubar() {
        return menubar;
    }

    /**
     * @param menubar the menubar to set
     */
    public void setMenubar(Menubar menubar) {
        this.menubar = menubar;
    }

    private void carregarMenuAdministrador() {
        try {
            //List<Acessos> acessos = (List<Acessos>) getSessaoHibernate().createQuery("from Acessos where Perfil="+p.getId()).list();
            menus = menuService.retornaTodosOrderBy();
        } catch (Exception ex) {
            Logger.getLogger(ListarMenuPrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }

        menuModel = new DefaultMenuModel();
        for (Menu m : getMenus()) {
            int cont = 0;
            DefaultSubMenu submenu = new DefaultSubMenu();
            submenu.setLabel(m.getNome());

            List<ItemMenu> itens = new ArrayList<ItemMenu>();
            try {
                itens = m.getItens();
            } catch (Exception ex) {
                Logger.getLogger(ListarMenuPrincipalView.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (ItemMenu i : itens) {
                DefaultMenuItem item = new DefaultMenuItem();
                item.setValue(i.getNome());
                item.setUrl(i.getAcao());
                submenu.addElement(item);
                cont++;

            }

            getMenuModel().addElement(submenu);

        }
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
