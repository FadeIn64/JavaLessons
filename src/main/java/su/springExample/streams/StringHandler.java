package su.springExample.streams;

public class StringHandler implements Handler<String, String>{
    @Override
    public String handle(String inData) {
        return inData+inData;
    }
}
