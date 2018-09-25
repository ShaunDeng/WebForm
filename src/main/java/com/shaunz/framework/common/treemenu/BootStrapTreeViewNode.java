package com.shaunz.framework.common.treemenu;

import org.springframework.stereotype.Component;

import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.common.utils.TreeNode;

/**
 * This is for controlling theme of the each tree node.
 * For the detail please see the document of bootstrap-treeview(google it!).
 * @author dengxiong@foxmail.com
 */
@Component
public class BootStrapTreeViewNode extends TreeNode{
	private static final long serialVersionUID = -2865461364179172802L;
	
	@Override
	public void init(){
		super.init();
		if(IStringUtil.notBlank(id) && IStringUtil.notBlank(name)){
			this.text = name;
			this.href = url;
		}
	}

	/**
	 * String Mandatory
	 * The text value displayed for a given tree node, typically to the right of the nodes icon.
	 */
	private String text;
	
	/**
	 * String Optional
	 * The icon displayed on a given node, typically to the left of the text.
	 * For simplicity we directly leverage Bootstraps Glyphicons support and as such you should provide both the base class and individual icon class separated by a space.
	 * By providing the base class you retain full control over the icons used. If you want to use your own then just add your class to this icon field.
	 */
	private String icon;
	
	/**
	 * String Optional
	 * The icon displayed on a given node when selected, typically to the left of the text.
	 */
	private String selectedIcon;
	
	/**
	 * String Optional
	 * The foreground color used on a given node, overrides global color option.
	 */
	private String color;
	
	/**
	 * String Optional
	 * The background color used on a given node, overrides global color option.
	 */
	private String backColor;
	
	/**
	 * String Optional
	 * Used in conjunction with global enableLinks option to specify anchor tag URL on a given node.
	 */
	private String href = "#";
	
	/**
	 * 	Boolean Default: true
	 * Whether or not a node is selectable in the tree. False indicates the node should act as an expansion heading and will not fire selection events.
	 */
	private Boolean selectable;
	
	/**
	 * Object Optional Describes a node's initial state.
	 */
	private class state{
		/**
		 * Boolean Default: false
		 * Whether or not a node is checked, represented by a checkbox style glyphicon.
		 */
		public Boolean checked;
		
		/**
		 * Boolean Default: false
		 * Whether or not a node is disabled (not selectable, expandable or checkable).
		 */
		public Boolean disabled;
		
		/**
		 * Boolean Default: false
		 * Whether or not a node is expanded i.e. open. Takes precedence over global option levels.
		 */
		public Boolean expanded = true;
		
		/**
		 * Boolean Default: false
		 * Whether or not a node is selected.
		 */
		public Boolean selected;
		
		
	};
	
	/**
	 * Array of Strings Optional
	 * Used in conjunction with global showTags option to add additional information to the right of each node; using Bootstrap Badges
	 */
	private String[] tags;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Boolean getSelectable() {
		return selectable;
	}

	public void setSelectable(Boolean selectable) {
		this.selectable = selectable;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
}
