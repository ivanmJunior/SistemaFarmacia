package br.com.farmacia.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

	public static void mensagemSucesso(String mensagem){
		FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext fContexto = FacesContext.getCurrentInstance();
		fContexto.addMessage(null, fMsg);
	}
	
	public static void mensagemErro(String mensagem){
		FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext fContexto = FacesContext.getCurrentInstance();
		fContexto.addMessage(null, fMsg);
	}
}
