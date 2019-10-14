package com.example.mynews;

import android.os.LocaleList;

import androidx.annotation.Nullable;

import org.threeten.bp.LocalDate;

import java.util.List;

public class SearchManager {

    public SearchInputState isUserInputCorrect(String userInput, List<String> sections, @Nullable LocalDate beginDate, @Nullable LocalDate endDate) {
        if (userInput == null
                || userInput.trim().isEmpty()) {
            return SearchInputState.INPUT_INCORRECT;
        }else if (sections.isEmpty()) {
            return SearchInputState.NO_SECTIONS_SELECTED;
        }else if (beginDate != null && endDate != null && beginDate.isAfter(endDate)) {
            return SearchInputState.DATE_IS_INCORRECT;
        }else if (beginDate != null && beginDate.isAfter(LocalDate.now())) {
            return SearchInputState.BEGIN_DATE_IS_IN_THE_FUTURE;
        }
        return SearchInputState.OK;
    }

}
