package tech.mindstructs.test.structures.properties;

import tech.intellispaces.framework.core.annotation.Data;
import tech.intellispaces.framework.core.annotation.Domain;
import tech.intellispaces.framework.core.annotation.Transition;

public interface DataSamples {

  @Data
  @Domain
  interface PrimitiveData {
    @Transition("756b6fa0-5d0c-4143-bf40-fcf171ae9fe9")
    int intValue();

    @Transition("3b17bdd4-525c-4616-890f-444045e65346")
    double doubleValue();
  }

  @Data
  @Domain
  interface SimpleData {
    @Transition("6d8468c0-2cf2-4a95-9535-c731a40d17e9")
    Integer intValue();

    @Transition("8f1359e9-0adc-4881-8480-933110ae3912")
    Double doubleValue();

    @Transition("d083ddee-a5af-46fe-8f56-c63e20eca986")
    String stringValue();
  }

  @Data
  @Domain
  interface NestedData {
    @Transition("8119ce95-9bc3-4825-858b-babdab0014ed")
    String stringValue();

    @Transition("8a17ef38-5f5b-4bd4-9c3a-a4c0c3fa37ef")
    NestedData nestedValue();
  }
}
