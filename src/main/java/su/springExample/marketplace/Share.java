package su.springExample.marketplace;

import java.util.Objects;

public class Share {
    String name;
    int price;

    Share(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Share share = (Share) o;
        return Objects.equals(name, share.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
