package com.common.presentation;

import com.common.control.PageStore;
import com.common.entity.SeedPage;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class NewSeedPageController implements Serializable {

    private String url;
    private Boolean active;

    @EJB
    PageStore pageStore;

    public String insertSeedPage() {
        SeedPage seedPage = new SeedPage();

        seedPage.setUrl(url);
        seedPage.setActive(active);

        String r = "index";

        try {
            pageStore.save(seedPage);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while saving seed page", "Error while saving " + seedPage);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);

            r = "failed";
        }

        FacesMessage facesMsg = new FacesMessage("Seed page successfully added");
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);

        return r;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
