import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import de.tudarmstadt.ukp.jwktl.api.IWiktionarySense;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class WiktionaryTest {
    @Test
    public void testGetEntriesWhenWordExists(){
        IWiktionaryEdition wkt = JWKTL.openEdition(new File("/Users/joshuahowell/Desktop/Twitter_Guard/Wiktionary/Dictionary/"));
        IWiktionaryPage page = wkt.getPageForWord("Ph.D.");

        List<IWiktionaryEntry> entries = page.getEntries();
        wkt.close();

        assertThat(entries.size(), not(equalTo(0)));
    }

    @Test(expected=NullPointerException.class)
    public void testGetPageWhenWordDoesNotExist(){
        IWiktionaryEdition wkt = JWKTL.openEdition(new File("/Users/joshuahowell/Desktop/Twitter_Guard/Wiktionary/Dictionary/"));
        IWiktionaryPage page = wkt.getPageForWord("junk text");
        wkt.close();

        List<IWiktionaryEntry> entries = page.getEntries();

    }
}
