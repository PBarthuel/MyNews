package com.example.mynews;

public class SearchManager {

    public boolean isUserInputCorrect (String userInput) {
        if (userInput == null || userInput.trim().isEmpty() || userInput.equals("é")) {
            return false;
        }
        return true;
    }
}
