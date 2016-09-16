#pragma once

#include <stdexcept>

#include "BasicDataStructures.h"
#include "LinkedList.h"


using namespace BasicDataStructure;

class Stack
{
public:
	Stack();
	~Stack();

	bool isEmpty();
	bool push(int newElement);
	int pop();
	int peak();


private:
	LinkedList* list = nullptr;
};
