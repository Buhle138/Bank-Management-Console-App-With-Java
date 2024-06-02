public class Main {

    static Bank bank = new Bank();

    public static void main(String[] args) {


        createObject(new String[] {"Chequing","f84c43f4-a634-4c57-a644-7602f8840870","Michael Scott","1524.51"}))


        // try {
        //     Account account = createObject(new String[] {"Chequing","f84c43f4-a634-4c57-a644-7602f8840870","Michael Scott","1524.51"});
        //     System.out.println();
        // }catch(Exception e){

        //     System.out.println(e.getMessage());

        // }

     
      
    }

    public static Account createObject(String[] values) throws Exception{
        switch((values[0])){
            case "Chequing" :  return (Account)(new Chequing (values[1], values[2], Double.parseDouble(values[3])));
            case "Savings" :  return (Account) new Savings (values[1], values[2], Double.parseDouble(values[3]));
            case "Loan" :  return (Account) new Loan (values[1], values[2], Double.parseDouble(values[3]));
        }
    }

    
}
