package DS;

import java.util.ArrayList;

public class BasicHashTable<X,Y>
{
    private HashEntry[] data;
    // is not like the size, Capacity represents how big the HashTable is
    private int capacity,size;

    public BasicHashTable(int tableSize)
    {
        this.capacity=tableSize;
        this.data=new HashEntry[this.capacity];
        this.size=0;
    }

    public Y get(X key)
    {
     int hash=calculateHash(key);
     if (data[hash] == null)
         throw new IllegalStateException("No value has been found for this key:" + key);
     else {
         return (Y)data[hash].getValue();
     }
    }

    public void put(X key,Y value)
    {
        int hash=calculateHash(key);
        data[hash]= new HashEntry<>(key,value) ;
        size++;
    }

    public Y delete(X key)
    {
        Y value=get(key);

        if (value != null)
        {
            int hash=calculateHash(key);
            data[hash]=null;
            size--;
            hash=(hash+1) % this.capacity;

            // After removing the given key, we need to re-hash the elements and keep the hash algorithm clean
            while (data[hash] != null)
            {
                HashEntry h=data[hash];
                data[hash]=null;
                put((X)h.getKey(),(Y)h.getValue());
                size--;
                hash=(hash+1) % this.capacity;
            }
        }

        return value;
    }


    //The big O here is approximately constant depending on how we calculate the hash code

    public boolean hasKey(X key)
    {
        int hash=calculateHash(key);

        if (data[hash] == null)
            return false;
        else {
            if(data[hash].getKey().equals(key))
                return true;
        }
        return false;
    }


    //The big O here is linear since we have a for loop

    public boolean hasValue(Y value)
    {
        for (int i=0; i<this.capacity; i++)
        {
            HashEntry entry=data[i];
            if (entry != null && entry.getValue().equals(value))
                return true;
        }
        return false;
    }

    public int size()
    {
        return this.size;
    }

    private int calculateHash(X key)
    {
        //find a space in the table using a modulus operation
        int hash=(key.hashCode() % this.capacity );
        while (data[hash] != null && !data[hash].getKey().equals(key))
        {
            //move up in case we have a collision, we need an empty spot!!
            hash = (hash+1) % this.capacity;
        }
        return hash;
    }



    private class HashEntry<X,Y>
    {
        private X Key;
        private Y Value;

        public HashEntry(X Key,Y Value)
        {
            this.Key=Key;
            this.Value=Value;
        }

        public X getKey() {
            return Key;
        }

        public void setKey(X key) {
            Key = key;
        }

        public Y getValue() {
            return Value;
        }

        public void setValue(Y value) {
            Value = value;
        }
    }
}
