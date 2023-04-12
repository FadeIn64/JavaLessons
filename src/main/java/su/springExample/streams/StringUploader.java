package su.springExample.streams;

public class StringUploader implements Uploader<String>{
    @Override
    public void upload(String inData) {
        System.out.println(inData);
    }
}
