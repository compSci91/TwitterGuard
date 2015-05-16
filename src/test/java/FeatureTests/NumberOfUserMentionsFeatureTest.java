package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfWordsNormalizer;
import Features.NumberOfUserMentionsFeature;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;
import twitter4j.UserMentionEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfUserMentionsFeatureTest {
    Status oneUserMentionStatus = mock(Status.class);

    @Before
    public void setUp(){
        when(oneUserMentionStatus.getUserMentionEntities()).thenReturn(new UserMentionEntity[]{mock(UserMentionEntity.class)});
        when(oneUserMentionStatus.getText()).thenReturn("@Josh Howell is programming Twitter Guard");
    }

    @Test
    public void testReturnValue(){
        assertEquals(1, new NumberOfUserMentionsFeature().returnValue(oneUserMentionStatus), 0);
    }

    @Test
    public void testReturnNormalizedValue(){
        Normalizer numberOfWordsNormalizer = new NumberOfWordsNormalizer();
        assertEquals(1 , new NumberOfUserMentionsFeature(numberOfWordsNormalizer).returnValue(oneUserMentionStatus), 0);
    }


}
