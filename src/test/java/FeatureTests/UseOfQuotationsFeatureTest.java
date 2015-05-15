package FeatureTests;


import Features.UseOfQuotationsFeature;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UseOfQuotationsFeatureTest {

    @Test
    public void testReturnValue(){
        Status statusContainsQuotations = mock(Status.class);
        when(statusContainsQuotations.getText()).thenReturn("\"Good evening,\", said the clergymen.");

        Status statusDoesNotContainQuotations = mock(Status.class);
        when(statusDoesNotContainQuotations.getText()).thenReturn("hello goodbye");

        assertEquals(1, new UseOfQuotationsFeature().returnValue(statusContainsQuotations), 0);
        assertEquals(0, new UseOfQuotationsFeature().returnValue(statusDoesNotContainQuotations), 0);
    }
}
