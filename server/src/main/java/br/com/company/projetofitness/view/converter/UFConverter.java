/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view.converter;

import br.com.company.projetofitness.model.entities.UnidadeFederacao;
import br.com.company.projetofitness.services.CidadeService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@Named(value ="ufConverter")
@ViewScoped
public class UFConverter implements Converter{
    @Inject
    private CidadeService cidadeService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return cidadeService.retornarUFPorId(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if(object != null && object instanceof UnidadeFederacao) {
            return String.valueOf(((UnidadeFederacao) object).getId());
        }
        else {
            return null;
        }
    }

   
}
