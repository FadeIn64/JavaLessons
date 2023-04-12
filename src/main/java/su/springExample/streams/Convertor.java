package su.springExample.streams;

public class Convertor<InType, OutType>{

    private Downloader<InType> downloader;
    private Handler<InType, OutType> handler;
    private Uploader<OutType> uploader;

    public Convertor(Downloader<InType> downloader, Handler<InType, OutType> handler, Uploader<OutType> uploader) {
        this.downloader = downloader;
        this.handler = handler;
        this.uploader = uploader;
    }

    public void convert(){
        uploader.upload(handler.handle(downloader.load()));
    }
}
