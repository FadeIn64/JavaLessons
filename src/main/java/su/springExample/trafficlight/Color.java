package su.springExample.trafficlight;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Color implements Colorable{

    String color;
    Colorable next;


    @Override
    public Colorable next() {
        return next;
    }

    @Override
    public String toString() {
        return color;
    }
}
