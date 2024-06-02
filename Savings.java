public class Savings extends Account{

    private static final double WITHDRAWAL_FEE = 5.00;

    public Savings(String id, String name, double balance){
        super(id, name, balance);
    }

    public Savings(Savings source){
        super(source);
    }
    @Override
    public Account clone(){
        return new Savings(this);
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.round(super.getBalance() + amount));     
    }
    @Override
    /*Return true if the condition to deduct $5.5 is met */
    public boolean withdraw(double amount) {
       /*Updating the balance */
       super.setBalance(super.round(super.getBalance() - amount - WITHDRAWAL_FEE));
        return false;
    }
    
}
