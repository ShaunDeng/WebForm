package com.shaunz.framework.core;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BaseEntity implements Cloneable{
	protected String id;
	protected boolean optFlag = false;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
