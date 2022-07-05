import java.util.LinkedList;

public class MyLinkedList<HashRecord> extends LinkedList<HashRecord> {

    @Override
    public boolean equals(Object o) {

        if(o == this) return true;

        if(o == null || this.getClass() != o.getClass()) return false;

        MyLinkedList<HashRecord> arrayList = (MyLinkedList<HashRecord>) o;

        if(arrayList.size() != this.size()) return false;

        for(HashRecord h : arrayList){
            for(HashRecord h1 : this){
                if (!h.equals(h1)) return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
