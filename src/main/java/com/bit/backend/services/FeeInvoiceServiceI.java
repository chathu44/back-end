package com.bit.backend.services;

import com.bit.backend.dtos.FeeInvoiceDto;

import java.util.List;

public interface FeeInvoiceServiceI {

    FeeInvoiceDto addFeeInvoice(FeeInvoiceDto feeInvoiceDto);
    List<FeeInvoiceDto> getAllFeeInvoices();
    FeeInvoiceDto getFeeInvoiceById(long id);
    FeeInvoiceDto updateFeeInvoice(long id, FeeInvoiceDto feeInvoiceDto);
    FeeInvoiceDto deleteFeeInvoice(long id);
}