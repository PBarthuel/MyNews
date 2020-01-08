package com.example.mynews.Search;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

public class SearchManager {

    private static final String DEFAULT_LUCENE = "(body:(\"%1$s\") OR headline:(\"%1$s\") OR byline:(\"%1$s\"))";

    public SearchInputState isUserInputCorrect(String userInput,
                                               List<String> sections,
                                               String pastDate,
                                               String futureDate) {

        LocalDate beginLocalDate;
        if (!pastDate.isEmpty()) {
            beginLocalDate = LocalDate.parse(pastDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            beginLocalDate = null;
        }

        LocalDate endLocalDate;
        if (!futureDate.isEmpty()) {
            endLocalDate = LocalDate.parse(futureDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
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

    public String getLucene(String userInput, List<String> sections) {

        StringBuilder resultLucene = new StringBuilder(String.format(DEFAULT_LUCENE, userInput));

        if (sections != null && !sections.isEmpty()) {
            resultLucene.append(" AND section_name:(");
            for (int i = 0; i < sections.size(); i++) {
                resultLucene.append(" \"").append(sections.get(i)).append("\"");
            }

            resultLucene.append(")");


        }
        return resultLucene.toString();
    }

    String getFormattedDate(String formattedDate) {

        if (formattedDate != null && !formattedDate.isEmpty()) {
            LocalDate date = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        } else {
            return null;
        }
    }
}
