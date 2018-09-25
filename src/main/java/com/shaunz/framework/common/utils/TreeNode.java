package com.shaunz.framework.common.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.io.Serializable;

@Component
public class TreeNode implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TreeNode.class);
	
	protected String pid;
	protected String id;
	protected String name;
	protected String url ;
	protected Object obj;
	protected TreeNode pNode;
	protected List<TreeNode> nodes;
	
	public TreeNode() {
		init();
	}
	
	public TreeNode(String id,String name,String pid,String url) {
		this.id=id;
		this.name=name;
		this.pid=pid;
		this.url=url;
		init();
	}
	
	public void init(){
		initNodes();
	}

	/**
	 * Check if this tree node have children.
	 * @return true if not have children.
	 */
	public boolean isLeaf() {
		if (nodes == null) {
			return true;
		} else {
			if (nodes.isEmpty()) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Add specific tree node as child of current node
	 * @param node
	 */
	public void addChildNode(TreeNode node) {
		initNodes();
		if(!nodes.contains(node)){
			nodes.add(node);
		}
	}

	protected void initNodes() {
		if (nodes == null)
			nodes = new ArrayList<TreeNode>();
	}

	
	@Override
	public boolean equals(Object obj) {
		TreeNode node = (TreeNode) obj ;
		return node.getId().equals(this.getId()) ;
	}

	public boolean isValidTree() {
		return true;
	}
	
	/**
	 * Get the parent of current tree node.
	 * @return
	 */
	public List<TreeNode> elders() {
		List<TreeNode> elderList = new ArrayList<TreeNode>();
		TreeNode parentNode = this.getPNode();
		if (parentNode == null) {
			return elderList;
		} else {
			elderList.add(parentNode);
			elderList.addAll(parentNode.elders());
			return elderList;
		}
	}

	/**
	 * Get all children from current tree node.
	 * @return
	 */
	public List<TreeNode> juniors() {
		List<TreeNode> juniorList = new ArrayList<TreeNode>();
		List<TreeNode> nodes = this.getNodes();
		if (nodes == null) {
			return juniorList;
		} else {
			int childNumber = nodes.size();
			for (int i = 0; i < childNumber; i++) {
				TreeNode junior = nodes.get(i);
				juniorList.add(junior);
				juniorList.addAll(junior.juniors());
			}
			return juniorList;
		}
	}

	/**
	 * Get children list from current tree node.
	 * @return
	 */
	public List<TreeNode> getNodes() {
		return nodes;
	}

	/**
	 * Delete the node and it's children from tree.
	 */
	public void deleteNode() {
		TreeNode parentNode = this.getPNode();
		String id = this.getId();

		if (parentNode != null) {
			parentNode.deleteChildNode(id);
		}
	}

	/**
	 * Delete the tree node from tree.
	 * @param childId
	 */
	public void deleteChildNode(String childId) {
		List<TreeNode> nodes = this.getNodes();
		int childNumber = nodes.size();
		for (int i = 0; i < childNumber; i++) {
			TreeNode child = nodes.get(i);
			if (child.getId() .equals( childId)) {
				nodes.remove(i);
				return;
			}
		}
	}

	/**
	 * Insert the specific treeNode into tree.
	 * @param treeNode
	 * @return
	 */
	public boolean insertJuniorNode(TreeNode treeNode) {
		String juniorParentId = treeNode.getPid();
		if (this.pid .equals(juniorParentId)) {
			addChildNode(treeNode);
			return true;
		} else {
			List<TreeNode> nodes = this.getNodes();
			int childNumber = nodes.size();
			boolean insertFlag;

			for (int i = 0; i < childNumber; i++) {
				TreeNode childNode = nodes.get(i);
				insertFlag = childNode.insertJuniorNode(treeNode);
				if (insertFlag == true)
					return true;
			}
			return false;
		}
	}

	/**
	 * Find a tree node base on id provided. 
	 * It will return the first one found.
	 * @param id
	 * @return
	 */
	public TreeNode findTreeNodeById(String id) {
		if (this.id.equals(id))
			return this;
		if (nodes.isEmpty() || nodes == null) {
			return null;
		} else {
			int childNumber = nodes.size();
			for (int i = 0; i < childNumber; i++) {
				TreeNode child = nodes.get(i);
				TreeNode resultNode = child.findTreeNodeById(id);
				if (resultNode != null) {
					return resultNode;
				}
			}
			return null;
		}
	}

	/**
	 * Traverse this TreeNode and print it.
	 */
	public void traverse() {
		if (id ==null || "".equals(id))
			return;
		logger.info(this.toString());
		if (nodes == null || nodes.isEmpty())
			return;
		int childNumber = nodes.size();
		for (int i = 0; i < childNumber; i++) {
			TreeNode child = nodes.get(i);
			child.traverse();
		}
	}


	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TreeNode getPNode() {
		return pNode;
	}

	public void setPNode(TreeNode pNode) {
		this.pNode = pNode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
	
}

