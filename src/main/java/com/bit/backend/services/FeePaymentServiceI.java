package com.bit.backend.services;

import com.bit.backend.dtos.FeePaymentDto;

import java.util.List;

public interface FeePaymentServiceI {

    FeePaymentDto addFeePayment(FeePaymentDto feePaymentDto);
    List<FeePaymentDto> getAllFeePayments();
    FeePaymentDto getFeePaymentById(long id);
    FeePaymentDto updateFeePayment(long id, FeePaymentDto feePaymentDto);
    FeePaymentDto deleteFeePayment(long id);
}