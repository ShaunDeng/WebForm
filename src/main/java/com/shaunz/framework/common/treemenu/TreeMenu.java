package com.shaunz.framework.common.treemenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shaunz.framework.authority.function.entity.Function;

/**
 * This is for controlling theme of the treemenu.
 * For the detail please see the document of bootstrap-treeview(google it!).
 * @author dengxiong@foxmail.com
 */
@Component
public class TreeMenu implements Serializable{
	private static final long serialVersionUID = 6478538834529362527L;
	
	private List<BootStrapTreeViewNode> tempNodeList;
	private boolean isValidTree = true;

	/**
	 * Array of Objects. No default, expects data.
	 * This is the core data to be displayed by the tree view.
	 */
	private List<BootStrapTreeViewNode> data = new ArrayList<BootStrapTreeViewNode>();
	
	/**
	 * String, any legal color value. Default: inherits from Bootstrap.css.
	 * Sets the default background color used by all nodes, except when overridden on a per node basis in data.
	 */
	private String backColor;
	
	/**
	 * String, any legal color value. Default: inherits from Bootstrap.css.
	 * Sets the border color for the component; set showBorder to false if you don't want a visible border.
	 */
	private String borderColor;
	
	/**
	 * String, class names(s). Default: "glyphicon glyphicon-check" as defined by Bootstrap Glyphicons
	 * Sets the icon to be as a checked checkbox, used in conjunction with showCheckbox.
	 */
	private String checkedIcon;
	
	/**
	 * String, class name(s). Default: "glyphicon glyphicon-minus" as defined by Bootstrap Glyphicons
	 * Sets the icon to be used on a collapsible tree node.
	 */
	private String collapseIcon;
	
	/**
	 * String, any legal color value. Default: inherits from Bootstrap.css.
	 * Sets the default foreground color used by all nodes, except when overridden on a per node basis in data.
	 */
	private String color;
	
	/**
	 * String, class name(s). Default: "glyphicon" as defined by Bootstrap Glyphicons
	 * Sets the icon to be used on a tree node with no child nodes.
	 */
	private String emptyIcon;
	
	/**
	 * Boolean. Default: false
	 * Whether or not to present node text as a hyperlink. The href value of which must be provided in the data structure on a per node basis.
	 */
	private Boolean enableLinks;
	
	/**
	 * String, class name(s). Default: "glyphicon glyphicon-plus" as defined by Bootstrap Glyphicons
	 * Sets the icon to be used on an expandable tree node.
	 */
	private String expandIcon;
	
	/**
	 * Boolean. Default: true
	 * Whether or not to highlight search results.
	 */
	private Boolean highlightSearchResults;
	
	/**
	 * Boolean. Default: true
	 * Whether or not to highlight the selected node.
	 */
	private Boolean highlightSelected;
	
	/**
	 * Integer. Default: 2
	 * Sets the number of hierarchical levels deep the tree will be expanded to by default.
	 */
	private Integer levels;
	
	/**
	 * Boolean. Default: false
	 * Whether or not multiple nodes can be selected at the same time.
	 */
	private Boolean multiSelect;
	
	/**
	 * String, class name(s). Default: "glyphicon glyphicon-stop" as defined by Bootstrap Glyphicons
	 * Sets the default icon to be used on all nodes, except when overridden on a per node basis in data.
	 */
	private String nodeIcon;
	
	/**
	 * String, any legal color value. Default: '#F5F5F5'.
	 * Sets the default background color activated when the users cursor hovers over a node.
	 */
	private String onhoverColor;
	
	/**
	 * String, class name(s). Default: "glyphicon glyphicon-stop" as defined by Bootstrap Glyphicons
	 * Sets the default icon to be used on all selected nodes, except when overridden on a per node basis in data.
	 */
	private String selectedIcon;
	
	/**
	 * String, any legal color value. Default: undefined, inherits.
	 * Sets the background color of the selected node.
	 */
	private String searchResultBackColor;
	
	/**
	 * String, any legal color value. Default: '#D9534F'.
	 * Sets the foreground color of the selected node.
	 */
	private String searchResultColor;
	
	/**
	 * String, any legal color value. Default: '#428bca'.
	 * Sets the background color of the selected node.
	 */
	private String selectedBackColor;
	
	/**
	 * String, any legal color value. Default: '#FFFFFF'.
	 * Sets the foreground color of the selected node.
	 */
	private String selectedColor;
	
	/**
	 * Boolean. Default: true
	 * Whether or not to display a border around nodes.
	 */
	private Boolean showBorder;
	
	/**
	 * Boolean. Default: false
	 * Whether or not to display checkboxes on nodes.
	 */
	private Boolean showCheckbox;
	
	/**
	 * Boolean. Default: true
	 * Whether or not to display a nodes icon.
	 */
	private Boolean showIcon;
	
	/**
	 * Boolean. Default: false
	 * Whether or not to display tags to the right of each node. The values of which must be provided in the data structure on a per node basis.
	 */
	private Boolean showTags;
	
	/**
	 * String, class names(s). Default: "glyphicon glyphicon-unchecked" as defined by Bootstrap Glyphicons
	 * Sets the icon to be as an unchecked checkbox, used in conjunction with showCheckbox.
	 */
	private String uncheckedIcon;

	public Object getData() {
		return data;
	}

	public void setData(List<BootStrapTreeViewNode> data) {
		this.data = data;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getCheckedIcon() {
		return checkedIcon;
	}

	public void setCheckedIcon(String checkedIcon) {
		this.checkedIcon = checkedIcon;
	}

	public String getCollapseIcon() {
		return collapseIcon;
	}

	public void setCollapseIcon(String collapseIcon) {
		this.collapseIcon = collapseIcon;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEmptyIcon() {
		return emptyIcon;
	}

	public void setEmptyIcon(String emptyIcon) {
		this.emptyIcon = emptyIcon;
	}

	public Boolean getEnableLinks() {
		return enableLinks;
	}

	public void setEnableLinks(Boolean enableLinks) {
		this.enableLinks = enableLinks;
	}

	public String getExpandIcon() {
		return expandIcon;
	}

	public void setExpandIcon(String expandIcon) {
		this.expandIcon = expandIcon;
	}

	public Boolean getHighlightSearchResults() {
		return highlightSearchResults;
	}

	public void setHighlightSearchResults(Boolean highlightSearchResults) {
		this.highlightSearchResults = highlightSearchResults;
	}

	public Boolean getHighlightSelected() {
		return highlightSelected;
	}

	public void setHighlightSelected(Boolean highlightSelected) {
		this.highlightSelected = highlightSelected;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Boolean getMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(Boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	public String getNodeIcon() {
		return nodeIcon;
	}

	public void setNodeIcon(String nodeIcon) {
		this.nodeIcon = nodeIcon;
	}

	public String getOnhoverColor() {
		return onhoverColor;
	}

	public void setOnhoverColor(String onhoverColor) {
		this.onhoverColor = onhoverColor;
	}

	public String getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public String getSearchResultBackColor() {
		return searchResultBackColor;
	}

	public void setSearchResultBackColor(String searchResultBackColor) {
		this.searchResultBackColor = searchResultBackColor;
	}

	public String getSearchResultColor() {
		return searchResultColor;
	}

	public void setSearchResultColor(String searchResultColor) {
		this.searchResultColor = searchResultColor;
	}

	public String getSelectedBackColor() {
		return selectedBackColor;
	}

	public void setSelectedBackColor(String selectedBackColor) {
		this.selectedBackColor = selectedBackColor;
	}

	public String getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}

	public Boolean getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(Boolean showBorder) {
		this.showBorder = showBorder;
	}

	public Boolean getShowCheckbox() {
		return showCheckbox;
	}

	public void setShowCheckbox(Boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}

	public Boolean getShowIcon() {
		return showIcon;
	}

	public void setShowIcon(Boolean showIcon) {
		this.showIcon = showIcon;
	}

	public Boolean getShowTags() {
		return showTags;
	}

	public void setShowTags(Boolean showTags) {
		this.showTags = showTags;
	}

	public String getUncheckedIcon() {
		return uncheckedIcon;
	}

	public void setUncheckedIcon(String uncheckedIcon) {
		this.uncheckedIcon = uncheckedIcon;
	}
	
	/**
	 * Adapter function list to BootStrapTreeViewNode list.
	 * @param functions
	 * @return
	 */
	public static List<BootStrapTreeViewNode> changeFunctionLstToTreeNodes(List<Function> functions) {
		List<BootStrapTreeViewNode> nodeLst = new ArrayList<BootStrapTreeViewNode>();
		BootStrapTreeViewNode treeNode;

		Iterator<Function> it = functions.iterator();
		while (it.hasNext()) {
			Function function = it.next();
			treeNode = new BootStrapTreeViewNode();
			treeNode.setObj(function);
			treeNode.setPid(function.getParentId());
			treeNode.setId(function.getId());
			treeNode.setName(function.getName());
			treeNode.setUrl(function.getUrl());
			treeNode.init();
			nodeLst.add(treeNode);
		}
		return nodeLst;
	}
	
	public TreeMenu() {}

	public TreeMenu(List<BootStrapTreeViewNode> treeNodes) {
		tempNodeList = treeNodes;
		generateTree();
	}

	/** generate a tree from the given BootStrapTreeViewNode list */
	public void generateTree() {
		putChildIntoParent(putNodesIntoMap());
	}

	/**
	 * put all the BootStrapTreeViewNode into a hash table by its id as the key
	 * @return hashmap that contains the BootStrapTreeViewNodes
	 */
	protected HashMap<String,BootStrapTreeViewNode> putNodesIntoMap() {
		LinkedHashMap<String,BootStrapTreeViewNode> nodeMap = new LinkedHashMap<String, BootStrapTreeViewNode>();
		Iterator<BootStrapTreeViewNode> it = tempNodeList.iterator();
		while (it.hasNext()) {
			BootStrapTreeViewNode treeNode = (BootStrapTreeViewNode) it.next();
			String id = treeNode.getId();
			String pId = treeNode.getPid();
			if (pId.equals(id)) {
				this.data.add(treeNode);
			}
			nodeMap.put(id, treeNode);
		}
		return nodeMap;
	}

	/**
	 * set the parent nodes point to the child nodes
	 * 
	 * @param nodeMap
	 *            a hashmap that contains all the treenodes by its id as the key
	 */
	protected void putChildIntoParent(HashMap<String,BootStrapTreeViewNode> nodeMap) {
		Iterator<BootStrapTreeViewNode> it = nodeMap.values().iterator();
		while (it.hasNext()) {
			BootStrapTreeViewNode treeNode = it.next();
			String parentId = treeNode.getPid();
			String parentKeyId = String.valueOf(parentId);
			if(!parentKeyId.equals(treeNode.getId())){
				if (nodeMap.containsKey(parentKeyId)) {
					BootStrapTreeViewNode parentNode = (BootStrapTreeViewNode) nodeMap.get(parentKeyId);
					if (parentNode == null) {
						this.isValidTree = false;
						return;
					} else {
						parentNode.addChildNode(treeNode);
					}
				}
			}
		}
	}

	/** initialize the tempNodeList property */
	protected void initTempNodeList() {
		if (this.tempNodeList == null) {
			this.tempNodeList = new ArrayList<BootStrapTreeViewNode>();
		}
	}

	/** add a tree node to the tempNodeList */
	public void addTreeNode(BootStrapTreeViewNode treeNode) {
		initTempNodeList();
		this.tempNodeList.add(treeNode);
	}
	
	/**
	 * Indicated if this tree menu is available for display.
	 * @return
	 */
	public boolean isValidTree() {
		return this.isValidTree;
	}
}
