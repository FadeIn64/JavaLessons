package su.springExample.marketplace;

import java.util.ArrayList;
import java.util.List;

public class MarketPlaceController {
    private final List<Robot> robots;

    public MarketPlaceController(Robot... robots) {
        this(List.of(robots));
    }
    public MarketPlaceController(List<Robot> robots) {
        this.robots = List.copyOf(robots);
    }

    public Share newShare(String name, int price){
        Share share = new Share(name, price);
        trigger(share);
        return share;
    }

    public void changePrice(Share share, int newPrice){
        share.price = newPrice;
        trigger(share);
    }

    private void trigger(Share sh){
        for(Robot robot : robots)
            robot.action(sh);
    }

}
