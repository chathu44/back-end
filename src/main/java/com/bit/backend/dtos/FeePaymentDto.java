package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FeePaymentDto {

    private Long id;
    private LocalDateTime paymentDate;
    private BigDecimal amount;
    private String paymentMethod;
    private String referenceNo;
    private FeeInvoiceDto invoice;
    private StaffDto receivedBy;
    private StatusDto status;

    public FeePaymentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public FeeInvoiceDto getInvoice() {
        return invoice;
    }

    public void setInvoice(FeeInvoiceDto invoice) {
        this.invoice = invoice;
    }

    public StaffDto getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(StaffDto receivedBy) {
        this.receivedBy = receivedBy;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    @com.fasterxml.jackson.annotation.JsonSetter("invoice")
    public void setInvoiceFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.invoice = null;
            return;
        }

        FeeInvoiceDto dto = new FeeInvoiceDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.invoice = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            this.invoice = dto;
        }
    }

    @com.fasterxml.jackson.annotation.JsonSetter("receivedBy")
    public void setReceivedByFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.receivedBy = null;
            return;
        }

        StaffDto dto = new StaffDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.receivedBy = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            this.receivedBy = dto;
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