#pragma once

#include <algorithm>
#include <iostream>

// Bug: Left - Right and Right - Left cases are not considered
class AVLTree
{
private:
	struct Node
	{
		int value;
		int height;

		Node* leftChild;
		Node* rightChild;
	};

	// Carefull, root node might change due after rotation
	Node* rightRotate( Node* n );
	Node* leftRotate( Node* n );
	// Careful, rebalance must be performed in post order
	Node* reBalance( Node* n );
	Node* reConectChildren( Node* n );
	Node* findNodeWithMinValue( Node* n );
	bool recInsert( Node* n, Node* inserted );
	bool recFindNodeAndDelete( Node* n, int toBeDeleted );
	void inOrderPrint( Node* n );
	void destroyNode( Node* n );
	int getHeight( Node* n );
	int getBalance( Node* n );

	Node* root = nullptr;

public:
	AVLTree();
	AVLTree( int rootElement );
	~AVLTree();

	bool insert( int newElement );
	bool deleteNode( int toBeDeleted );
	bool isEmpty();
	void printTree();
};
