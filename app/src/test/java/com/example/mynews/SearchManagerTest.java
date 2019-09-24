package com.example.mynews;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchManagerTest {

    private SearchManager searchManager = new SearchManager();

    @Test
    public void should_return_false_when_user_input_is_null () {
        //Given
        String userInput = null;
        //When
        boolean result = searchManager.isUserInputCorrect(userInput, new ArrayList<String>());
        //Then
        assertFalse(result);
    }

    @Test
    public void should_return_false_when_user_input_is_empty () {
        //Given
        String userInput = "";
        //When
        boolean result = searchManager.isUserInputCorrect(userInput);
        //Then
        assertFalse(result);
    }

    @Test
    public void should_return_false_when_user_input_is_blank_characters () {
        //Given
        String userInput = "\n";
        //When
        boolean result = searchManager.isUserInputCorrect(userInput);
        //Then
        assertFalse(result);
    }

    @Test
    public void should_return_false_when_user_input_is_an_a_with_an_accent_character () {
        //Given
        String userInput = "à";
        //When
        boolean result = searchManager.isUserInputCorrect(userInput);
        //Then
        assertFalse(result);
    }

    @Test
    public void should_return_true_when_user_input_is_an_grave_accent_character () {
        //Given
        String userInput = "è";
        //When
        boolean result = searchManager.isUserInputCorrect(userInput);
        //Then
        assertTrue(result);
    }

    @Test
    public void should_return_true_when_user_input_is_an_aigu_accent_character () {
        //Given
        String userInput = "é";
        //When
        boolean result = searchManager.isUserInputCorrect(userInput);
        //Then
        assertTrue(result);
    }

    @Test
    public void should_return_true_when_user_input_is_courgette () {
        //Given
        String userInput = "courgette";
        //When
        boolean result = searchManager.isUserInputCorrect(userInput);
        //Then
        assertTrue(result);
    }
}
