package com.shaunz.framework.core;

import org.apache.log4j.Logger;

public class BaseEntity implements Cloneable{
	protected Logger logger = Logger.getLogger(BaseEntity.class);
	protected String id;
	protected boolean optFlag = false;
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public boolean getOptFlag() {
		return optFlag;
	}

	public void setOptFlag(boolean optFlag) {
		this.optFlag = optFlag;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
