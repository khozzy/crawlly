package com.common.control;

import com.common.annotation.BufferSize;
import com.common.annotation.LimitHost;
import com.common.annotation.MaxUrl;
import com.common.annotation.RespectRobots;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;

@Singleton
public class Configuration {

    private Integer maxUrl = 10; // maximum urls to crawl inside domain (-1 infinity)
    private Boolean limitHost = true; // true if you want the crawler to crawl only those URLs within the same domain as the starting URL
    private Boolean respectRobots = true;
    private Integer bufferSize = 4096;

    @Produces @MaxUrl
    public Integer getMaxUrl() {
        return maxUrl;
    }

    public void setMaxUrl(Integer maxUrl) {
        this.maxUrl = maxUrl;
    }

    @Produces @LimitHost
    public Boolean getLimitHost() {
        return limitHost;
    }

    public void setLimitHost(Boolean limitHost) {
        this.limitHost = limitHost;
    }

    @Produces @RespectRobots
    public Boolean getRespectRobots() {
        return respectRobots;
    }

    public void setRespectRobots(Boolean respectRobots) {
        this.respectRobots = respectRobots;
    }

    @Produces @BufferSize
    public Integer getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }
}
