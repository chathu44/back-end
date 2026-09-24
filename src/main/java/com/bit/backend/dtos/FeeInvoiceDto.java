package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FeeInvoiceDto {

    private Long id;
    private String invoiceCode;
    private String billingMonth;
    private BigDecimal amount;
    private LocalDate dueDate;
    private ChildDto child;
    private EnrollmentDto enrollment;
    private ParentDto parent;
    private StatusDto status;

    public FeeInvoiceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public ChildDto getChild() {
        return child;
    }

    public void setChild(ChildDto child) {
        this.child = child;
    }

    public EnrollmentDto getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(EnrollmentDto enrollment) {
        this.enrollment = enrollment;
    }

    public ParentDto getParent() {
        return parent;
    }

    public void setParent(ParentDto parent) {
        this.parent = parent;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
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
            this.child = dto;
        }
    }

    @com.fasterxml.jackson.annotation.JsonSetter("enrollment")
    public void setEnrollmentFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.enrollment = null;
            return;
        }

        EnrollmentDto dto = new EnrollmentDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.enrollment = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            this.enrollment = dto;
        }
    }

    @com.fasterxml.jackson.annotation.JsonSetter("parent")
    public void setParentFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.parent = null;
            return;
        }

        ParentDto dto = new ParentDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.parent = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            this.parent = dto;
        }
    }

    @com.fasterxml.jackson.annotation.JsonSetter("status")
    public void setStatusFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.status = null;
            return;
        }

        StatusDto dto = new StatusDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.status = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            if (node.hasNonNull("name")) {
                dto.setName(node.get("name").asText());
            }
            this.status = dto;
        }
    }
}