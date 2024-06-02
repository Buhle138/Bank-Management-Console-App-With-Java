import java.text.DecimalFormat;
public abstract class Account{ /*This is a parent class */
    private String id;
    private String name;
    private double balance;


    public Account(String id, String name, double balance) {
        if(id == null || id.isBlank() || name == null || name.isBlank()){
            throw new IllegalArgumentException("Not supposed to be null or blank.");
        }
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Account(Account source) {
        this.id = source.id;
        this.name = source.name;
        this.balance = source.balance;
    }



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("Not supposed to be null or blank");
        }
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()){
          throw new IllegalArgumentException("Invalid Name!");
        }
        this.name = name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract void deposit(double amount);
    public abstract boolean withdraw(double amount);
    public abstract Account clone(); /*Forcing objects to override clone in order to get a copy of the object that calls it. */

    protected double round(double amount) {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.parseDouble(formatter.format(amount));
    }

    public String toString() {
        return (this.getClass().getSimpleName()) + "    " +
            "\t" + this.getId() + "" +
            "\t" + this.getName()+ "" +
            "\t$" + this.getBalance() + "";
    }
}