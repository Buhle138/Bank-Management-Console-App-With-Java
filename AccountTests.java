import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;



public class AccountTests {

   Account[] accounts;

    @Before
    public void setUp(){
        accounts = new Account[]{
            new Chequing("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", 1524.51),
            new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", 2241.60),
            new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Phoebe Buffay", 2537.31)
        };
    
    }

    /*Testing for  withdrawals */

    /*Making sure the correct amount is withdrawn */
    @Test
    public void withdrawl(){
        /*withdrawing from the checking account that is at index zero (0) */
        accounts[0].withdraw(1440);
        assertEquals(84.51, accounts[0].getBalance());
    }

    /*Unit test for when the account holder withdraws more than they should must be charged $5.5 fee */
    @Test
    public void overdraft(){
        accounts[0].withdraw(1534.43);
        assertEquals(-15.42, accounts[0].getBalance());
    }

    /*Making sure the overdraft does not exceed the $200 limit */
    @Test
    public void overdraftLimit(){
        accounts[0].withdraw(1726);
        assertEquals(1524.51, accounts[0].getBalance()); /*return false if it something we dont expect and return true if it something we expect. */
    }
    /*Making sure we charge a $5 fee if they attempt to withdraw from savings account. */
    @Test
    public void withdrawlFee(){
        accounts[1].withdraw(100);
        assertEquals(2136.60, accounts[1].getBalance());
    }
    @Test
    /*Testing to make sure that for a loan account every withdrawal is charged a fixed interest rate of 2% on the amount*/
    public void withdrawalInterest(){
        accounts[2].withdraw(2434.31);
        assertEquals(5020.31, accounts[2].getBalance());

    }
    /*Testing to make sure that every withdrawal can't exceed $10 000 */
    @Test
    public void withdrawalLimit(){
        accounts[2].withdraw(7463.69);
        assertEquals(2537.31, accounts[2].getBalance());
    }
    /*Testing for deposits */
    @Test
    public void deposit() {
        accounts[0].deposit(5000);
        assertEquals(6524.51, accounts[0].getBalance());
    }
    /*Testing to make sure that when we deposit money the money should decrease the loan amount. */
    @Test
    public void loanDeposit(){
        accounts[2].deposit(1000);
        assertEquals(1537.31, accounts[2].getBalance());
    }
    /*Testing to make sure that a chequing account is taxable. Income that exceeds $3,000 is taxed 15%. */
    @Test
    public void incomeTax(){
        double income = 4000;
        accounts[0].deposit(income);
        ((Taxable)accounts[0]).tax(income);
        assertEquals(5374.51, accounts[0].getBalance());
       
    }
}
