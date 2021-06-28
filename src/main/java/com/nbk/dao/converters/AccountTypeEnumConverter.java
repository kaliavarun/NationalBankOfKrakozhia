package com.nbk.dao.converters;

import com.nbk.dao.domain.account.AccountTypeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * Converter for converting the enumeration to Long persisting to database
 */
@Converter(autoApply = true)
public class AccountTypeEnumConverter implements AttributeConverter<AccountTypeEnum, Long> {

  @Override
  public Long convertToDatabaseColumn(AccountTypeEnum accountTypeEnum) {
    if (accountTypeEnum == null) {
      return null;
    }
    return accountTypeEnum.getCode();
  }

  @Override
  public AccountTypeEnum convertToEntityAttribute(Long code) {
    if (code == null) {
      return null;
    }

    return Stream.of(AccountTypeEnum.values())
        .filter(c -> c.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
