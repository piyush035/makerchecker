package com.rennover.client.framework.widget.base;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * @author bguedes
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ZTree extends JTree
{
	/**
	 * 
	 */
	public ZTree()
	{
		super();
	}

	/**
	 * @param value
	 */
	public ZTree(Object[] value)
	{
		super(value);
	}

	/**
	 * @param value
	 */
	public ZTree(Vector value)
	{
		super(value);
	}

	/**
	 * @param value
	 */
	public ZTree(Hashtable value)
	{
		super(value);
	}

	/**
	 * @param root
	 */
	public ZTree(TreeNode root)
	{
		super(root);
	}

	/**
	 * @param root
	 * @param asksAllowsChildren
	 */
	public ZTree(TreeNode root, boolean asksAllowsChildren)
	{
		super(root, asksAllowsChildren);
	}

	/**
	 * @param newModel
	 */
	public ZTree(TreeModel newModel)
	{
		super(newModel);
	}

	/**
	 * Correction du BUG 4654916 : JList and JTree should scroll automatically
	 * with first-letter navigation
	 * 
	 * @see http://developer.java.sun.com/developer/bugParade/bugs/4654916.html
	 */
	public void setSelectionPath(TreePath newPath)
	{
		super.setSelectionPath(newPath);
		scrollPathToVisible(newPath);
	}

}