#include "AVLTree.h"



AVLTree::AVLTree()
{

}

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
	destroyNode( root );
}

bool AVLTree::insert( int newElement )
{
	Node* nodeToBeInserted = new Node();
	nodeToBeInserted->value = newElement;
	nodeToBeInserted->leftChild = nullptr;
	nodeToBeInserted->rightChild = nullptr;
	nodeToBeInserted->height = 0;

	if (isEmpty())
	{
		root = nodeToBeInserted;

		return true;
	}
	else
	{
		if (recInsert( root, nodeToBeInserted ))
		{
			root = reBalance( root );

			return true;
		}

		return false;
	}
}

bool AVLTree::deleteNode( int toBeDeleted )
{
	if (isEmpty())
	{
		return false;
	}
	else if (root->value == toBeDeleted)
	{
		std::cout << "Delete root node" << std::endl;

		Node* nodeToBeDeleted = root;
		root = reConectChildren( root );
		delete nodeToBeDeleted;

		return true;
	}
	else
	{
		std::cout << "Delete a child node" << std::endl;

		return recFindNodeAndDelete( root, toBeDeleted );
	}

	root = reBalance( root );
}

bool AVLTree::isEmpty()
{
	return root == nullptr;
}

bool AVLTree::recInsert( Node * n, Node* inserted )
{
	if (inserted->value > n->value)
	{
		if (n->rightChild == nullptr)
		{
			n->rightChild = inserted;
			n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;

			return true;
		}
		else
		{
			if (recInsert( n->rightChild, inserted ))
			{
				n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;

				return true;
			}
			else
			{
				return false;
			}
		}
	}
	else if (inserted->value < n->value)
	{
		if (n->leftChild == nullptr)
		{
			n->leftChild = inserted;
			n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;

			return true;
		}
		else
		{
			if (recInsert( n->leftChild, inserted ))
			{
				n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;

				return true;
			}
			else
			{
				return false;
			}
		}
	}
	else
	{
		std::cout << "Element already exists, duplicates are not allowed!" << std::endl;

		return false;
	}
}

bool AVLTree::recFindNodeAndDelete( Node * n, int toBeDeleted )
{
	if (toBeDeleted > n->value)
	{
		if (n->rightChild != nullptr)
		{
			if (n->rightChild->value == toBeDeleted)
			{
				// Delete right child
				Node* nodeToBeDeleted = n->rightChild;
				n->rightChild = reConectChildren( n->rightChild );
				delete nodeToBeDeleted;
				n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;

				return true;
			}
			else
			{
				return recFindNodeAndDelete( n->rightChild, toBeDeleted );
			}
		}
		else
		{
			std::cout << "Element " << toBeDeleted << " not found!" << std::endl;

			return false;
		}
	}
	// toBeDeleted will never equal to value of node n
	// at this stage since it has been compared before
	else
	{
		if (n->leftChild != nullptr)
		{
			if (n->leftChild->value == toBeDeleted)
			{
				// Delete left child
				Node* nodeToBeDeleted = n->leftChild;
				n->leftChild = reConectChildren( n->leftChild );
				delete nodeToBeDeleted;
				n->height = std::max( getHeight( n->leftChild ), getHeight( n->rightChild ) ) + 1;

				return true;
			}
			else
			{
				return recFindNodeAndDelete( n->leftChild, toBeDeleted );
			}
		}
		else
		{
			std::cout << "Element " << toBeDeleted << " not found!" << std::endl;

			return false;
		}
	}
}

AVLTree::Node* AVLTree::reConectChildren( Node * n )
{
	Node* subRoot = new Node();

	if (n->leftChild == nullptr || n->rightChild == nullptr)
	{
		subRoot = (n->leftChild != nullptr) ? n->leftChild : n->rightChild;
	}
	else
	{
		Node* temp = findNodeWithMinValue( n->rightChild );

		//std::cout << "Min Element found! Value: " << temp->value << std::endl;

		subRoot->value = temp->value;
		recFindNodeAndDelete( n->rightChild, temp->value );
		subRoot->rightChild = n->rightChild;
		subRoot->leftChild = n->leftChild;
		subRoot->height = std::max( getHeight( subRoot->leftChild ), getHeight( subRoot->rightChild ) ) + 1;
	}

	return subRoot;
}

AVLTree::Node* AVLTree::findNodeWithMinValue( Node * n )
{
	if (n == nullptr)
	{
		return nullptr;
	}
	else
	{
		while (n->leftChild != nullptr)
		{
			n = n->leftChild;
		}

		return n;
	}
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

AVLTree::Node* AVLTree::reBalance( Node * n )
{
	if (n->leftChild != nullptr)
	{
		n->leftChild = reBalance( n->leftChild );
	}

	if (n->rightChild != nullptr)
	{
		n->rightChild = reBalance( n->rightChild );
	}

	// Left - right case, make it left - left
	if (getBalance( n ) > 1 && getBalance( n->leftChild ) < 0)
	{
		n->leftChild = leftRotate( n->leftChild );
	}
	// Right - left case, make it right - right
	else if (getBalance( n ) < -1 && getBalance( n->rightChild ) > 0)
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

void AVLTree::destroyNode( Node * n )
{
	if (n != nullptr)
	{
		destroyNode( n->leftChild );
		destroyNode( n->rightChild );

		n->leftChild = nullptr;
		n->rightChild = nullptr;
		delete n;
	}
}

void AVLTree::printTree()
{
	if (!isEmpty())
	{
		std::cout << "Root: Height: " << getHeight( root ) << "  Value: " << root->value << std::endl;

		inOrderPrint( root );
	}
}
