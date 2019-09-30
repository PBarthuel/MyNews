package com.example.mynews;

import java.util.List;

public class SearchManager {

    public SearchInputState isUserInputCorrect (String userInput, List<String> sections) {
        if (userInput == null
                || userInput.trim().isEmpty()
                || userInput.contains("à")) {
            return SearchInputState.INPUT_INCORRECT;
        }else if (sections.isEmpty()) {
            return SearchInputState.NO_SECTIONS_SELECTED;
        }
        return SearchInputState.OK;
    }

}
