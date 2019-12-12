package Interfaces;

public interface MyListOfInterfaces {
    public boolean add(Object newEntry);
    public void add(int index, Object newEntry);
    public Object remove(int index);
    public void clear();
    public Object set(int index, Object anEntry);
    public Object get(int index);
    public boolean contains(Object anEntry);
    public int size();
    public boolean isEmpty();
}
