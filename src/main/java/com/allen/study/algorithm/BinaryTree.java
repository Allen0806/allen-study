package com.allen.study.algorithm;

import java.util.LinkedList;
import java.util.List;

public class BinaryTree {

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new LinkedList<>();
		LinkedList<TreeNode> stack = new LinkedList<>();
		TreeNode node = root;

		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.addLast(node);
				node = node.getLeft();
			}

			if (!stack.isEmpty()) {
				node = stack.removeLast();
				result.add(node.getVal());
				node = node.getRight();
			}
		}

		System.out.println(result);
		return result;
	}
}
