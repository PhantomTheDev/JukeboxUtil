package net.phantomthedev.jukeboxutil;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.WallRedstoneTorchBlock;
import net.phantomthedev.jukeboxutil.utilities.CustomConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JukeboxUtil implements ModInitializer {
    public static final String MOD_ID = "jukeboxutil";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        // A test to see if I can read file relative to the jar

        // Gets the new files
        File customDir = new File(String.valueOf(FabricLoader.getInstance().getGameDir().resolve("discs").resolve("custom")));

        // Gets the converted files
        File encodedDir = new File(String.valueOf(FabricLoader.getInstance().getGameDir().resolve("discs").resolve("encoded")));

        File[] customDirListing = customDir.listFiles();

        // Make a list of all the mp3 files that need to be encoded
        ArrayList<File> toBeEncoded = new ArrayList();
        if (customDirListing != null) {
            for (File child : customDirListing) {
                if (child.getName().contains(".mp3")) {
                    toBeEncoded.add(child);
                }
            }
        }

        LOGGER.info("Counted " + toBeEncoded.size() + " files");

        File[] encodedDirListing = encodedDir.listFiles();
        ArrayList<File> encodedFiles = new ArrayList<>();

        if (encodedDirListing != null) {
            for (File child : encodedDirListing) {
                if (child.getName().contains(".ogg")) {
                    encodedFiles.add(child);
                }
            }
        }

        LOGGER.info("Counted " + encodedFiles.size() + " files");

        //Logic to determine which files need to be encoded or not

        for (File disc : toBeEncoded) {
            boolean alreadyDone = false;
            // Loop to see if it has been encoded or not
            for (int i = 0; i < encodedFiles.size(); i++) {
                // remove the file extensions and see if the names match. If they do, then DO NOT convert
                if (disc.getName().substring(0, disc.getName().length() - 4).equals(encodedFiles.get(i).getName().substring(0, encodedFiles.get(i).getName().length() - 4))) {
                    alreadyDone = true;
                    break;
                }
            }

            if(!alreadyDone) {
                // encode it
                CustomConverter.beginEncode(disc, new File(String.valueOf(FabricLoader.getInstance().getGameDir().resolve("discs").resolve("encoded").resolve(disc.getName().substring(0, disc.getName().length() - 4) + ".ogg"))), CustomConverter.Channel.MONO);
            }
        }



//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            writer.write("test");
//            writer.close();
//            LOGGER.info("Wrote to a file I guess: " + file.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
