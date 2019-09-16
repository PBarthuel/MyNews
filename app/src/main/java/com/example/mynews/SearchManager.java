package com.example.mynews;

import java.util.List;

public class SearchManager {

    public boolean isUserInputCorrect (String userInput, List<String> sections) {
        if (userInput == null
                || userInput.trim().isEmpty()
                || userInput.contains("à")) {
            return false;
        }
        return true;
    }
}
