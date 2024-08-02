package tech.intellispaces.ixora.test.structures.properties.samples;

import tech.intellispaces.core.annotation.Data;
import tech.intellispaces.core.annotation.Domain;
import tech.intellispaces.core.annotation.Transition;

@Data
@Domain
public interface PrimitiveData {

  @Transition("756b6fa0-5d0c-4143-bf40-fcf171ae9fe9")
  int intValue();

  @Transition("3b17bdd4-525c-4616-890f-444045e65346")
  double doubleValue();
}
