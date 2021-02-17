package com.altynnikov;

import com.altynnikov.models.QueryInput;
import com.altynnikov.models.TimeLineInput;
import com.altynnikov.services.ConsoleService;
import com.altynnikov.services.QueryInputService;
import com.altynnikov.services.TimelineService;

import java.util.ArrayList;
import java.util.List;

import static com.altynnikov.services.ConsoleService.readConsoleInput;

public class Main {
    static List<Integer> results = new ArrayList<>();

    private static void showResult() {
        for (Integer averageTime : results) {
            if (averageTime == null) {
                System.out.println("-");
            } else {
                System.out.println(averageTime);
            }
        }
    }

    private static void processInput() {
        System.out.println("Enter number of lines");
        int numberOfLines = Integer.parseInt(readConsoleInput());

        for (int i = 0; i < numberOfLines; i++) {
            System.out.println("Enter waiting timeline or query");
            String input = readConsoleInput();

            if (TimelineService.isInputMatchToTimeLineInput(input)) {
                TimeLineInput timeLineInput = TimelineService.convertInputToTimeLineInput(input);

                if (TimelineService.getTimeLineRecords().containsKey(String.valueOf(timeLineInput.getServiceId()))) {
                    TimelineService.getTimeLineRecords().get(String.valueOf(timeLineInput.getServiceId())).add(timeLineInput);
                } else {
                    List<TimeLineInput> newList = new ArrayList<>();
                    newList.add(timeLineInput);
                    TimelineService.getTimeLineRecords().put(String.valueOf(timeLineInput.getServiceId()), newList);
                }
            } else if (QueryInputService.isInputMatchToTimeLineInput(input)) {
                QueryInput queryInput = QueryInputService.convertInputToQueryInput(input);
                Integer averageTime = queryInput.executeQuery();
                results.add(averageTime);
            } else {
                ConsoleService.showErrorMessage("Input doesn't match to pattern at line " + (i+1));
            }
        }
    }

    public static void main(String[] args) {
        processInput();
        showResult();
    }
}
