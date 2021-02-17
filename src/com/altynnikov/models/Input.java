package com.altynnikov.models;

import com.altynnikov.services.ConsoleService;

public class Input {
    private static final int SERVICE_DIVISION = 2;
    private static final int QUESTION_DIVISION = 3;
    private static final int NUMBER_OF_SERVICES = 10;
    private static final int NUMBER_OF_SERVICE_VARIATION = 3;
    private static final int NUMBER_OF_QUESTION_TYPE = 10;
    private static final int NUMBER_OF_CATEGORIES = 20;
    private static final int NUMBER_OF_SUBCATEGORIES = 5;
    private static final int LOWER_RANGE_OF_ID = 1;

    private int serviceId;
    private int serviceVariationId;
    private int questionTypeId;
    private int categoryId;
    private int subCategoryId;
    private String responseType;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getServiceVariationId() {
        return serviceVariationId;
    }

    public void setServiceVariationId(int serviceVariationId) {
        this.serviceVariationId = serviceVariationId;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public static void writeServiceInfo(Input objectForUpdating, String serviceInfo) {
        String[] splitServiceInfo = serviceInfo.split("\\.");

        if (isServiceInfoSuit(splitServiceInfo)) {
            if (splitServiceInfo.length < SERVICE_DIVISION) {
                objectForUpdating.setServiceId(Integer.parseInt(splitServiceInfo[0]));
            } else {
                objectForUpdating.setServiceId(Integer.parseInt(splitServiceInfo[0]));
                objectForUpdating.setServiceVariationId(Integer.parseInt(splitServiceInfo[1]));
            }
        } else {
            ConsoleService.showErrorMessage("id related to service group wrong");
        }
    }

    public static void writeQuestionInfo(Input objectForUpdating, String questionInfo) {
        String[] splitQuestionInfo = questionInfo.split("\\.");


        if (isQuestionInfoSuit(splitQuestionInfo)) {
            if (splitQuestionInfo.length == QUESTION_DIVISION) {
                objectForUpdating.setQuestionTypeId(Integer.parseInt(splitQuestionInfo[0]));
                objectForUpdating.setCategoryId(Integer.parseInt(splitQuestionInfo[1]));
                objectForUpdating.setSubCategoryId(Integer.parseInt(splitQuestionInfo[2]));
            } else if (splitQuestionInfo.length == QUESTION_DIVISION - 1) {
                objectForUpdating.setQuestionTypeId(Integer.parseInt(splitQuestionInfo[0]));
                objectForUpdating.setCategoryId(Integer.parseInt(splitQuestionInfo[1]));
            } else {
                objectForUpdating.setQuestionTypeId(Integer.parseInt(splitQuestionInfo[0]));
            }
        } else {
            ConsoleService.showErrorMessage("id related to question group wrong");
        }
    }

    private static boolean isQuestionInfoSuit(String[] input) {
        if (input.length == QUESTION_DIVISION) {
            return (Integer.parseInt(input[0]) <= NUMBER_OF_QUESTION_TYPE && Integer.parseInt(input[0]) >= LOWER_RANGE_OF_ID)
                    && (Integer.parseInt(input[1]) <= NUMBER_OF_CATEGORIES && Integer.parseInt(input[1]) >= LOWER_RANGE_OF_ID)
                    && (Integer.parseInt(input[2]) <= NUMBER_OF_SUBCATEGORIES && Integer.parseInt(input[2]) >= LOWER_RANGE_OF_ID);
        }
        return (Integer.parseInt(input[0]) <= NUMBER_OF_QUESTION_TYPE && Integer.parseInt(input[0]) >= LOWER_RANGE_OF_ID);
    }

    private static boolean isServiceInfoSuit(String[] input) {
        if (input.length == SERVICE_DIVISION) {
            return (Integer.parseInt(input[0]) <= NUMBER_OF_SERVICES && Integer.parseInt(input[0]) >= LOWER_RANGE_OF_ID)
                    && (Integer.parseInt(input[1]) <= NUMBER_OF_SERVICE_VARIATION && Integer.parseInt(input[1]) >= LOWER_RANGE_OF_ID);
        }
        return (Integer.parseInt(input[0]) <= NUMBER_OF_SERVICES && Integer.parseInt(input[0]) >= LOWER_RANGE_OF_ID);
    }
}
