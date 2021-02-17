package com.altynnikov.services;

import com.altynnikov.models.Input;
import com.altynnikov.models.QueryInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryInputService {
    private static final String INPUT_MATCHER = "[D] (\\d+(\\.\\d+)?) (\\d+(\\.\\d+(\\.\\d+)?)?)? ?(\\*)? ([PN])" +
            " ((0[1-9]|[12][0-9]|3[01])[- \\.](0[1-9]|1[012])[- \\.](19|20)\\d\\d)(-((0[1-9]|[12][0-9]|3[01])[- \\.](0[1-9]|1[012])[- \\.](19|20)\\d\\d))?";

    public static boolean isInputMatchToTimeLineInput(String input) {
        return input.matches(INPUT_MATCHER);
    }

    public static QueryInput convertInputToQueryInput(String input) {
        QueryInput queryInput = new QueryInput();

        Pattern pattern = Pattern.compile(INPUT_MATCHER);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            if (matcher.group(6) != null && matcher.group(6).equals("*")) {
                queryInput.setMatchAllTypes(true);
                processInput(queryInput, matcher);
            } else {
                processInput(queryInput, matcher);
            }
        }
        return queryInput;
    }

    private static void processInput(QueryInput queryInput, Matcher matcher) {
        Input.writeServiceInfo(queryInput, matcher.group(1));
        if (matcher.group(3) != null) {
            Input.writeQuestionInfo(queryInput, matcher.group(3));
        }
        queryInput.setResponseType(matcher.group(7));
        queryInput.setDateFrom(LocalDate.parse(matcher.group(8), DateTimeFormatter.ofPattern("d.MM.yyyy")));
        if (matcher.group(13) != null)
            queryInput.setDateTo(LocalDate.parse(matcher.group(13), DateTimeFormatter.ofPattern("d.MM.yyyy")));
    }
}
