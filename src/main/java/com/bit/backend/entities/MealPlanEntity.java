package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "meal_plan")
public class MealPlanEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    @Column(name = "meal_type", length = 30)
    private String mealType;

    @Column(name = "menu_items", nullable = false, length = 255)
    private String menuItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom_id")
    private ClassroomEntity classroom;

    public MealPlanEntity() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getMealDate() { return mealDate; }
    public void setMealDate(LocalDate mealDate) { this.mealDate = mealDate; }
    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }
    public String getMenuItems() { return menuItems; }
    public void setMenuItems(String menuItems) { this.menuItems = menuItems; }
    public ClassroomEntity getClassroom() { return classroom; }
    public void setClassroom(ClassroomEntity classroom) { this.classroom = classroom; }
}
