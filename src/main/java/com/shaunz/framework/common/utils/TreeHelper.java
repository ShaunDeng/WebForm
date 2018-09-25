package com.shaunz.framework.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shaunz.framework.authority.function.entity.Function;


public class TreeHelper {

	private TreeNode root;
	private List<TreeNode> tempNodeList;
	private boolean isValidTree = true;

	public TreeHelper() {
	}

	public TreeHelper(List<TreeNode> treeNodeList) {
		tempNodeList = treeNodeList;
		generateTree();
	}

	public static TreeNode getTreeNodeById(TreeNode tree, String id) {
		if (tree == null)
			return null;
		TreeNode treeNode;
		treeNode = tree.findTreeNodeById(id);
		return treeNode;
	}

	/** generate a tree from the given treeNode or entity list */
	public void generateTree() {
		putChildIntoParent(putNodesIntoMap());
	}

	/**
	 * put all the treeNodes into a hash table by its id as the key
	 * @return hashmap that contains the treenodes
	 */
	protected HashMap<String,TreeNode> putNodesIntoMap() {
		String rootId = "root" ;
		LinkedHashMap<String,TreeNode> nodeMap = new LinkedHashMap<String, TreeNode>();
		Iterator<TreeNode> it = tempNodeList.iterator();
		while (it.hasNext()) {
			TreeNode treeNode = (TreeNode) it.next();
			String id = treeNode.getId();
			if (rootId.equals(id)) {
				rootId = id;
				this.root = treeNode;
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
	protected void putChildIntoParent(HashMap<String,TreeNode> nodeMap) {
		Iterator<TreeNode> it = nodeMap.values().iterator();
		while (it.hasNext()) {
			TreeNode treeNode = it.next();
			String parentId = treeNode.getPid();
			String parentKeyId = String.valueOf(parentId);
			if (nodeMap.containsKey(parentKeyId)) {
				TreeNode parentNode = (TreeNode) nodeMap.get(parentKeyId);
				if (parentNode == null) {
					this.isValidTree = false;
					return;
				} else {
					parentNode.addChildNode(treeNode);
				}
			}
		}
	}

	/** initialize the tempNodeList property */
	protected void initTempNodeList() {
		if (this.tempNodeList == null) {
			this.tempNodeList = new ArrayList<TreeNode>();
		}
	}

	/** add a tree node to the tempNodeList */
	public void addTreeNode(TreeNode treeNode) {
		initTempNodeList();
		this.tempNodeList.add(treeNode);
	}

	/**
	 * insert a tree node to the tree generated already
	 * 
	 * @return show the insert operation is ok or not
	 */
	public boolean insertTreeNode(TreeNode treeNode) {
		boolean insertFlag = root.insertJuniorNode(treeNode);
		return insertFlag;
	}

	/**
	 * adapt the entities to the corresponding treeNode
	 * 
	 * @param entityList
	 *            list that contains the entities
	 * @return the list containg the corresponding treeNodes of the entities
	 */
	public static List<TreeNode> changeEnititiesToTreeNodes(List<Map<String, String>> entityList) {
		List<TreeNode> tempNodeList = new ArrayList<TreeNode>();
		TreeNode treeNode;

		Iterator<Map<String, String>> it = entityList.iterator();
		while (it.hasNext()) {
			Map<String, String> data = (Map<String, String>) it.next();
			treeNode = new TreeNode();
			treeNode.setObj(data);
			treeNode.setPid(data.get("parent_id"));
			treeNode.setId(data.get("id"));
			treeNode.setName(data.get("name"));
			treeNode.setUrl(data.get("url"));
			tempNodeList.add(treeNode);
		}
		return tempNodeList;
	}
	
	public static List<TreeNode> changeFunctionLstToTreeNodes(List<Function> functions) {
		return null;
	}

	public boolean isValidTree() {
		return this.isValidTree;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public List<TreeNode> getTempNodeList() {
		return tempNodeList;
	}

	public void setTempNodeList(List<TreeNode> tempNodeList) {
		this.tempNodeList = tempNodeList;
	}

}

