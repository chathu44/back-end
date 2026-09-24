package com.bit.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fee_payment")
public class FeePaymentEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_method", length = 30)
    private String paymentMethod;

    @Column(name = "reference_no", length = 50)
    private String referenceNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id", nullable = false)
    private FeeInvoiceEntity invoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "received_by", nullable = false)
    private StaffEntity receivedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusEntity status;

    public FeePaymentEntity() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getReferenceNo() { return referenceNo; }
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }
    public FeeInvoiceEntity getInvoice() { return invoice; }
    public void setInvoice(FeeInvoiceEntity invoice) { this.invoice = invoice; }
    public StaffEntity getReceivedBy() { return receivedBy; }
    public void setReceivedBy(StaffEntity receivedBy) { this.receivedBy = receivedBy; }
    public StatusEntity getStatus() { return status; }
    public void setStatus(StatusEntity status) { this.status = status; }
}