package JavaClasses;

public class MyListOfObjects{
    Node first;
    int length;

    public MyListOfObjects(){
        first = null;
        length= 0;
    }

    public String toString(){
        String s = "";
        Node temp = first;
        int index = 0;
        while (temp != null){   //loop until end of list
            s += "\n"+index+": "+temp.data;
            temp = temp.next;  //go to next item in list
            index++;  //increment the index
        }
        return s;
    }

    public boolean add(Object newEntry){ // add at beginning of list
        Node temp = new Node(newEntry, first);
        first = temp;
        length++;  //increment the length of the List
        return true;
    }

    public void add(int index,Object newEntry){
        Node previous = first;
        Node current = first;

        if(index < 0 || index > length){   //invalid index
            return;
        }
        if(index == 0){
            add(newEntry);
            return;
        }
        int positionInList = 1; //second node in list
        current=current.next; //current moved on to next item

        while (previous != null && positionInList < index) {
            positionInList++;
            current = current.next;
            previous = previous.next;
        }

        if (positionInList == index) {
            Node temp = new Node( newEntry, current);
            previous.next = temp;
            length++;
            return;
        }
        return;
    }

    public Object remove(int index){
        Node previous = first;
        Node current = first;

        if(index < 0 || index >= length){   //invalid index
            return null;
        }
        if(index == 0){   //remove first item
            Node temp = first;
            first = first.next;
            length--;
            return temp;
        }
        int positionInList = 1; //second node in list
        current=current.next; //current moved on to next item

        while (previous != null && positionInList < index) {
            positionInList++;
            current = current.next;
            previous = previous.next;
        }

        if (positionInList == index) {
            Object temp =  current.data;
            previous.next = current.next;
            length--;
            return temp;
        }
        return null;
    }

    public void clear(){
        first = null;
        length= 0;
    }
    public Object set(int index, Object anEntry) // like replace
    {
        return ""; // temp fix
    }
    public Object get(int index) // like getEntry
    {
        if(index<0 || index >= length) // invalid index
            return "";
        Node temp = first;
        int position = 0;
        while(position != index){
            temp = temp.next;
            position++;
        }
        return temp.data;
    }

    public boolean contains(Object anEntry){
        return false;
    }
    public int size() // like getLength
    {
        return length;
    }

    public boolean isEmpty(){
        if(length == 0)
            return true;
        return false;
    }

    /*******************************************************************************/

    public class Node{
        private Object data;
        private Node next;

        private Node(Object data, Node n){
            this.data = data;
            next = n;
        }

    } //end of inner Node class
    /*******************************************************************************/


}
