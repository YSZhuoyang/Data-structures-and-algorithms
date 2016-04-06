#include "AVLTree.h"



AVLTree::AVLTree( int rootElement )
{
	root = new Node();
	root->height = 0;
	root->leftChild = nullptr;
	root->rightChild = nullptr;
	root->value = rootElement;
}

AVLTree::~AVLTree()
{
	deleteNode( root );
}

void AVLTree::insert( int newElement )
{
	Node* inserted = new Node();
	inserted->value = newElement;
	inserted->leftChild = nullptr;
	inserted->rightChild = nullptr;
	inserted->height = 0;

	recInsert( root, inserted );
	reBalance( root );
}

void AVLTree::recInsert( Node * n, Node* inserted )
{
	if (inserted->value > n->value)
	{
		if (n->rightChild == nullptr)
		{
			n->rightChild = inserted;
		}
		else
		{
			recInsert( n->rightChild, inserted );
		}
	}
	else
	{
		if (n->leftChild == nullptr)
		{
			n->leftChild = inserted;
		}
		else
		{
			recInsert( n->leftChild, inserted );
		}
	}

	n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;
}

void AVLTree::inOrderPrint( Node * n )
{
	if (n != nullptr)
	{
		inOrderPrint( n->leftChild );
		std::cout << "Height: " << n->height << "  Value: " << n->value << std::endl;
		inOrderPrint( n->rightChild );
	}
	else
	{
		std::cout << "null" << std::endl;
	}
}

int AVLTree::getHeight( Node* n )
{
	if (n == nullptr)
	{
		return -1;
	}
	else
	{
		return n->height;
	}
}

int AVLTree::getBalance( Node * n )
{
	if (n == nullptr)
	{
		return 0;
	}
	else
	{
		return getHeight( n->leftChild ) - getHeight( n->rightChild );
	}
}

void AVLTree::rightRotate( Node * n )
{
	Node* newSubRoot = n->leftChild;
	Node* newRightLeft = newSubRoot->rightChild;

	// Right rotate
	newSubRoot->rightChild = n;
	n->leftChild = newRightLeft;

	// Update heights
	n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;
	newSubRoot->height = std::max( getHeight( newSubRoot->leftChild ), getHeight( newSubRoot->rightChild) ) + 1;
	root = (getHeight( newSubRoot ) > getHeight( root )) ? newSubRoot : root;
}

void AVLTree::leftRotate( Node * n )
{
	Node* newSubRoot = n->rightChild;
	Node* newLeftRight = newSubRoot->leftChild;

	// Left rotate
	newSubRoot->leftChild = n;
	n->rightChild = newLeftRight;

	// Update heights
	n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;
	newSubRoot->height = std::max( getHeight( newSubRoot->leftChild ), getHeight( newSubRoot->rightChild ) ) + 1;
	root = (getHeight( newSubRoot ) > getHeight( root )) ? newSubRoot : root;
}

void AVLTree::reBalance( Node * n )
{
	if (n == nullptr)
	{
		return;
	}

	reBalance( n->leftChild );
	reBalance( n->rightChild );

	if (getBalance( n ) > 1)
	{
		rightRotate( n );
	}
	else if (getBalance( n ) < -1)
	{
		leftRotate( n );
	}
}

void AVLTree::deleteNode( Node * n )
{
	if (n != nullptr)
	{
		deleteNode( n->leftChild );
		deleteNode( n->rightChild );

		n->leftChild = nullptr;
		n->rightChild = nullptr;
		delete( n );
	}
}

void AVLTree::printTree()
{
	std::cout << "Root: Height: " << root->height << "  Value: " << root->value << std::endl;

	inOrderPrint( root );
}
