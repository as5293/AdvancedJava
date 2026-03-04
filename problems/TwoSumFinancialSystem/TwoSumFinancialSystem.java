import java.util.*;

class Transaction {

    int id;
    int amount;
    String merchant;
    String time;

    Transaction(int id,int amount,String merchant,String time){

        this.id=id;
        this.amount=amount;
        this.merchant=merchant;
        this.time=time;

    }

}

public class TwoSumFinancialSystem {

    // Two sum detection
    static void findTwoSum(List<Transaction> transactions,int target){

        HashMap<Integer,Transaction> map = new HashMap<>();

        for(Transaction t:transactions){

            int complement = target - t.amount;

            if(map.containsKey(complement)){

                System.out.println(
                        "Match Found: Transaction "
                        +map.get(complement).id
                        +" + "+t.id
                        +" = "+target);

            }

            map.put(t.amount,t);

        }

    }

    public static void main(String[] args){

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(1,500,"Store A","10:00"));
        transactions.add(new Transaction(2,300,"Store B","10:15"));
        transactions.add(new Transaction(3,200,"Store C","10:30"));

        int target = 500;

        findTwoSum(transactions,target);

    }

}