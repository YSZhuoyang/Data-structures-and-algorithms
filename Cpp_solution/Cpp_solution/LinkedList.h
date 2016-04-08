#pragma once

#include <iostream>
#include <stdexcept>

#include "BasicDataStructures.h"

using namespace BasicDataStructure;

class LinkedList
{
public:
	LinkedList();
	LinkedList( int initialElement );
	~LinkedList();

	bool isEmpty();
	bool append( int value );
	bool insert( int index, int value );
	bool deleteByIndex( int index );
	void deleteByValue( int toBeDeleted );
	int getByIndex( int index );
	int getSize();
	void sort();
	void printList();

private:
	// Careful, after reposition the tail, the next pointer must
	// point to nullptr, otherwise even the next node is deleted
	// by calling 'delete', the next pointer will still point to
	// something
	void repositionTail();
	void destroyNode( ListNode* n );

	ListNode* head		=		nullptr;
	ListNode* tail		=		nullptr;
	int size			=		0;
};
