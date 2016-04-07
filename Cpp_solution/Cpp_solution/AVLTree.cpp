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
	std::cout << "Start inserting value: " << newElement << std::endl;

	Node* inserted = new Node();
	inserted->value = newElement;
	inserted->leftChild = nullptr;
	inserted->rightChild = nullptr;
	inserted->height = 0;

	recInsert( root, inserted );
	root = reBalance( root, newElement );
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
		std::cout << "Height: " << getHeight( n ) << "  Value: " << n->value << std::endl;
		inOrderPrint( n->rightChild );
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

AVLTree::Node* AVLTree::rightRotate( Node * n )
{
	Node* newSubRoot = n->leftChild;
	Node* newRightLeft = newSubRoot->rightChild;

	// Right rotate
	newSubRoot->rightChild = n;
	n->leftChild = newRightLeft;

	// Update heights
	n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;
	newSubRoot->height = std::max( getHeight( newSubRoot->leftChild ), getHeight( newSubRoot->rightChild) ) + 1;

	return newSubRoot;
}

AVLTree::Node* AVLTree::leftRotate( Node * n )
{
	Node* newSubRoot = n->rightChild;
	Node* newLeftRight = newSubRoot->leftChild;

	// Left rotate
	newSubRoot->leftChild = n;
	n->rightChild = newLeftRight;

	// Update heights
	n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;
	newSubRoot->height = std::max( getHeight( newSubRoot->leftChild ), getHeight( newSubRoot->rightChild ) ) + 1;

	return newSubRoot;
}

AVLTree::Node* AVLTree::reBalance( Node * n, int inserted )
{
	if (n->leftChild != nullptr)
	{
		n->leftChild = reBalance( n->leftChild, inserted );
	}

	if (n->rightChild != nullptr)
	{
		n->rightChild = reBalance( n->rightChild, inserted );
	}

	// Left - right case, make it left - left
	if (getBalance( n ) > 1 && inserted > n->leftChild->value)
	{
		n->leftChild = leftRotate( n->leftChild );
	}
	// Right - left case, make it right - right
	else if (getBalance( n ) < -1 && inserted < n->rightChild->value)
	{
		n->rightChild = rightRotate( n->rightChild );
	}

	// Left - left case
	if (getBalance( n ) > 1)
	{
		return rightRotate( n );
	}
	// Right - right case
	else if (getBalance( n ) < -1)
	{
		return leftRotate( n );
	}
	else
	{
		return n;
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
	std::cout << "Root: Height: " << getHeight( root ) << "  Value: " << root->value << std::endl;

	inOrderPrint( root );
}
