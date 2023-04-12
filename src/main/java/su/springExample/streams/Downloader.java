package su.springExample.streams;

public interface Downloader<OutType> {
    OutType load();
}
