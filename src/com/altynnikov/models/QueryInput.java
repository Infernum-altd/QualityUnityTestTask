package com.altynnikov.models;

import com.altynnikov.services.TimelineService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryInput extends Input {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private boolean isMatchAllTypes;

    public boolean isMatchAllTypes() {
        return isMatchAllTypes;
    }

    public void setMatchAllTypes(boolean matchAllTypes) {
        isMatchAllTypes = matchAllTypes;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Integer executeQuery() {
        List<TimeLineInput> timeLineInputs;
        if (isMatchAllTypes) {
            timeLineInputs = findElementsWithMatchAllTypes();
        } else {
            timeLineInputs = findElementsWithMatch();
        }
        return calculateAverageTime(timeLineInputs);
    }

    private Integer calculateAverageTime(List<TimeLineInput> timeLineInputs) {
        Integer result = null;
        for (TimeLineInput timeLineInput : timeLineInputs) {
            if (result == null)
                result = 0;
            result += timeLineInput.getTime();
        }
        return result == null ? null : result/timeLineInputs.size();
    }

    private List<TimeLineInput> findElementsWithMatch() {
        List<TimeLineInput> timeLineInputs = TimelineService.getTimeLineRecords().get(String.valueOf(this.getServiceId()));
        List<TimeLineInput> result = new ArrayList<>();
        if (timeLineInputs != null) {
            for (TimeLineInput input : timeLineInputs) {
                if (this.dateTo != null) {
                    if (isMandatoryFieldsEquals(input)
                            && (input.getDate().isBefore(this.getDateTo())
                            && input.getDate().isAfter(this.getDateFrom()))) {
                        result.add(input);
                    }
                } else {
                    if (isMandatoryFieldsEquals(input) && (input.getDate().equals(this.dateFrom))) {
                        result.add(input);
                    }
                }
            }
        }

        return result;
    }

    private boolean isMandatoryFieldsEquals(TimeLineInput timeLineInput) {
        return (timeLineInput.getQuestionTypeId() == 0 || this.getQuestionTypeId() == 0 || timeLineInput.getQuestionTypeId() == this.getQuestionTypeId())
                && (timeLineInput.getResponseType().equals(this.getResponseType()))
                && (timeLineInput.getServiceVariationId() == 0 || this.getServiceVariationId() == 0 || timeLineInput.getServiceVariationId() == this.getServiceVariationId())
                && (timeLineInput.getCategoryId() == 0 || this.getCategoryId() == 0 || timeLineInput.getCategoryId() == this.getCategoryId())
                && (timeLineInput.getSubCategoryId() == 0 || this.getSubCategoryId() == 0 || timeLineInput.getSubCategoryId() == this.getSubCategoryId());
    }

    private List<TimeLineInput> findElementsWithMatchAllTypes() {
        List<TimeLineInput> timeLineInputs = TimelineService.getTimeLineRecords().get(String.valueOf(this.getServiceId()));
        List<TimeLineInput> result = new ArrayList<>();
        for (TimeLineInput input : timeLineInputs) {
            if (this.dateTo != null) {
                if ((input.getQuestionTypeId() == 0 || this.getQuestionTypeId() == 0 || input.getQuestionTypeId() == this.getQuestionTypeId())
                        && (input.getResponseType().equals(this.getResponseType()))
                        && (input.getDate().isBefore(this.getDateTo()) && input.getDate().isAfter(this.getDateFrom()))) {
                    result.add(input);
                }
            } else {
                if ((input.getQuestionTypeId() == 0 || this.getQuestionTypeId() == 0 || input.getQuestionTypeId() == this.getQuestionTypeId())
                        && (input.getResponseType().equals(this.getResponseType()))
                        && (input.getDate().equals(this.dateTo))) {
                    result.add(input);
                }
            }
        }
        return result;
    }
}
