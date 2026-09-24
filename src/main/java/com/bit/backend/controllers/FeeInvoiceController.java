package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.FeeInvoiceDto;
import com.bit.backend.services.FeeInvoiceServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class FeeInvoiceController {

    private final FeeInvoiceServiceI feeInvoiceServiceI;

    public FeeInvoiceController(FeeInvoiceServiceI feeInvoiceServiceI) {
        this.feeInvoiceServiceI = feeInvoiceServiceI;
    }

    @GetMapping("/fee-invoice")
    public ResponseEntity<ApiListResponse<FeeInvoiceDto>> getAllFeeInvoices() {
        return ResponseEntity.ok(
                ApiListResponse.of(feeInvoiceServiceI.getAllFeeInvoices())
        );
    }

    @GetMapping("/fee-invoice/{id}")
    public ResponseEntity<ApiListResponse<FeeInvoiceDto>> getFeeInvoiceById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(feeInvoiceServiceI.getFeeInvoiceById(id))
        );
    }

    @PostMapping("/fee-invoice")
    public ResponseEntity<ApiListResponse<FeeInvoiceDto>> addFeeInvoice(
            @RequestBody FeeInvoiceDto feeInvoiceDto) {
        FeeInvoiceDto created = feeInvoiceServiceI.addFeeInvoice(feeInvoiceDto);

        return ResponseEntity
                .created(URI.create("/api/v1/fee-invoice/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/fee-invoice/{id}")
    public ResponseEntity<ApiListResponse<FeeInvoiceDto>> updateFeeInvoice(
            @PathVariable long id,
            @RequestBody FeeInvoiceDto feeInvoiceDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        feeInvoiceServiceI.updateFeeInvoice(id, feeInvoiceDto)
                )
        );
    }

    @DeleteMapping("/fee-invoice/{id}")
    public ResponseEntity<ApiListResponse<FeeInvoiceDto>> deleteFeeInvoice(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(feeInvoiceServiceI.deleteFeeInvoice(id))
        );
    }
}