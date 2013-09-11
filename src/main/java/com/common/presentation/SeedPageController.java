package com.common.presentation;

import com.common.control.PageStore;
import com.common.entity.SeedPage;
import com.crawler.Net;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class SeedPageController implements Serializable {

    @EJB
    PageStore pageStore;

    public String createSeedPage() {
        return "newSeedPage";
    }

    public List<SeedPage> getSeedPages() {
        return pageStore.getAllSeedPages();
    }

    public Long getSubPagesCount(SeedPage seedPage) {
        return pageStore.getSubPagesCountForSeedPage(seedPage);
    }
}
