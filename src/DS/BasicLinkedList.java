package DS;

public class BasicLinkedList<X>
{
    private Node first,last;
    private int counter;

    public BasicLinkedList()
    {
        //initialize it to empty list
        first=null;
        last=null;
        counter=0;
    }

    public int size()
    {
        return counter;
    }

    public void add(X item)
    {
        if (first==null)
        {
            first=new Node(item);
            last=first;
        }
        else
        {
            Node newItem=new Node(item);
            last.setNext(newItem);
            // set last to point to the new item
            last=newItem;
        }

        counter++;
    }

    public X remove()
    {
        if (first==null)
            throw new IllegalStateException("The LinkedList is empty");


        X item=first.getNodeItem();
        first=first.getNext();
        counter--;
        return item;

    }

    public void insert (X item,int position)
    {
        if (position > size())
            throw new IllegalArgumentException("The position is greater than the size of the LinkedList");

        Node current = first;

        for (int i=1; i<position && current !=null; i++)
        {
            current=current.getNext();
        }

        Node newNode=new Node(item);
        //Save the node to make it the next for the new node
        Node nextNode=current.getNext();
        // Set new node in the position
        current.setNext(newNode);
        newNode.setNext(nextNode);
        counter++;
    }

    public X removeAt(int position)
    {
        if (first==null)
            throw new IllegalStateException("The LinkedList is empty");

        Node current =first;
        Node previousNode=first;

        for (int i=1;i<position && current !=null; i++)
        {
            previousNode = current;
            current=current.getNext();
        }

        X nodeItem = current.getNodeItem();
        previousNode.setNext(current.getNext());
        counter--;
        return nodeItem;
    }

    public X get(int position)
    {
        if (first==null)
            throw new IllegalStateException("The LinkedList is empty");


        Node current = first;

        for (int i=1; i<size() && current !=null; i++)
        {
            if (i==position)
            {
                return current.getNodeItem();
            }
            current=current.getNext();
        }

        throw new IllegalStateException("The item was not found");
    }

    public int find(X item)
    {
        if (first==null)
            throw new IllegalStateException("The LinkedList is empty");

        Node current = first;

        for (int i=1; i<size() && current !=null; i++)
        {
            if (current.getNodeItem().equals(item))
            {
                return i;
            }
            current=current.getNext();
        }

        return -1;
    }


    @Override
    public String toString()
    {
        StringBuffer string=new StringBuffer();
        Node current=first;

        while (current !=null)
        {
            string.append(current.getNodeItem());
            current=current.getNext();

            if (current != null)
                string.append(", ");
        }

        return string.toString();
    }


    private class Node
    {
        private Node next;
        private X nodeItem;

        public Node(X item)
        {
            this.next=null;
            this.nodeItem=item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public X getNodeItem() {
            return nodeItem;
        }
    }
}

