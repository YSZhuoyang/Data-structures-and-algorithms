#pragma once

namespace BasicDataStructure
{
	struct ListNode
	{
		int value;
		ListNode* next;

		ListNode()
		{
			value = -1;
			next = nullptr;
		}

		ListNode( int n )
		{
			value = n;
			next = nullptr;
		}
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
