import java.util.*;


public class PriorityQueue extends AbstractCollection
{
    private static class DefaultComparator implements Comparator
    {
        public int compare (Object o1, Object o2)
        {
            return ((Comparable) o1).compareTo(o2);
        }
    }
    private Comparator myComp = new DefaultComparator();
    private int        mySize;
    private ArrayList  myList;

 
    private class PQItr implements Iterator
    {
        public Object next()
        {
            return myList.get(myCursor);
        }
        
        public boolean hasNext()
        {
            return myCursor <= mySize;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("remove not implemented");
        }
        
        private int myCursor = 1;
    }

    
    public PriorityQueue()
    {
        myList = new ArrayList(32);
        myList.add(null);             // first slot has index 1
        mySize = 0;
    }

 
    public PriorityQueue(Comparator comp)
    {
        this();
        myComp = comp;
    }

    public PriorityQueue(Collection coll)
    {
        this();
        myList.addAll(coll);
        mySize = coll.size();

        for(int k=coll.size()/2; k >= 1; k--)
        {
            heapify(k);
        }
    }

    
    public boolean add(Object o)
    {
        myList.add(o);        // stored, but not correct location
        mySize++;             // added element, update count
        int k = mySize;       // location of new element

        while (k > 1 && myComp.compare(myList.get(k/2), o) > 0)
        {
            myList.set(k, myList.get(k/2));
            k /= 2;
        }
        myList.set(k,o);
        
        return true;
    }

    
    public int size()
    {
        return mySize;
    }

   
    public boolean isEmpty()
    {
        return mySize == 0;
    }

    
    public Object remove()
    {
        if (! isEmpty())
        {
            Object hold = myList.get(1);
            
            myList.set(1, myList.get(mySize));  // move last to top
            myList.remove(mySize);              // pop last off
            mySize--;
            if (mySize > 1)
            {
                heapify(1);
            }
            return hold;
        }
        return null;
    }

    
    public Object peek()
    {
        return myList.get(1);
    }

    public Iterator iterator()
    {
        return new PQItr();
    }

    private void heapify(int vroot)
    {
        Object last = myList.get(vroot);
        int child, k = vroot;
        while (2*k <= mySize)
        {
            child = 2*k;
            if (child < mySize &&
                        myComp.compare(myList.get(child),
                                       myList.get(child+1)) > 0)
            {
                child++;
            }
            if (myComp.compare(last, myList.get(child)) <= 0)
            {
                break;
            }
            else
            {
                myList.set(k, myList.get(child));
                k = child;
            }
        }
        myList.set(k, last);
    }
}