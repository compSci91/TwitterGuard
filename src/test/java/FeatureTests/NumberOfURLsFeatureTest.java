package FeatureTests;

import Features.NumberOfURLsFeature;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;
import twitter4j.URLEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfURLsFeatureTest {
    Status twoEntityStatus = mock(Status.class);

    @Before
    public void setUp(){
        URLEntity[] twoEntities = {mock(URLEntity.class), mock(URLEntity.class)};
        when(twoEntityStatus.getURLEntities()).thenReturn(twoEntities);
    }

    @Test
    public void testReturnValue(){
        assertEquals(2, new NumberOfURLsFeature().returnValue(twoEntityStatus), 0);
    }
}
