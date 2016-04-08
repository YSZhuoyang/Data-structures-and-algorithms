#include "LinkedList.h"


LinkedList::LinkedList()
{

}

LinkedList::LinkedList( int initialElement )
{
	head = new ListNode();
	head->value = initialElement;
	head->next = nullptr;
	tail = head;
	size = 1;
}


LinkedList::~LinkedList()
{
	destroyNode( head );
}

void LinkedList::repositionTail()
{
	if (isEmpty())
	{
		tail = nullptr;
	}
	else if (size == 1)
	{
		tail = head;
		tail->next = nullptr;
	}
	else
	{
		ListNode* iter = head;

		for (int i = 0; i < size - 1; i++)
		{
			iter = iter->next;
		}

		tail = iter;
		tail->next = nullptr;
	}
}

void LinkedList::destroyNode( ListNode* n )
{
	if (n->next != nullptr)
	{
		destroyNode( n->next );
		n->next = nullptr;
	}

	delete n;
}

bool LinkedList::isEmpty()
{
	return size == 0;
}

bool LinkedList::append( int value )
{
	if (isEmpty())
	{
		head = new ListNode();
		head->value = value;
		tail = head;
	}
	else
	{
		tail->next = new ListNode();
		tail->next->value = value;
		tail->next->next = nullptr;
		tail = tail->next;
	}

	size++;

	return true;
}

bool LinkedList::insert( int index, int value )
{
	if (index == 0)
	{
		ListNode* newHead = new ListNode();
		newHead->value = value;
		newHead->next = head;
		head = newHead;

		if (size == 0)
		{
			tail = head;
		}

		size++;

		return true;
	}
	else if (isEmpty() || size < index)
	{
		return false;
	}
	else if (index == size)
	{
		tail->next = new ListNode();
		tail->next->value = value;
		tail->next->next = nullptr;
		tail = tail->next;

		size++;

		return true;
	}

	ListNode* iter = head;

	for (int counter = 1; counter < index; counter++)
	{
		iter = iter->next;
	}
	
	ListNode* inserted = new ListNode();
	inserted->value = value;
	inserted->next = iter->next;
	iter->next = inserted;

	size++;

	return true;
}

bool LinkedList::deleteByIndex( int index )
{
	if (isEmpty() || index >= size)
	{
		return false;
	}
	else if (size == 1 && index == 0)
	{
		ListNode* toBeDeleted = head;
		delete toBeDeleted;
		size--;

		head = nullptr;
		tail = nullptr;

		return true;
	}
	else if (index == 0)
	{
		ListNode* toBeDeleted = head;
		head = head->next;
		delete toBeDeleted;
		size--;

		return true;
	}
	else if (index == size - 1)
	{
		ListNode* toBeDeleted = tail;
		size--;
		repositionTail();
		delete toBeDeleted;

		return true;
	}
	else
	{
		ListNode* iter = head;

		for (int i = 0; i < index - 1; i++)
		{
			iter = iter->next;
		}

		ListNode* toBeDeleted = iter->next;
		iter->next = iter->next->next;
		delete toBeDeleted;
		size--;
		repositionTail();

		return true;
	}
}

void LinkedList::deleteByValue( int toBeDeleted )
{
	if (isEmpty())
	{
		return;
	}
	else if (head->value == toBeDeleted)
	{
		ListNode* toBeDeleted = head;

		if (size == 1)
		{
			head = nullptr;
			tail = nullptr;

			delete toBeDeleted;
			size--;

			return;
		}
		else
		{
			head = head->next;
			delete toBeDeleted;
			size--;
		}
	}

	ListNode* iter = head;

	while (iter->next != nullptr)
	{
		if (iter->next->value == toBeDeleted)
		{
			ListNode* toBeDeleted = iter->next;
			iter->next = iter->next->next;
			delete toBeDeleted;
			size--;
		}
		else
		{
			iter = iter->next;
		}
	}

	repositionTail();
}

int LinkedList::getByIndex( int index )
{
	if (index >= size)
	{
		throw std::out_of_range("Out of bound exception");
	}
	else if (index == 0 && !isEmpty())
	{
		return head->value;
	}
	else if (index == size - 1)
	{
		return tail->value;
	}
	else
	{
		ListNode* iter = head;

		for (int i = 0; i < size - 1; i++)
		{
			iter = iter->next;
		}

		return iter->value;
	}
}

int LinkedList::getSize()
{
	return size;
}

void LinkedList::sort()
{
	if (isEmpty() || head->next == nullptr)
	{
		return;
	}

	ListNode* sortedHead = head;
	ListNode* sortedTail = head;
	ListNode* toBeSorted;
	ListNode* iter;

	head = head->next;
	sortedHead->next = nullptr;

	while (head != nullptr)
	{
		toBeSorted = head;
		head = head->next;
		toBeSorted->next = nullptr;

		if (toBeSorted->value < sortedHead->value)
		{
			toBeSorted->next = sortedHead;
			sortedHead = toBeSorted;
		}
		else if (toBeSorted->value > sortedTail->value)
		{
			sortedTail->next = toBeSorted;
			sortedTail = toBeSorted;
		}
		else
		{
			iter = sortedHead;

			if (iter->next != nullptr && iter->next->value < toBeSorted->value)
			{
				iter = iter->next;
			}

			toBeSorted->next = iter->next;
			iter->next = toBeSorted;
		}
	}

	head = sortedHead;
	tail = sortedTail;
}

void LinkedList::printList()
{
	ListNode* iter = head;

	while (iter != nullptr)
	{
		std::cout << "Value: " << iter->value << std::endl;
		iter = iter->next;
	}
}
