package com.example.mynews;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchManagerTest {

    private SearchManager searchManager = new SearchManager();

    @Test
    public void should_return_input_incorrect_when_user_input_is_null () {
        //Given
        String userInput = null;
        //When
        SearchInputState result= searchManager.isUserInputCorrect(userInput, new ArrayList<String>());
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_false_when_user_input_is_empty () {
        //Given
        String userInput = "";
        //When
        SearchInputState result= searchManager.isUserInputCorrect(userInput, new ArrayList<String>());
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_false_when_user_input_is_blank_characters () {
        //Given
        String userInput = "\n";
        //When
        SearchInputState result= searchManager.isUserInputCorrect(userInput, new ArrayList<String>());
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_false_when_user_input_is_an_a_with_an_accent_character () {
        //Given
        String userInput = "à";
        //When
        SearchInputState result= searchManager.isUserInputCorrect(userInput, new ArrayList<String>());
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_true_when_user_input_is_an_grave_accent_character () {
        //Given
        String userInput = "è";
        //When
        SearchInputState result= searchManager.isUserInputCorrect(userInput, new ArrayList<String>());
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_true_when_user_input_is_an_aigu_accent_character () {
        //Given
        String userInput = "é";
        //When
        SearchInputState result= searchManager.isUserInputCorrect(userInput, new ArrayList<String>());
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_input_incorrect_when_user_input_is_courgette () {
        //Given
        String userInput = "courgette";
        //When
        SearchInputState result= searchManager.isUserInputCorrect(userInput, new ArrayList<String>());
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_ok_when_one_sections_is_selected () {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        sections.add("science");
        //When
        SearchInputState result= searchManager.isUserInputCorrect(userInput, sections);
        //Then
        assertEquals(SearchInputState.OK, result);
    }

    @Test
    public void should_return_no_sections_selected_when_no_sections_are_selected () {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        //When
        SearchInputState result= searchManager.isUserInputCorrect(userInput, sections);
        //Then
        assertEquals(SearchInputState.NO_SECTIONS_SELECTED, result);
    }
}
