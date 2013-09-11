package com.common.presentation;

import com.common.annotation.BufferSize;
import com.common.annotation.LimitHost;
import com.common.annotation.MaxUrl;
import com.common.annotation.RespectRobots;
import com.common.control.Configuration;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ConfigurationController {

    @Inject
    Configuration configuration;

    @Inject @MaxUrl
    private Integer maxUrl;

    @Inject @LimitHost
    private Boolean limitHost;

    @Inject @RespectRobots
    private Boolean respectRobots;

    @Inject @BufferSize
    private Integer bufferSize;

    public String save() {
        configuration.setMaxUrl(maxUrl);
        configuration.setLimitHost(limitHost);
        configuration.setRespectRobots(respectRobots);
        configuration.setBufferSize(bufferSize);

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Configuration saved", "Configuration saved");
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);

        return "index";
    }

    public Integer getMaxUrl() {
        return maxUrl;
    }

    public Boolean getLimitHost() {
        return limitHost;
    }

    public Boolean getRespectRobots() {
        return respectRobots;
    }

    public Integer getBufferSize() {
        return bufferSize;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setMaxUrl(Integer maxUrl) {
        this.maxUrl = maxUrl;
    }

    public void setLimitHost(Boolean limitHost) {
        this.limitHost = limitHost;
    }

    public void setRespectRobots(Boolean respectRobots) {
        this.respectRobots = respectRobots;
    }

    public void setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }
}
