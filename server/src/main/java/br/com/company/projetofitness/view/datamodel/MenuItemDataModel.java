/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.datamodel;

import br.com.company.projetofitness.model.entities.ItemMenu;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author jose
 */
public class MenuItemDataModel extends ListDataModel<ItemMenu> implements SelectableDataModel<ItemMenu>,Serializable {

    public MenuItemDataModel() {
    }

    public MenuItemDataModel(List<ItemMenu> data) {
        super(data);
    }

    @Override
    public ItemMenu getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<ItemMenu> menus = (List<ItemMenu>) getWrappedData();

        for (ItemMenu menu : menus) {
            if (menu.getId() == Integer.parseInt(rowKey)) {
                return menu;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(ItemMenu menu) {
        return menu.getId();
    }
}
