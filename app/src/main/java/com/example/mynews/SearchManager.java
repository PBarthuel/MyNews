package com.example.mynews;

import androidx.annotation.NonNull;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

public class SearchManager {

    public SearchInputState isUserInputCorrect(String userInput,
                                               List<String> sections,
                                               @NonNull String beginDate,
                                               @NonNull String endDate) {

        LocalDate beginLocalDate;
        if (beginDate.isEmpty()) {
            beginLocalDate = LocalDate.parse(beginDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            beginLocalDate = null;
        }

        LocalDate endLocalDate;
        if (endDate.isEmpty()) {
            endLocalDate =
                    LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }else {
            endLocalDate = null;
        }

        if (userInput == null
                || userInput.trim().isEmpty()) {
            return SearchInputState.INPUT_INCORRECT;
        } else if (sections.isEmpty()) {
            return SearchInputState.NO_SECTIONS_SELECTED;
        } else if (beginLocalDate != null && endLocalDate != null && beginLocalDate.isAfter(endLocalDate)) {
            return SearchInputState.DATE_IS_INCORRECT;
        } else if (beginLocalDate != null && beginLocalDate.isAfter(LocalDate.now())) {
            return SearchInputState.BEGIN_DATE_IS_IN_THE_FUTURE;
        }
        return SearchInputState.OK;
    }

}
