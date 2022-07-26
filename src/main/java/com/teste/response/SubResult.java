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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((followingWin == null) ? 0 : followingWin.hashCode());
        result = prime * result + ((interval == null) ? 0 : interval.hashCode());
        result = prime * result + ((previousWin == null) ? 0 : previousWin.hashCode());
        result = prime * result + ((producer == null) ? 0 : producer.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubResult other = (SubResult) obj;
        if (followingWin == null) {
            if (other.followingWin != null)
                return false;
        } else if (!followingWin.equals(other.followingWin))
            return false;
        if (interval == null) {
            if (other.interval != null)
                return false;
        } else if (!interval.equals(other.interval))
            return false;
        if (previousWin == null) {
            if (other.previousWin != null)
                return false;
        } else if (!previousWin.equals(other.previousWin))
            return false;
        if (producer == null) {
            if (other.producer != null)
                return false;
        } else if (!producer.equals(other.producer))
            return false;
        return true;
    }
}
