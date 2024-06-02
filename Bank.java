import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

/*This class will be managing transactions */
public class Bank {

    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public Bank(){
        this.accounts = new ArrayList<Account>(); /*Array list that stores account objects */
        this.transactions = new ArrayList<Transaction>(); /*Array list that stores transaction objects */
    }

    /*creating a method that returns an array of transactions that match a particular account. */
    public Transaction[] getTransactions(String accountId){
        List<Transaction> list = this.transactions.stream()
        .filter((transaction) -> transaction.getId().equals(accountId)) /*going through the arrayList (transactions) to filter the id's that match the id's that are being passed in. */
        .collect(Collectors.toList()); /*collecting them and storing them into a list */

        return list.toArray(new Transaction[list.size()]); /*Return that list as an array */
    }

    /*Getting the account for a particular transaction. */
    public Account getAccount(String transactionId){
        return accounts.stream()
        .filter((account) -> account.getId().equals(transactionId)) /*There must only be a single transaction which matches the id. */
        .findFirst() /*find the first element in the filtered array. */
        .orElse(null); /*if we can't find anything we will say null */
    }

    public void addAccount(Account account){
        this.accounts.add(account.clone()); /* add a clone because you are not allowed to add objects to an abstract class. You can only add clones.*/
    }

    private void addTransaction(Transaction transaction){ /*Making this method private because we only want the bank class to be responsible for the transactions */
        this.transactions.add(new Transaction(transaction));
    }

    public void executeTransaction(Transaction transaction){
        switch(transaction.getType()){
            case WITHDRAW : withdrawTransaction(transaction); break;
            case DEPOSIT : depositTransaction(transaction); break;
        }
    }

    /*The logic behind the withdrawTransaction method is if the withdrawal is successful (it returns true) then if must be added to the transactions lists*/
    private void withdrawTransaction(Transaction transaction){
        if(getAccount(transaction.getId()).withdraw(transaction.getAmount())){ /*Note: withdraw method returns a boolean (true or false) */
            addTransaction(transaction);
        }
    }

    private void depositTransaction(Transaction transaction){
        getAccount(transaction.getId()).deposit(transaction.getAmount());
        addTransaction(transaction);
    }

    private double getIncome(Taxable account){
        Transaction[] transactions = getTransactions(((Chequing)account).getId()); /*Getting every single transaction that belongs to Taxable class */
        /*Note: we are type casting the taxable account because Taxable does not have the getId() method. */
        return Arrays.stream(transactions)
        .mapToDouble((transaction) -> {
            switch (transaction.getType()){
                case WITHDRAW: return -transaction.getAmount();
                case DEPOSIT: return transaction.getAmount();
                default: return 0;
            }
        }).sum(); /*Will return the sum of every element in the stream. */
    }

    public void deductTaxes(){
        for(Account account : accounts){
            if(Taxable.class.isAssignableFrom(account.getClass())){ /*If it implements the Taxable class */
                Taxable taxable = (Taxable)account; /*Then we type cast the account object for it to get access to the tax method. */
                taxable.tax(getIncome(taxable));
            }
        }
    }
    
}
