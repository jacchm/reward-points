package com.jacchm.transaction.adapter.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

class BigDecimalCustomDeserializer extends NumberDeserializers.BigDecimalDeserializer {

  @Override
  public BigDecimal deserialize(JsonParser parser, DeserializationContext context) throws IOException {

    return super.deserialize(parser, context).setScale(2, RoundingMode.HALF_EVEN);
  }
}
