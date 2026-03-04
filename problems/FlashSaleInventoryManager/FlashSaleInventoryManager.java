import java.util.*;

public class FlashSaleInventoryManager {

    // productId -> stock
    static HashMap<String, Integer> inventory = new HashMap<>();

    // waiting list
    static Queue<Integer> waitingList = new LinkedList<>();

    public static void main(String[] args) {

        inventory.put("IPHONE15_256GB", 100);

        checkStock("IPHONE15_256GB");

        purchaseItem("IPHONE15_256GB", 12345);
        purchaseItem("IPHONE15_256GB", 67890);

        // simulate stock exhaustion
        for(int i=0;i<100;i++){
            purchaseItem("IPHONE15_256GB", 90000+i);
        }

    }

    // check stock
    static void checkStock(String productId){

        int stock = inventory.getOrDefault(productId,0);

        System.out.println(productId+" -> "+stock+" units available");

    }

    // purchase product
    static synchronized void purchaseItem(String productId,int userId){

        int stock = inventory.getOrDefault(productId,0);

        if(stock>0){

            stock--;

            inventory.put(productId,stock);

            System.out.println("User "+userId+" purchase SUCCESS, remaining "+stock);

        }
        else{

            waitingList.add(userId);

            System.out.println("User "+userId+" added to waiting list position "+waitingList.size());

        }

    }

}