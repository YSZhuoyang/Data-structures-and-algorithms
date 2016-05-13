#pragma once

#include <iostream>
#include <exception>

using namespace std;

class Queue
{
public:
	Queue();
	Queue( int c );
	~Queue();

	void enQueue( int element );
	int deQueue();
	int getSize();
	int getCapacity();
	void printQueue();
	bool isEmpty();

private:
	void resize( int c );

	int* elementArray;
	unsigned int size;
	unsigned int capacity;
	unsigned int head;
	unsigned int tail;
};
