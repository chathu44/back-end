package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.FeePaymentDto;
import com.bit.backend.services.FeePaymentServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class FeePaymentController {

    private final FeePaymentServiceI feePaymentServiceI;

    public FeePaymentController(FeePaymentServiceI feePaymentServiceI) {
        this.feePaymentServiceI = feePaymentServiceI;
    }

    @GetMapping("/fee-payment")
    public ResponseEntity<ApiListResponse<FeePaymentDto>> getAllFeePayments() {
        return ResponseEntity.ok(
                ApiListResponse.of(feePaymentServiceI.getAllFeePayments())
        );
    }

    @GetMapping("/fee-payment/{id}")
    public ResponseEntity<ApiListResponse<FeePaymentDto>> getFeePaymentById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(feePaymentServiceI.getFeePaymentById(id))
        );
    }

    @PostMapping("/fee-payment")
    public ResponseEntity<ApiListResponse<FeePaymentDto>> addFeePayment(
            @RequestBody FeePaymentDto feePaymentDto) {
        FeePaymentDto created = feePaymentServiceI.addFeePayment(feePaymentDto);

        return ResponseEntity
                .created(URI.create("/api/v1/fee-payment/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/fee-payment/{id}")
    public ResponseEntity<ApiListResponse<FeePaymentDto>> updateFeePayment(
            @PathVariable long id,
            @RequestBody FeePaymentDto feePaymentDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        feePaymentServiceI.updateFeePayment(id, feePaymentDto)
                )
        );
    }

    @DeleteMapping("/fee-payment/{id}")
    public ResponseEntity<ApiListResponse<FeePaymentDto>> deleteFeePayment(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(feePaymentServiceI.deleteFeePayment(id))
        );
    }
}