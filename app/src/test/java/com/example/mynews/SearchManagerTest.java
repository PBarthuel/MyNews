package com.example.mynews;

import org.junit.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchManagerTest {

    private SearchManager searchManager = new SearchManager();

    @Test
    public void should_return_ok_when_user_input_are_correct() {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        sections.add("science");
        String beginDate = "19/12/1995";
        String endDate = "19/12/2019";
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, sections, beginDate, endDate);
        //Then
        assertEquals(SearchInputState.OK, result);
    }

    @Test
    public void should_return_date_is_incorrect_when_end_date_is_before_begin_date() {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        sections.add("science");
        String beginDate = "19/12/1995";
        String endDate = "12/10/1995";
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, sections, beginDate, endDate);
        //Then
        assertEquals(SearchInputState.DATE_IS_INCORRECT, result);
    }

    @Test
    public void should_return_begin_date_is_in_the_future_when_begin_date_is_after_today() {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        sections.add("science");
        LocalDate localDate = LocalDate.now().plusDays(1);
        String beginDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, sections, beginDate, "");
        //Then
        assertEquals(SearchInputState.BEGIN_DATE_IS_IN_THE_FUTURE, result);
    }

    @Test
    public void should_return_ok_when_user_input_are_correct_bis() {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        sections.add("science");
        String endDate = "12/10/2019";
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, sections, "", endDate);
        //Then
        assertEquals(SearchInputState.OK, result);
    }

    @Test
    public void should_return_ok_when_user_input_are_correct_thrice() {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        sections.add("science");
        String beginDate = "19/12/1995";
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, sections, beginDate, "");
        //Then
        assertEquals(SearchInputState.OK, result);
    }

    @Test
    public void should_return_ok_when_user_input_are_correct_fourth() {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        sections.add("science");
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, sections, "", "");
        //Then
        assertEquals(SearchInputState.OK, result);
    }

    @Test
    public void should_return_input_incorrect_when_user_input_is_null() {
        //Given
        String userInput = null;
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, new ArrayList<String>(), "", "");
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_input_incorrect_when_user_input_is_empty() {
        //Given
        String userInput = "";
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, new ArrayList<String>(), "", "");
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_input_incorrect_when_user_input_is_blank_characters() {
        //Given
        String userInput = "\n";
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, new ArrayList<String>(), "", "");
        //Then
        assertEquals(SearchInputState.INPUT_INCORRECT, result);
    }

    @Test
    public void should_return_ok_when_user_input_is_an_a_with_an_accent_character() {
        //Given
        String userInput = "à";
        List<String> section = new ArrayList<>();
        section.add("courgette");
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, section, "", "");
        //Then
        assertEquals(SearchInputState.OK, result);
    }

    @Test
    public void should_return_no_section_selected_when_user_input_is_correct_but_no_section_selected() {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, sections, "", "");
        //Then
        assertEquals(SearchInputState.NO_SECTIONS_SELECTED, result);
    }

    @Test
    public void should_return_no_sections_selected_when_no_sections_are_selected() {
        //Given
        String userInput = "courgette";
        List<String> sections = new ArrayList<>();
        //When
        SearchInputState result = searchManager.isUserInputCorrect(userInput, sections, "", "");
        //Then
        assertEquals(SearchInputState.NO_SECTIONS_SELECTED, result);
    }

    @Test
    public void should_return_good_lucene_for_one_key_word() {
        //Given
        String keyWord = "Trump";
        //when
        String result = searchManager.getLucene(keyWord, null, null, null);
        //Then
        assertEquals("(body:(\"Trump\") OR headline:(\"Trump\") OR byline:(\"Trump\"))", result);
    }

    @Test
    public void should_return_good_lucene_for_no_key_word() {
        //Given
        String keyWord = "";
        //when
        String result = searchManager.getLucene(keyWord, null, null, null);
        //Then
        assertEquals("(body:(\"\") OR headline:(\"\") OR byline:(\"\"))", result);
    }

    @Test
    public void should_return_good_lucene_for_one_key_word_no_section() {
        //Given
        String keyWord = "Trump";
        List<String> sections = new ArrayList<>();
        //when
        String result = searchManager.getLucene(keyWord, sections, null, null);
        //Then
        assertEquals("(body:(\"Trump\") OR headline:(\"Trump\") OR byline:(\"Trump\"))", result);
    }

    @Test
    public void should_return_good_lucene_for_one_key_word_one_section() {
        //Given
        String keyWord = "Trump";
        List<String> sections = new ArrayList<>();
        sections.add("Sports");
        //when
        String result = searchManager.getLucene(keyWord, sections, null, null);
        //Then
        assertEquals("(body:(\"Trump\") OR headline:(\"Trump\") OR byline:(\"Trump\")) AND section_name:( \"Sports\")", result);
    }

    @Test
    public void should_return_good_lucene_for_one_key_word_two_section() {
        //Given
        String keyWord = "Trump";
        List<String> sections = new ArrayList<>();
        sections.add("Sports");
        sections.add("Arts");
        //when
        String result = searchManager.getLucene(keyWord, sections, null, null);
        //Then
        assertEquals("(body:(\"Trump\") OR headline:(\"Trump\") OR byline:(\"Trump\")) AND section_name:( \"Sports\" \"Arts\")", result);
    }

    @Test
    public void should_return_good_lucene_for_one_key_word_two_section_and_one_date() {
        //Given
        String keyWord = "Trump";
        List<String> sections = new ArrayList<>();
        sections.add("Sports");
        sections.add("Arts");
        String beginDate = "20120109";
        //when
        String result = searchManager.getLucene(keyWord, sections, beginDate, null);
        //Then
        assertEquals("(body:(\"Trump\") OR headline:(\"Trump\") OR byline:(\"Trump\")) AND section_name:( \"Sports\" \"Arts\")&facet_fields=source&facet=true&begin_date=20120109", result);
    }

    @Test
    public void should_return_good_lucene_for_one_key_word_two_section_and_two_date() {
        //Given
        String keyWord = "Trump";
        List<String> sections = new ArrayList<>();
        sections.add("Sports");
        sections.add("Arts");
        String beginDate = "20120109";
        String endDate = "20190109";
        //when
        String result = searchManager.getLucene(keyWord, sections, beginDate, endDate);
        //Then
        assertEquals("(body:(\"Trump\") OR headline:(\"Trump\") OR byline:(\"Trump\")) AND section_name:( \"Sports\" \"Arts\")&facet_fields=source&facet=true&begin_date=20120109&end_date=20190109", result);
    }
}
