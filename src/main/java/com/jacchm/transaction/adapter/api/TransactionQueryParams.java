package com.jacchm.transaction.adapter.api;

import com.jacchm.transaction.domain.model.QueryParams;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Calendar;

@Slf4j
record TransactionQueryParams(
    @NotNull(message = "A month query parameter must be specified.")
    MonthEnum month,
    @NotNull(message = "A year query parameter must be specified.")
    Integer year,
    String customerId
) {

  QueryParams toDomainQueryParams() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MONTH, month.getMonthNumber());
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    final Instant fromDate = cal.getTime().toInstant();
    cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
    final Instant toDate = cal.getTime().toInstant();

    return QueryParams.builder()
        .customerId(customerId)
        .fromDate(fromDate)
        .toDate(toDate)
        .build();
  }

}

