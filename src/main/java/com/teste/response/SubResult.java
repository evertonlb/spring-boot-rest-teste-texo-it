package com.teste.response;

import java.io.Serializable;

public class SubResult implements Serializable, Comparable<SubResult>{
    
    String producer;

    Integer interval;

    Integer previousWin;
    
    Integer followingWin;

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    @Override
	public int compareTo(SubResult outro) {
		return (this.interval - outro.interval);
	}
}
