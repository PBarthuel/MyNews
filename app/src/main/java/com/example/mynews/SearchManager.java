package com.example.mynews;

public class SearchManager {

    public boolean isUserInputCorrect (String userInput) {
        if (userInput == null || userInput.trim().isEmpty() || userInput.equals("é") || userInput.equals("è") || userInput.equals("à")) {
            return false;
        }
        return true;
    }
}
