package tech.intellispaces.ixora.structures.properties;

import tech.intellispaces.framework.core.annotation.Data;
import tech.intellispaces.framework.core.annotation.Domain;

@Data
@Domain
public interface DataSample {

  int intPrimitiveValue();

  double doublePrimitiveValue();

  Integer intValue();

  Double doubleValue();

  String stringValue();
}
