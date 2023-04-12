package su.springExample.trafficlight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("traficlight")
public class TraficLight implements Colorable{

    private Colorable offColor;
    private Colorable onColor;

    private Colorable curColor;

    @Autowired
    public TraficLight(@Qualifier("blackColor") Colorable offColor, @Qualifier("redColor") Colorable onColor) {
        this.offColor = offColor;
        this.onColor = onColor;
        this.curColor = this.offColor;
    }

    public Colorable next(){
        curColor = curColor.next();
        return curColor;
    }

    public void on(){
        curColor = onColor;
    }

    public void off(){
        curColor = offColor;
    }
}
