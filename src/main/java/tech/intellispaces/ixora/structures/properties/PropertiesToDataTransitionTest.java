package tech.intellispaces.ixora.structures.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link PropertiesToDataTransition} guide.
 */
public class PropertiesToDataTransitionTest {

  public static void allTests(PropertiesToDataTransition guide) {
    testEmptyProperties(guide);
    testNotEmptyProperties(guide);
  }

  public static void testEmptyProperties(PropertiesToDataTransition guide) {
    // Given
    Properties properties = mock(Properties.class);

    // When
    DataSample data = guide.propertiesToData(properties, DataSample.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intPrimitiveValue()).isEqualTo(0);
    assertThat(data.doublePrimitiveValue()).isEqualTo(0);

    assertThat(data.intValue()).isEqualTo(null);
    assertThat(data.doubleValue()).isEqualTo(null);
    assertThat(data.stringValue()).isEqualTo(null);
  }

  public static void testNotEmptyProperties(PropertiesToDataTransition guide) {
    // Given
    Properties properties = mock(Properties.class);
    when(properties.value("intPrimitiveValue")).thenReturn(1);
    when(properties.value("doublePrimitiveValue")).thenReturn(2.2);
    when(properties.value("intValue")).thenReturn(3);
    when(properties.value("doubleValue")).thenReturn(4.4);
    when(properties.value("stringValue")).thenReturn("abc");

    // When
    DataSample data = guide.propertiesToData(properties, DataSample.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intPrimitiveValue()).isEqualTo(1);
    assertThat(data.doublePrimitiveValue()).isEqualTo(2.2);

    assertThat(data.intValue()).isEqualTo(3);
    assertThat(data.doubleValue()).isEqualTo(4.4);
    assertThat(data.stringValue()).isEqualTo("abc");
  }
}
