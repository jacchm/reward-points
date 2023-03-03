package com.jacchm.project.adapter.api;

import lombok.Value;

import java.time.Instant;

@Value
public class DateRange {

  Instant from;
  Instant to;

}

