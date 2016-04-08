#pragma once

namespace BasicDataStructure
{
	struct ListNode
	{
		int value;
		ListNode* next;
	};

	struct TreeNode
	{
		int value;

		TreeNode* leftChild;
		TreeNode* rightChild;
	};

	struct AVLTreeNode
	{
		int value;
		int height;

		AVLTreeNode* leftChild;
		AVLTreeNode* rightChild;
	};
}
