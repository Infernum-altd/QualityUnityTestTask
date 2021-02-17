package com.altynnikov.services;

import com.altynnikov.models.Input;
import com.altynnikov.models.TimeLineInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimelineService {
    private static final String INPUT_MATCHER = "[C] (\\d+\\.?\\d?) (\\d+\\.?\\d+\\.?\\d?) ([PN])" +
            " ((0[1-9]|[12][0-9]|3[01])[- \\.](0[1-9]|1[012])[- \\.](19|20)\\d\\d) (\\d+)";

    private static Map<String, List<TimeLineInput>> timeLineRecords = new HashMap<>();

    public static Map<String, List<TimeLineInput>> getTimeLineRecords() {
        return timeLineRecords;
    }

    public static boolean isInputMatchToTimeLineInput(String input) {
        return input.matches(INPUT_MATCHER);
    }

    public static TimeLineInput convertInputToTimeLineInput(String input) {
        TimeLineInput resultTimeLineInput = new TimeLineInput();
        Pattern pattern = Pattern.compile(INPUT_MATCHER);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            Input.writeServiceInfo(resultTimeLineInput, matcher.group(1));
            Input.writeQuestionInfo(resultTimeLineInput, matcher.group(2));
            resultTimeLineInput.setResponseType(matcher.group(3));
            resultTimeLineInput.setDate(LocalDate.parse(matcher.group(4), DateTimeFormatter.ofPattern("d.MM.yyyy")));
            resultTimeLineInput.setTime(Integer.parseInt(matcher.group(8)));
        }
        return resultTimeLineInput;
    }


}
