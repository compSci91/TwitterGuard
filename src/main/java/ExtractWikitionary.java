import de.tudarmstadt.ukp.jwktl.JWKTL;

import java.io.File;

public class ExtractWikitionary {
    public static void main(String[] args) {
        String PATH_TO_DUMP_FILE = "/Users/joshuahowell/Desktop/Twitter_Guard/Wiktionary/Setup/enwiktionary-20150413-pages-articles-multistream.xml";
        String TARGET_DIRECTORY = "/Users/joshuahowell/Desktop/Twitter_Guard/Wiktionary/Dictionary/";
        boolean OVERWRITE_EXISTING_FILES = true;
        File dumpFile = new File(PATH_TO_DUMP_FILE);

        File outputDirectory = new File(TARGET_DIRECTORY);
        boolean overwriteExisting = OVERWRITE_EXISTING_FILES;

        JWKTL.parseWiktionaryDump(dumpFile, outputDirectory, overwriteExisting);
    }
}
