package tech.intellispaces.ixora.structures.properties;

import org.junit.jupiter.api.Test;
import tech.intellispaces.ixora.structures.collection.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for guide {@link YamlStringToPropertiesGuide}.
 */
public interface YamlStringToPropertiesGuideTest {

  YamlStringToPropertiesGuide guide();

  @Test
  default void testEmptyYaml() {
    // Given
    String yaml = "";

    // When
    Properties properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(0);
  }

  @Test
  default void testSimpleData() {
    // Given
    String yaml = """
        intValue: 1
        doubleValue: 2.2
        stringValue: abc
        """;

    // When
    Properties properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(3);

    assertThat(properties.integerValue("intValue")).isEqualTo(1);
    assertThat(properties.value("intValue")).isEqualTo(1);

    assertThat(properties.doubleValue("doubleValue")).isEqualTo(2.2);
    assertThat(properties.value("doubleValue")).isEqualTo(2.2);

    assertThat(properties.stringValue("stringValue")).isEqualTo("abc");
    assertThat(properties.value("stringValue")).isEqualTo("abc");
  }

  @Test
  @SuppressWarnings("unchecked")
  default void testIntegerList() {
    // Given
    String yaml = """
        values:
          - 1
          - 2
          - 3
        """;

    // When
    Properties properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(1);

    assertThat(properties.integerList("values").size()).isEqualTo(3);
    assertThat(properties.integerList("values").element(0)).isEqualTo(1);
    assertThat(properties.integerList("values").element(1)).isEqualTo(2);
    assertThat(properties.integerList("values").element(2)).isEqualTo(3);

    assertThat(((List<Integer>) properties.value("values")).size()).isEqualTo(3);
    assertThat(((List<Integer>) properties.value("values")).element(0)).isEqualTo(1);
    assertThat(((List<Integer>) properties.value("values")).element(1)).isEqualTo(2);
    assertThat(((List<Integer>) properties.value("values")).element(2)).isEqualTo(3);
  }

  @Test
  @SuppressWarnings("unchecked")
  default void testDoubleList() {
    // Given
    String yaml = """
        values:
          - 1.1
          - 2.2
          - 3.3
        """;

    // When
    Properties properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(1);

    assertThat(properties.doubleList("values").size()).isEqualTo(3);
    assertThat(properties.doubleList("values").element(0)).isEqualTo(1.1);
    assertThat(properties.doubleList("values").element(1)).isEqualTo(2.2);
    assertThat(properties.doubleList("values").element(2)).isEqualTo(3.3);

    assertThat(((List<Double>) properties.value("values")).size()).isEqualTo(3);
    assertThat(((List<Double>) properties.value("values")).element(0)).isEqualTo(1.1);
    assertThat(((List<Double>) properties.value("values")).element(1)).isEqualTo(2.2);
    assertThat(((List<Double>) properties.value("values")).element(2)).isEqualTo(3.3);
  }

  @Test
  @SuppressWarnings("unchecked")
  default void testStringList() {
    // Given
    String yaml = """
        values:
          - a
          - "b"
          - c
        """;

    // When
    Properties properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(1);

    assertThat(properties.stringList("values").size()).isEqualTo(3);
    assertThat(properties.stringList("values").element(0)).isEqualTo("a");
    assertThat(properties.stringList("values").element(1)).isEqualTo("b");
    assertThat(properties.stringList("values").element(2)).isEqualTo("c");

    assertThat(((List<String>) properties.value("values")).size()).isEqualTo(3);
    assertThat(((List<String>) properties.value("values")).element(0)).isEqualTo("a");
    assertThat(((List<String>) properties.value("values")).element(1)).isEqualTo("b");
    assertThat(((List<String>) properties.value("values")).element(2)).isEqualTo("c");
  }

  @Test
  default void testNestedData() {
    // Given
    String yaml = """
        value1: 1
        nestedValue:
          value2: abc
        """;

    // When
    Properties properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(2);
    assertThat(properties.value("value1")).isEqualTo(1);
    assertThat(properties.value("nestedValue.value2")).isEqualTo("abc");
    assertThat(properties.propertiesValue("nestedValue").size()).isEqualTo(1);
  }
}
