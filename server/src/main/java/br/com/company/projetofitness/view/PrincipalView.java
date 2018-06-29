/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.view;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jcarmo
 */
@Named
public class PrincipalView implements Serializable{
    private FacesMessage msg;
    
    protected void addMensagem(Object o, FacesMessage.Severity s) {
        String mensagem = "";
        if (o instanceof Throwable) {
            Exception e = (Exception) o;
            mensagem = e.getMessage();
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement st : e.getStackTrace()) {
                sb.append(st.toString()).append("\n");

            }
            msg = new FacesMessage(s, e.getMessage(), sb.toString());
        } else {
            mensagem = o.toString();
            msg = new FacesMessage(s, mensagem, mensagem);
        }

        FacesContext.getCurrentInstance().addMessage("Mensagem", msg);
    }
    /**
     * @return the msg
     */
    public FacesMessage getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }
    public String getUrlApp(){
        return getServletContext().getRealPath("/");
    }
    public String getContextPath() {
        return getRequest().getContextPath();
    }

    protected String getFormularioAtual() {
        return getRequest().getRequestURI().replace(getContextPath(), "");
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }
    protected ServletContext getServletContext() {
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return context;
    }
    
    public String getApplicationPath() {
        return getServletContext().getContextPath();
    }
    
}
