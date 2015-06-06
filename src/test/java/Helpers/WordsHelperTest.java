package Helpers;

import Features.NumberOfWords;
import Features.StatusFeature;
import org.junit.Test;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordsHelperTest {

    @Test
    public void testGetWords(){
        WordsHelper wordsHelper = new WordsHelper();
        Status mockStatus = mock(Status.class);

        when(mockStatus.getText()).thenReturn("I don't know why you say goodbye, I say hello.");

        List<String> words = new ArrayList<String>(
                Arrays.asList("I", "don't", "know", "why", "you", "say", "goodbye", "I", "say", "hello")
        );

        assertEquals(words, wordsHelper.getWords(mockStatus));

    }
}