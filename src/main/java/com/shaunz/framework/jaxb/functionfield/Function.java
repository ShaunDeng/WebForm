package com.shaunz.framework.jaxb.functionfield;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Function {
	@XmlAttribute(name="id")
	private String id;
	@XmlAttribute(name="title")
	private String title;
	@XmlElement(name="tableHeader")
	@XmlList
	private List<String> tableHeader;
	@XmlElement(name="tableColumn")
	@XmlList
	private List<String> tableColumn;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getTableHeader() {
		return tableHeader;
	}
	public void setTableHeader(List<String> tableHeader) {
		this.tableHeader = tableHeader;
	}
	public List<String> getTableColumn() {
		return tableColumn;
	}
	public void setTableColumn(List<String> tableColumn) {
		this.tableColumn = tableColumn;
	}
}
