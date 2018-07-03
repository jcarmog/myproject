/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.datamodel;

import br.com.company.projetofitness.model.entities.Perfil;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author jose
 */
public class PerfilDataModel extends ListDataModel<Perfil> implements SelectableDataModel<Perfil>, Serializable {

     public PerfilDataModel() {
    }

    public PerfilDataModel(List<Perfil> data) {
        super(data);
    }

    @Override
    public Object getRowKey(Perfil t) {
        return t.getId();
    }

    @Override
    public Perfil getRowData(String rowKey) {
        List<Perfil> perfis = (List<Perfil>) getWrappedData();

        for (Perfil perfil : perfis) {
            if (perfil.getId() == Integer.parseInt(rowKey)) {
                return perfil;
            }
        }

        return null;
    }
}
