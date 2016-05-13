#include "AVLTree.h"
#include "LinkedList.h"
#include "BurstBalloons.h"
#include "Stack.h"
#include "Queue.h"


int main()
{
	/*BurstBalloons s;
	vector<int> v;
	v.push_back( 3 );
	v.push_back( 1 );
	v.push_back( 5 );
	v.push_back( 8 );

	s.maxCoins( v );
	*/

	/* Test queue implemented with array */
	Queue queue;

	queue.printQueue();

	queue.enQueue( 1 );
	queue.enQueue( 2 );
	queue.enQueue( -1 );

	queue.printQueue();

	cout << "Pop out: " << queue.deQueue() << endl;

	queue.printQueue();

	//cout << "Pop out: " << queue.deQueue() << endl;

	//queue.printQueue();

	/* Test AVL Tree */
	printf( "\nTest AVL Tree: \n\n" );

	AVLTree avlTree;

	if (avlTree.insert( 3 ) &&
		avlTree.insert( 0 ) &&
		avlTree.insert( 1 ) &&
		avlTree.insert( 2 ) &&
		avlTree.insert( 4 ) &&
		avlTree.insert( -1 ))
	{
		printf( "Insertion succeeded!\n" );
	}

	if (avlTree.deleteNode( 1 ))
	{
		printf( "Deletion succeeded!\n" );
	}

	avlTree.printTree();


	/* Test Linked list */
	printf( "\n\nTest linked list: \n\n" );

	LinkedList list( 0 );

	list.append( 2 );
	list.append( 1 );
	list.append( -1 );

	printf( "\n\nBefore sorted: \n\n" );
	list.printList();

	list.sort();

	printf( "\n\nAfter sorted: \n\n" );
	list.printList();

	printf( "\n\nBefore deletion by index: \n\n" );
	list.printList();

	list.deleteByIndex( 3 );
	printf( "\n\nAfter deletion by index 3: \n\n" );
	list.printList();

	list.deleteByIndex( 0 );
	printf( "\n\nAfter deletion by index 0: \n\n" );
	list.printList();
	//std::cout << "size: " << +list.getSize() << std::endl;

	list.deleteByIndex( 1 );
	printf( "\n\nAfter deletion by index 1: \n\n" );
	list.printList();
	
	list.deleteByIndex( 0 );
	printf( "\n\nAfter deletion by index 0: \n\n" );
	list.printList();
	
	printf( "\n\nBefore sorted: \n\n" );
	list.printList();

	list.sort();

	printf( "\n\nAfter sorted: \n\n" );
	list.printList();

	/* Test stack */
	Stack s;

	s.push( 0 );
	s.push( 3 );

	getchar();

	return 0;
}
