import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RapeStatusListenerTest {
    RapeStatusListener rapeStatusListener;
    @Before
    public void setUp(){
        rapeStatusListener = new RapeStatusListener();
    }

    @Test
    public void testContainsTriggerWords() throws Exception {
        String statusText = "if you aren't forcing your dick inside them. Someone touching your ass isn't rape.";

        boolean statusIsSuspicious = rapeStatusListener.containsRapeVariations(statusText);

        assertThat(statusIsSuspicious, is(true));
    }

    @Test
    public void grapeIsNotATriggerWord() throws Exception{
      String statusText = "grapes";

      boolean statusIsSuspicious = rapeStatusListener.containsRapeVariations(statusText);

      assertThat(statusIsSuspicious, is(false));
    }
}