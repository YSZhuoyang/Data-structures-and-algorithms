#pragma once

#include <algorithm>
#include <iostream>

#include "BasicDataStructures.h"

using namespace BasicDataStructure;

// Bug: Left - Right and Right - Left cases are not considered
class AVLTree
{
public:
	AVLTree();
	AVLTree( int rootElement );
	~AVLTree();

	bool insert( int newElement );
	bool deleteNode( int toBeDeleted );
	bool isEmpty();
	void printTree();


private:
	// Carefull, root node might change after rotation
	AVLTreeNode* rightRotate( AVLTreeNode* n );
	AVLTreeNode* leftRotate( AVLTreeNode* n );
	// Careful, rebalance must be performed in post order
	AVLTreeNode* reBalance( AVLTreeNode* n );
	AVLTreeNode* reConectChildren( AVLTreeNode* n );
	AVLTreeNode* findNodeWithMinValue( AVLTreeNode* n );
	bool recInsert( AVLTreeNode* n, AVLTreeNode* inserted );
	bool recFindNodeAndDelete( AVLTreeNode* n, int toBeDeleted );
	void inOrderPrint( AVLTreeNode* n );
	void destroyNode( AVLTreeNode* n );
	int getHeight( AVLTreeNode* n );
	int getBalance( AVLTreeNode* n );

	AVLTreeNode* root = nullptr;
};
