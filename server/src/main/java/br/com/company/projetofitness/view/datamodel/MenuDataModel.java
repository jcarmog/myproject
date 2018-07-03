/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.datamodel;

import br.com.company.projetofitness.model.entities.Menu;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author jose
 */
public class MenuDataModel extends ListDataModel<Menu> implements SelectableDataModel<Menu>,Serializable {

    public MenuDataModel() {
    }

    public MenuDataModel(List<Menu> data) {
        super(data);
    }

    @Override
    public Menu getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<Menu> menus = (List<Menu>) getWrappedData();

        for (Menu menu : menus) {
            if (String.valueOf(menu.getId()).equals(rowKey)) {
                return menu;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Menu menu) {
        return menu.getId();
    }
}
