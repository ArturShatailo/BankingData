public class HashRecord {

    private final String index;

    public HashRecord(Index index) {
        this.index = index.hideCode();
    }

    @Override
    public String toString() {
        return index;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) return true;

        if(o == null || this.getClass() != o.getClass()) return false;

        HashRecord hr = (HashRecord) o;

        return hr.index.equals(index);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
