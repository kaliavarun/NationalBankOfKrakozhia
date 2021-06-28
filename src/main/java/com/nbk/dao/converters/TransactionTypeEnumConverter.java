package com.nbk.dao.converters;

import com.nbk.dao.domain.account.TransactionTypeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/** Converter for converting the enumeration to Long persisting to database */
@Converter(autoApply = true)
public class TransactionTypeEnumConverter implements AttributeConverter<TransactionTypeEnum, Long> {

  @Override
  public Long convertToDatabaseColumn(TransactionTypeEnum transactionTypeEnum) {
    if (transactionTypeEnum == null) {
      return null;
    }
    return transactionTypeEnum.getCode();
  }

  @Override
  public TransactionTypeEnum convertToEntityAttribute(Long code) {
    if (code == null) {
      return null;
    }

    return Stream.of(TransactionTypeEnum.values())
        .filter(c -> c.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
