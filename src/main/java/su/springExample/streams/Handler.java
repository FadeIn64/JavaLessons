package su.springExample.streams;

public interface Handler<InType, OutType>{
    OutType handle(InType inData);
}
