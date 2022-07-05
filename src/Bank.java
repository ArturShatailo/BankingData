import java.util.ArrayList;

public class Bank {

    //List of all generated Item objects
    private final ArrayList<Item> general = new ArrayList<>();

    private final Index bankIndex = new Index("Central-bank", "472-01-21");

    //Item index counter
    private long itemIndex = 0;

    //Adds new Item object (with null as 'current' field and result of method 'addItemIndex' as itemIndex field) into general List
    //as many times as specified in 'amount' parameter
    public void fillBank(long amount){
        for(long i = 0; i < amount; i++){
            general.add(new Item(null, addItemIndex()));
        }
    }

    //Returns itemIndex field increased by 1
    private long addItemIndex() {
        return itemIndex++;
    }

    //Returns List of elements from general List that has @param 'a' as a 'current' field value
    public ArrayList<Item> getItems(Account a) {

        return new ArrayList<>(
                general.stream()
                        .filter(n -> n.getCurrent() != null && n.getCurrent().equals(a)).toList());
    }

    //Sets 'current' field of Item object, found with method findItems, as @param 'to'. Adds new HashRecord into 'history' field of
    //found Item. Puts new ItemContract into @param 'to' itemContracts collection.
    public void sell(Account to){
        Item i = findItems();
        i.setCurrent(to);
        i.getHistory().add( new HashRecord(bankIndex) );
        to.getItemContracts().put(i.getItemIndex(), new ItemContract(i.getHistory()));
    }

    //Cals for checkItems method. If it returns null, shows error message and adds new Item into 'general', returns created value.
    //If checkItems returns not null, the result will be returned by the current method.
    private Item findItems() {

        Item zero = checkItems();
        if(zero == null){
            System.out.println("Bank is empty, be careful");
            fillBank(1);
            zero = general.get(general.size() - 1);
        }
        return zero;
    }

    //Finds and returns Item from 'general' with 'current' field null, if it's absent, returns null
    private Item checkItems() {
        return general.stream().filter(n -> n.getCurrent() == null).findAny().orElse(null);
    }

    //Calls blockAccount method
    public void processErrorRequest(Account a) {
        blockAccount(a);
    }

    //Removes all Item objects from 'general' that have 'current' field equal to @param 'a', clear itemContracts field of @param 'a'
    //Calls fillBank method with amount parameter that is equal to deleted Item objects amount.
    private void blockAccount(Account a) {
        long s = getItems(a).size();
        general.removeAll(getItems(a));
        a.getItemContracts().clear();
        fillBank(s);
    }

}
