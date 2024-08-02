package tech.intellispaces.ixora.test.structures.properties.samples;

import tech.intellispaces.core.annotation.Data;
import tech.intellispaces.core.annotation.Domain;
import tech.intellispaces.core.annotation.Transition;

@Data
@Domain
public interface NestedData {

  @Transition("8119ce95-9bc3-4825-858b-babdab0014ed")
  String stringValue();

  @Transition("8a17ef38-5f5b-4bd4-9c3a-a4c0c3fa37ef")
  NestedData nestedValue();
}
