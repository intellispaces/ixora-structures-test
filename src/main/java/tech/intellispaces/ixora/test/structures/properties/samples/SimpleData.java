package tech.intellispaces.ixora.test.structures.properties.samples;

import tech.intellispaces.core.annotation.Data;
import tech.intellispaces.core.annotation.Domain;
import tech.intellispaces.core.annotation.Transition;

@Data
@Domain
public interface SimpleData {

  @Transition("6d8468c0-2cf2-4a95-9535-c731a40d17e9")
  Integer intValue();

  @Transition("8f1359e9-0adc-4881-8480-933110ae3912")
  Double doubleValue();

  @Transition("d083ddee-a5af-46fe-8f56-c63e20eca986")
  String stringValue();
}
