#include "Stack.h"



Stack::Stack()
{
	list = new LinkedList();
}

Stack::~Stack()
{
	delete list;
	list = nullptr;
}

bool Stack::isEmpty()
{
	return list->isEmpty();
}

bool Stack::push( int newElement )
{
	list->insert( 0, newElement );

	return true;
}

int Stack::peak()
{
	if (isEmpty())
	{
		throw std::out_of_range("Out of bound exception");
	}
	else
	{
		return list->getByIndex( 0 );
	}
}

int Stack::pop()
{
	if (isEmpty())
	{
		throw std::out_of_range( "Out of bound exception" );
	}
	else
	{
		int res = list->getByIndex( 0 );
		list->deleteByIndex( 0 );

		return res;
	}
}
