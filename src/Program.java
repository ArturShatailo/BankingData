import java.util.LinkedList;

public class Program implements BankObject{

    public static void start() {

        b.fillBank(30L);

        System.out.println("This is demonstration of the program.");
        System.out.println("\nThree accounts created:");
        Account account = new Account("Denis");
        Account account1 = new Account("Andrew");
        Account account2 = new Account("Forbes");

        System.out.println(account);
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\nEach account get default amount of Items: first - 5; second - 3, third - 1");
        account.buy(5);
        System.out.println(account);

        account1.buy(3);
        System.out.println(account1);

        account2.buy(1);
        System.out.println(account2);


        System.out.println("\nFirst account sends 2 Items to second account");
        account.send(2, account1);
        System.out.println(account);
        System.out.println(account1);
        System.out.println(account2);


        System.out.println("\nSecond account sends 5 Items to third account");
        account1.send(5, account2);
        System.out.println(account);
        System.out.println(account1);
        System.out.println(account2);


        System.out.println("\nNow, first account try to generate false Item");
        LinkedList<HashRecord> h = new LinkedList<>(account2.getItemContracts().get(5L).history());
        account.getItemContracts().put(5L, new ItemContract(h));
        System.out.println(account);
        System.out.println(account1);
        System.out.println(account2);


        System.out.println("\nAnd then try to send all Items to third account and system shows error and clears fraudulent account");
        account.send(4, account2);
        System.out.println(account);
        System.out.println(account1);
        System.out.println(account2);

    }
}
