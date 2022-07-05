import java.util.LinkedList;

public record ItemContract(LinkedList<HashRecord> history) {

    @Override
    public String toString() {
        return "history: " + history;
    }

}
