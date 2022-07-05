import java.util.HashMap;
import java.util.Map;

public class Account implements BankObject{

    //Special object Index that has number and name
    private final Index accountIndex;

    //List of ItemContract objects that confirm Item ownership
    private final Map<Long, ItemContract> itemContracts = new HashMap<>();

    public Account(String name) {
        this.accountIndex = new Index(name);
    }

    public Map<Long, ItemContract> getItemContracts() {
        return itemContracts;
    }

    /*
    Calls checkContracts method and if true received, calls transfer method calls it as many times as specified
    in the parameter 'amount'. If checkContracts returns false, shows the message or error.
     */
    public void send(int amount, Account purpose){
        if(checkContracts(amount)) {
            for (int i = 0; i < amount; i++) {
                transfer(purpose);
            }
        } else {
            System.out.println("Not enough amount of Items");
        }
    }

    /*
    Calls sell method of Bank object as many times as specified in parameter 'amount'
     */
    public void buy(int amount){
        for(int i = 0; i < amount; i++){
            b.sell(this);
        }
    }

    /*
    Get Item using Bank method getItems but filter result according to condition that it should have 'current'
    field equals to this account or get null if such Item is absent. Calls validateAndGet method and if it returns
    null, this method shows an error and calls for sendErrorRequest method. If validateAndGet returns not null,
    calls method transferAccepted.
     */
    public void transfer(Account to){

        Item i = b.getItems(this)
                .stream()
                .findFirst()
                .orElse(null);

        if(validateAndGet(i) != null) {
            transferAccepted(i, to);
        } else {
            System.out.println("Transfer error. The request has been set to the bank system.");
            sendErrorRequest();
        }

    }

    /*
    Sets 'current' field of Item @param 'i' as a @param 'a' value. Add new HashRecord object into 'history' field of 'i'
    Gets ItemContract object from 'itemContracts' field of this account, removes it and put it to 'a' object field 'itemContracts'.
     */
    private void transferAccepted(Item i, Account a) {
        i.setCurrent(a);
        i.getHistory().add( new HashRecord(accountIndex) );
        ItemContract c = itemContracts.get(i.getItemIndex());
        a.itemContracts.put(i.getItemIndex(), c);
        itemContracts.remove(i.getItemIndex());
    }

    //Calls method processErrorRequest of the Bank object
    private void sendErrorRequest() {
        b.processErrorRequest(this);
    }

    /*
    Returns null if @param 'i' is null
    Returns null if itemContracts doesn't contain such element with itemIndex as @param 'i' has.
    Gets ItemContract form itemContracts field according to index key value.
    Returns null if found ItemContract's field 'history' of doesn't equal to 'history' field of 'i'.
    Returns found ItemContract if any conditions triggerred.
     */
    private ItemContract validateAndGet(Item i) {

        if(i == null) return null;

        if(!itemContracts.containsKey(i.getItemIndex())) return null;

        ItemContract ic = itemContracts.get(i.getItemIndex());

        if(!ic.history().equals(i.getHistory())) return null;

        return ic;

    }

    //Returns true if this itemContracts field has size more than @param amount, and false if it hasn't
    public boolean checkContracts(int amount){
        return itemContracts.size() >= amount;
    }

    @Override
    public String toString(){
        return "("+accountIndex+") -> "+itemContracts;
    }




}
