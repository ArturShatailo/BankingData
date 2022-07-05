public class Item {

    //List of HashRecord objects that added each time when the Item changes 'current' field
    private final MyLinkedList<HashRecord> history;

    //The account of holder that has ItemContract object for this Item
    private Account current;

    //index of item that is generated during Item creation
    private final long itemIndex;

    public Item(Account current, long index) {
        this.history = new MyLinkedList<>();
        this.current = current;
        this.itemIndex = index;
    }

    public MyLinkedList<HashRecord> getHistory() {
        return this.history;
    }

    public void setCurrent(Account current) {
        this.current = current;
    }

    public Account getCurrent() {
        return current;
    }

    public long getItemIndex() {
        return itemIndex;
    }
}
