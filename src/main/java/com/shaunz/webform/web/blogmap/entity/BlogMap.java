package com.shaunz.webform.web.blogmap.entity;

public class BlogMap{
    private String pageId;

    private String pageType;

    private String blogId;

    private String orderId;
    
    public BlogMap(){
    	
    }
    
    public BlogMap(String pageId, String pageType, String blogId, String orderId){
    	this.pageId = pageId;
    	this.pageType = pageType;
    	this.blogId = blogId;
    	this.orderId = orderId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType == null ? null : pageType.trim();
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId == null ? null : blogId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }
}