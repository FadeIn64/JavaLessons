package su.springExample.marketplace;

import java.util.Objects;

public class SimpleRobot  implements Robot{

    private String shareName;
    private int count;
    private int price;

    public SimpleRobot(String name){
        this.shareName = name;
    }



    @Override
    public void action(Share share){

        if (!shareName.equals(share.name))
            return;
        if (count == 0) {
            count = 100;
            price = share.price;
            System.out.println("BUY " + price);
        }
        else if (price + 5 < share.price || price - 5 > share.price) {
            System.out.println("SELL: " + share.price*count);
            count = 0;
            price = 0;
        }

    }
}
