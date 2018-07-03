/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.converter;

import br.com.company.projetofitness.model.entities.Menu;
import br.com.company.projetofitness.services.MenuService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@Named(value ="menuConverter")
@ViewScoped
public class MenuConverter implements Converter{
    @Inject
    private MenuService menuService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return menuService.retornarPorId(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Não é um menu válida."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if((object !=null) && (object instanceof String) && !object.toString().equals("")){
            return object.toString();
        }else if(object != null && object instanceof Menu) {
            return String.valueOf(((Menu) object).getId());
        }
        else {
            return null;
        }
    }

   
}
