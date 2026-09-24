package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;

public class MealPlanDto {

    private Long id;
    private LocalDate mealDate;
    private String mealType;
    private String menuItems;
    private ClassroomDto classroom;

    public MealPlanDto() {
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

    public String getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(String menuItems) {
        this.menuItems = menuItems;
    }

    public ClassroomDto getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomDto classroom) {
        this.classroom = classroom;
    }

    @com.fasterxml.jackson.annotation.JsonSetter("classroom")
    public void setClassroomFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.classroom = null;
            return;
        }

        ClassroomDto dto = new ClassroomDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.classroom = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }

            if (node.hasNonNull("roomName")) {
                dto.setRoomName(node.get("roomName").asText());
            }

            this.classroom = dto;
        }
    }
}