package com.example.mynews;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

public class SearchManager {

    private static final String DEFAULT_LUCENE = "(body:(\"%1$s\") OR headline:(\"%1$s\") OR byline:(\"%1$s\"))";
    //private static final String DEFAULT_DATE = "&facet_field=day_of_week&facet=true&begin_date=%1$s&end_date=%1$s";

    public SearchInputState isUserInputCorrect(String userInput,
                                               List<String> sections,
                                               String beginDate,
                                               String endDate) {

        LocalDate beginLocalDate;
        if (!beginDate.isEmpty()) {
            beginLocalDate = LocalDate.parse(beginDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            beginLocalDate = null;
        }

        LocalDate endLocalDate;
        if (!endDate.isEmpty()) {
            endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

        StringBuilder result = new StringBuilder(String.format(DEFAULT_LUCENE, userInput));

        if (sections != null && !sections.isEmpty()) {
            result.append(" AND section_name:(");
            for (int i = 0; i < sections.size(); i++) {
                result.append(" \"").append(sections.get(i)).append("\"");
            }

            result.append(")");


        }
        return result.toString();
    }

    /*public String getDate(String beginDate, String endDate) {

        StringBuilder resultDate = new StringBuilder(DEFAULT_DATE);

        if ((beginDate != null && !beginDate.isEmpty()) || (endDate != null && !endDate.isEmpty()) ) {
            resultDate.append("&facet=true");
            if (beginDate != null && !beginDate.isEmpty()) {
                resultDate.append("&begin_date=").append(beginDate);
            }
            if (endDate != null && !endDate.isEmpty()) {
                resultDate.append("&end_date=").append(endDate);
            }
        }

        return resultDate.toString();
    }*/
}
