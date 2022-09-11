package net.phantomthedev.jukeboxutil.utilities;

import net.phantomthedev.jukeboxutil.JukeboxUtil;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;

/**
 * Used to convert the .mp3 files to .ogg if there are any
 */
public class CustomConverter {

    //private static final String MUSIC_PARENT_DIRECTORY = Launcher.class.getResourceAsStream("../../discs/");

    public enum Channel {
        MONO,
        STEREO
    }

    public CustomConverter() {
        findAllFiles();
    }

    public void findAllFiles() {

    }

    /**
     * Attempts to encode all the non .ogg files that it found
     */
    public void bulkEncode() {

    }



    public static void beginEncode(File source, File target, Channel channel) {
        JukeboxUtil.LOGGER.info("Beginning Encode of " + source.getName());
        try{
            new Encoder().encode(new MultimediaObject(source), target,
                    new EncodingAttributes().setOutputFormat("ogg")
                            .setAudioAttributes(new AudioAttributes().setCodec("libvorbis")
                                    .setChannels(channel.ordinal() + 1)
                                    .setSamplingRate(48000).setBitRate(192000)),
                    new EncodeProgressListener());


        } catch (EncoderException e) {
            JukeboxUtil.LOGGER.error("Failed to encode " + source.getName());
        }

    }



}
