package net.phantomthedev.jukeboxutil.utilities;

import ws.schild.jave.info.MultimediaInfo;
import ws.schild.jave.progress.EncoderProgressListener;

public class EncodeProgressListener implements EncoderProgressListener {
    @Override
    public void sourceInfo(MultimediaInfo info) {
        System.out.println("Analyzed " + info.toString());
    }

    @Override
    public void progress(int permil) {
        System.out.println("Progress: " + permil + "/1000");
    }

    @Override
    public void message(String message) {
        System.out.println("Encoder: " + message);
    }
}
