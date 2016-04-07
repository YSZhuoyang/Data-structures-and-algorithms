#include "AVLTree.h"
#include "BurstBalloons.h"

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

	AVLTree avlTree( 3 );

	avlTree.insert( 0 );
	avlTree.insert( 1 );
	avlTree.insert( 2 );
	avlTree.insert( 4 );
	avlTree.insert( -1 );

	avlTree.printTree();

	getchar();

	return 0;
}
