package su.springExample.streams;

public class StringDownloader implements Downloader<String>{

    public String connect;

    public StringDownloader(String connect) {
        this.connect = connect;
    }

    @Override
    public String load() {
        return connect;
    }
}
