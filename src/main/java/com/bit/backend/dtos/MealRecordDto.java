package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;

public class MealRecordDto {

    private Long id;
    private LocalDate mealDate;
    private String mealType;
    private Boolean ateMeal;
    private String notes;
    private ChildDto child;
    private MealPlanDto mealPlan;

    public MealRecordDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Boolean getAteMeal() {
        return ateMeal;
    }

    public void setAteMeal(Boolean ateMeal) {
        this.ateMeal = ateMeal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ChildDto getChild() {
        return child;
    }

    public void setChild(ChildDto child) {
        this.child = child;
    }

    public MealPlanDto getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlanDto mealPlan) {
        this.mealPlan = mealPlan;
    }

    @com.fasterxml.jackson.annotation.JsonSetter("child")
    public void setChildFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.child = null;
            return;
        }

        ChildDto dto = new ChildDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.child = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }

            if (node.hasNonNull("firstName")) {
                dto.setFirstName(node.get("firstName").asText());
            }

            this.child = dto;
        }
    }

    @com.fasterxml.jackson.annotation.JsonSetter("mealPlan")
    public void setMealPlanFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.mealPlan = null;
            return;
        }

        MealPlanDto dto = new MealPlanDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.mealPlan = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }

            this.mealPlan = dto;
        }
    }
}