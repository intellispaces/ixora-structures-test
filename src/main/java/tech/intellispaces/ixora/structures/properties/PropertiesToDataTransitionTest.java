package tech.intellispaces.ixora.structures.properties;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link PropertiesToDataTransition} guide.
 */
public interface PropertiesToDataTransitionTest {

  PropertiesToDataTransition guide();

  @Test
  default void testPrimitiveData_whenEmptyProperties() {
    // Given
    Properties properties = mock(Properties.class);

    // When
    DataSamples.PrimitiveData data = guide().propertiesToData(properties, DataSamples.PrimitiveData.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(0);
    assertThat(data.doubleValue()).isEqualTo(0);
  }

  @Test
  default void testSimpleData_whenEmptyProperties() {
    // Given
    Properties properties = mock(Properties.class);

    // When
    DataSamples.SimpleData data = guide().propertiesToData(properties, DataSamples.SimpleData.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isNull();
    assertThat(data.doubleValue()).isNull();
    assertThat(data.stringValue()).isNull();
  }

  @Test
  default void testNestedData_whenEmptyProperties() {
    // Given
    Properties properties = mock(Properties.class);

    // When
    DataSamples.NestedData data = guide().propertiesToData(properties, DataSamples.NestedData.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isNull();
    assertThat(data.nestedValue()).isNull();
  }

  @Test
  default void testPrimitiveData_whenNotEmptyProperties() {
    // Given
    Properties properties = mock(Properties.class);
    when(properties.value("intValue")).thenReturn(1);
    when(properties.value("doubleValue")).thenReturn(2.2);

    // When
    DataSamples.PrimitiveData data = guide().propertiesToData(properties, DataSamples.PrimitiveData.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(1);
    assertThat(data.doubleValue()).isEqualTo(2.2);
  }

  @Test
  default void testSimpleData_whenNotEmptyProperties() {
    // Given
    Properties properties = mock(Properties.class);
    when(properties.value("intValue")).thenReturn(1);
    when(properties.value("doubleValue")).thenReturn(2.2);
    when(properties.value("stringValue")).thenReturn("abc");

    // When
    DataSamples.SimpleData data = guide().propertiesToData(properties, DataSamples.SimpleData.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(1);
    assertThat(data.doubleValue()).isEqualTo(2.2);
    assertThat(data.stringValue()).isEqualTo("abc");
  }

  @Test
  default void testNestedData_whenNotEmptyProperties() {
    // Given
    Properties properties = mock(Properties.class);
    Properties nestedProperties = mock(Properties.class);
    when(properties.value("stringValue")).thenReturn("abc");
    when(properties.value("nestedValue")).thenReturn(nestedProperties);
    when(nestedProperties.value("stringValue")).thenReturn("def");

    // When
    DataSamples.NestedData data = guide().propertiesToData(properties, DataSamples.NestedData.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isEqualTo("abc");
    assertThat(data.nestedValue()).isNotNull();
    assertThat(data.nestedValue().stringValue()).isEqualTo("def");
    assertThat(data.nestedValue().nestedValue()).isNull();
  }
}
