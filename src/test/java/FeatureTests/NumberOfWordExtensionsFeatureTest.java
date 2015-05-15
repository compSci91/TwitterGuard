package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfWordsNormalizer;
import Features.NumberOfWordExtensionsFeature;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfWordExtensionsFeatureTest {
    Status twoWordExtensionStatus = mock(Status.class);

    @Before
    public void setUp(){
        when(twoWordExtensionStatus.getText()).thenReturn("two words Hiii Byeee");

    }
    @Test
    public void testReturnValue(){
        assertEquals(2, new NumberOfWordExtensionsFeature().returnValue(twoWordExtensionStatus), 0);
    }

    @Test
    public void testReturnNormalizedValue(){
        Normalizer numberOfWordsNormalizer = new NumberOfWordsNormalizer();
        assertEquals(2 / 4.0, new NumberOfWordExtensionsFeature(numberOfWordsNormalizer).returnValue(twoWordExtensionStatus), 0);

    }


}
