package tech.intellispaces.ixora.structures.properties;

import tech.intellispaces.framework.core.annotation.Data;
import tech.intellispaces.framework.core.annotation.Domain;

public interface DataSamples {

  @Data
  @Domain
  interface PrimitiveData {
    int intValue();
    double doubleValue();
  }

  @Data
  @Domain
  interface SimpleData {
    Integer intValue();
    Double doubleValue();
    String stringValue();
  }

  @Data
  @Domain
  interface NestedData {
    String stringValue();
    NestedData nestedValue();
  }
}
