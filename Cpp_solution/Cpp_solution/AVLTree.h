#pragma once

#include <algorithm>
#include <iostream>


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
	void rightRotate( Node* n );
	void leftRotate( Node* n );
	// Careful, rebalance must be performed in post order
	void reBalance( Node* n );
	void recInsert( Node* n, Node* inserted );
	void inOrderPrint( Node* n );
	void deleteNode( Node* n );
	int getHeight( Node* n );
	int getBalance( Node* n );

	Node* root;

public:
	AVLTree( int rootElement );
	~AVLTree();

	void insert( int newElement );
	void printTree();
};
