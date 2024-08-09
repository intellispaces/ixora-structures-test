package tech.intellispaces.ixora.test.structures.properties;

import intellispaces.ixora.structures.collection.List;
import intellispaces.ixora.structures.collection.ListHandle;
import intellispaces.ixora.structures.properties.PropertiesHandle;
import intellispaces.ixora.structures.properties.YamlStringToPropertiesMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.core.IntellispacesFramework;
import tech.intellispaces.core.system.Modules;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for guide {@link YamlStringToPropertiesMapper}.
 */
public abstract class YamlStringToPropertiesMapperTest {

  @BeforeEach
  public void init() {
    IntellispacesFramework.loadModule();
  }

  @AfterEach
  public void deinit() {
    Modules.currentModule().stop();
  }

  public abstract YamlStringToPropertiesMapper guide();

  @Test
  public void testEmptyYaml() {
    // Given
    String yaml = "";

    // When
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(0);
  }

  @Test
  public void testSimpleData() {
    // Given
    String yaml = """
        intValue: 1
        doubleValue: 2.2
        stringValue: abc
        """;

    // When
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

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
  public void testIntegerList() {
    // Given
    String yaml = """
        values:
          - 1
          - 2
          - 3
        """;

    // When
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(1);

    assertThat(properties.integerList("values").size()).isEqualTo(3);
    assertThat(properties.integerList("values").element(0)).isEqualTo(1);
    assertThat(properties.integerList("values").element(1)).isEqualTo(2);
    assertThat(properties.integerList("values").element(2)).isEqualTo(3);

    assertThat(((ListHandle<Integer>) properties.value("values")).size()).isEqualTo(3);
    assertThat(((ListHandle<Integer>) properties.value("values")).element(0)).isEqualTo(1);
    assertThat(((ListHandle<Integer>) properties.value("values")).element(1)).isEqualTo(2);
    assertThat(((ListHandle<Integer>) properties.value("values")).element(2)).isEqualTo(3);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testDoubleList() {
    // Given
    String yaml = """
        values:
          - 1.1
          - 2.2
          - 3.3
        """;

    // When
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(1);

    assertThat(properties.doubleList("values").size()).isEqualTo(3);
    assertThat(properties.doubleList("values").element(0)).isEqualTo(1.1);
    assertThat(properties.doubleList("values").element(1)).isEqualTo(2.2);
    assertThat(properties.doubleList("values").element(2)).isEqualTo(3.3);

    assertThat(((ListHandle<Double>) properties.value("values")).size()).isEqualTo(3);
    assertThat(((ListHandle<Double>) properties.value("values")).element(0)).isEqualTo(1.1);
    assertThat(((ListHandle<Double>) properties.value("values")).element(1)).isEqualTo(2.2);
    assertThat(((ListHandle<Double>) properties.value("values")).element(2)).isEqualTo(3.3);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testStringList() {
    // Given
    String yaml = """
        values:
          - a
          - "b"
          - c
        """;

    // When
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(1);

    assertThat(properties.stringList("values").size()).isEqualTo(3);
    assertThat(properties.stringList("values").element(0)).isEqualTo("a");
    assertThat(properties.stringList("values").element(1)).isEqualTo("b");
    assertThat(properties.stringList("values").element(2)).isEqualTo("c");

    assertThat(((ListHandle<String>) properties.value("values")).size()).isEqualTo(3);
    assertThat(((ListHandle<String>) properties.value("values")).element(0)).isEqualTo("a");
    assertThat(((ListHandle<String>) properties.value("values")).element(1)).isEqualTo("b");
    assertThat(((ListHandle<String>) properties.value("values")).element(2)).isEqualTo("c");
  }

  @Test
  public void testNestedData() {
    // Given
    String yaml = """
        value1: 1
        nestedValue:
          value2: abc
        """;

    // When
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(2);
    assertThat(properties.value("value1")).isEqualTo(1);
    assertThat(properties.value("nestedValue.value2")).isEqualTo("abc");
    assertThat(properties.propertiesValue("nestedValue").size()).isEqualTo(1);
  }
}
