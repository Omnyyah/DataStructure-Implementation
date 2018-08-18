package DS;

import java.util.ArrayList;
import java.util.Collections;

public class BasicStack<X>
{
    //private X[] data;
    //changed the array to ArrayList because of array size limitation
    private ArrayList<X> data;
    private int stackPointer;

    public  BasicStack()
    {
        //data=(X[]) new Object[1000];
        data = new ArrayList<>();
        stackPointer=0;
    }

    public void push(X item)
    {
        data.add(item);
        stackPointer++;
        //data[stackPointer++]=item;
    }

    public X pop()
    {
        if (stackPointer==0)
            throw new IllegalStateException("Empty Stack");
        //return data[--stackPointer];
        X item=data.get(--stackPointer);
      //  stackPointer = stackPointer-1;
        return item;
    }

    public X peek()
    {
       // return data[stackPointer-1];
        return data.get(stackPointer-1);
    }

    public boolean contains(X item)
    {
        boolean found = false;
        for (int i=0;i<data.size();i++)
        {
            if (data.get(i).equals(item))
            {
                found = true;
                break;
            }
        }


        return found;
    }

//    public X access(X item)
//    {
//        while (stackPointer > 0)
//        {
//            X tempItem=pop();
//            if(item.equals(tempItem))
//            {
//                return tempItem;
//            }
//        }
//        throw new IllegalArgumentException(" This item " + item + " is not on the Stack " );
//    }

    public boolean empty()
    {
        return stackPointer == 0;
    }

    public int size()
    {
        return stackPointer;
    }


    //If you need to print the stack elements

    @Override
    public String toString()
    {
        StringBuilder string=new StringBuilder();
        for (X x:data)
        {
            string.append(" " + x);
        }

        return string.toString();
    }
}
