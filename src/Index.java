import java.util.Date;

public class Index{

    private final String indexCode;

    private final String name;

    public Index(String name) {
        this.indexCode = String.valueOf(Tech.getRandom(1, 10000000));
        this.name = name;
    }

    public Index(String name, String indexCode) {
        this.indexCode = indexCode;
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int CODE = 21;
        return (CODE + indexCode.hashCode() - (name.hashCode() / indexCode.hashCode()));
    }

    //Coding of this object hashCode using 'current time' and exponentiation
    //to provide an extra level of abstraction and safety of system.
    public String hideCode() {

        StringBuilder shc = new StringBuilder();
        char [] c = String.valueOf(this.hashCode()).toCharArray();

        for (char value : c) {
            long time = new Date().getTime();
            if (Character.isDigit(value)) {
                shc.append((int) ((time / 100) / Math.pow((int) value, 5.6)));
            } else {
                shc.append(1);
            }
        }

        return String.valueOf(shc);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString(){
        return "index: "+indexCode+", name: "+name;
    }
}