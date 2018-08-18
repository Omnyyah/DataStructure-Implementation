package DS;

import java.util.ArrayList;

public class BasicQueue<X>
{
    private ArrayList<X> data;
    private int front,end;

    public BasicQueue()
    {
        // default size
        this(1000);
    }

    public BasicQueue(int size)
    {
        this.front=-1;
        this.end=-1;
        data = new ArrayList<>(size);
    }

    public int size()
    {
        return ( front==-1 && end==-1) ? 0 : (end-front+1);
    }

    public void enqueue(X item)
    {
        if( ( (end +1 ) % data.size() ) == front ) {
            throw new IllegalStateException("You can not add: " + item + "Queue is full");
        }
        else if (size() == 0) {
            front++;
            end++;
            data.add(end,item);
        }
        else {
            end++;
            data.add(end,item);
        }

    }

    public X dequeu()
    {
        X item=null;
        if (size() == 0) {
            throw new IllegalStateException("Queue is empty!");
        }
        // to check if item is the last item, we need to reset the queue
        else if (front == end )
        {
            item=data.get(front);
            front =-1;
            end =-1;
        }
        else
        {
            item=data.get(front);
            front++;
        }
        return item;
    }

    public boolean contains(X item)
    {
        boolean found=false;
        if (size()==0)
            return found;
        else {
            for (int i = front; i < end; i++) {
                if (data.get(i).equals(item))
                    found=true;
                break;
            }
        }
        return found;
    }

    public X access(int position)
    {
     if (size()==0)
         throw new IllegalArgumentException("The queue is Empty");
     else if (position > size())
         throw new IllegalArgumentException("The position is greater than the queue size");


     // set i equal to zero, because we need to loop throw the queue and the front not always equal to zero
     int i=0;
     for (int j=front; j<end; j++) {
         if (i == position) {
             return data.get(j);
         }
         i++;
     }

     throw new IllegalArgumentException("The item at the position:" + position + " is not found ");
    }

}
