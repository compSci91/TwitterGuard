package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfWordsNormalizer;
import Features.NumberOfWordExtensions;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfWordExtensionsTest {
    Status twoWordExtensionStatus = mock(Status.class);

    @Before
    public void setUp(){
        when(twoWordExtensionStatus.getText()).thenReturn("two words Hiii Byeee");

    }
    @Test
    public void testReturnValue(){
        assertEquals(2, new NumberOfWordExtensions().returnValue(twoWordExtensionStatus), 0);
    }

    @Test
    public void testReturnNormalizedValue(){
        Normalizer numberOfWordsNormalizer = new NumberOfWordsNormalizer();
        assertEquals(2 / 4.0, new NumberOfWordExtensions(numberOfWordsNormalizer).returnValue(twoWordExtensionStatus), 0);

    }


}
