package com.indeed.control;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Stateless
public class Messages {

    public void append(String title, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title,details));
    }
}
