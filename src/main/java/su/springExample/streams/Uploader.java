package su.springExample.streams;

public interface Uploader<InType> {

    void upload(InType inData);

}
