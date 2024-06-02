public class Chequing extends Account implements Taxable{

    private static final double OVERDRAFT_FEE = 5.50;
    private static final double OVERDRAFT_LIMIT  = -200.00;
    private static final double TAXABLE_INCOME = 3000.00;
    private static final double TAX_RATE = 0.15;

    public Chequing(String id, String name, double balance){
        super(id, name, balance);
    }

    public Chequing(Chequing source){
        super(source);
    }

  public void deposit(double amount){

    super.setBalance(super.round(super.getBalance() + amount));

  }
  /*Return true if the condition to subtract the over draft is true. Basically if the conditions of the app are met then return true. if not then return false */
  public boolean withdraw(double amount){

    if(super.getBalance() - amount < OVERDRAFT_LIMIT){
        return false;
    }else if(super.getBalance() - amount < 0){
        super.setBalance(super.round(super.getBalance() - amount - OVERDRAFT_FEE));
        return true;
    }else{
        super.setBalance(super.getBalance() - amount);
        return true;
    }
  }
  public void tax(double income){
    double tax = Math.max(0, income - TAXABLE_INCOME) * TAX_RATE; /*Note: if income is less than taxable income then nothing will be taxed because the greater number between 0 and a negative number is 0. */
    super.setBalance(super.round(super.getBalance() - tax));
}

    public Account clone(){
        return new Chequing(this); /*going to get all of it's values from the current object that is calling this method. 
        that is why we are using the this keyword in the constructor of the object. this represents an object */
    }
    
}
