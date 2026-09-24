package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "meal_record")
public class MealRecordEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    @Column(name = "meal_type", length = 30)
    private String mealType;

    @Column(name = "ate_meal")
    private Boolean ateMeal;

    @Column(name = "notes", length = 255)
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "child_id", nullable = false)
    private ChildEntity child;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meal_plan_id", nullable = false)
    private MealPlanEntity mealPlan;

    public MealRecordEntity() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getMealDate() { return mealDate; }
    public void setMealDate(LocalDate mealDate) { this.mealDate = mealDate; }
    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }
    public Boolean getAteMeal() { return ateMeal; }
    public void setAteMeal(Boolean ateMeal) { this.ateMeal = ateMeal; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public ChildEntity getChild() { return child; }
    public void setChild(ChildEntity child) { this.child = child; }
    public MealPlanEntity getMealPlan() { return mealPlan; }
    public void setMealPlan(MealPlanEntity mealPlan) { this.mealPlan = mealPlan; }
}
