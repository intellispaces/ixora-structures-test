package tech.intellispaces.ixora.test.structures.properties;

import intellispaces.ixora.structures.properties.PropertiesHandle;
import intellispaces.ixora.structures.properties.PropertiesToDataMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.core.IntellispacesFramework;
import tech.intellispaces.core.system.Modules;
import tech.intellispaces.ixora.test.structures.properties.samples.NestedDataHandle;
import tech.intellispaces.ixora.test.structures.properties.samples.PrimitiveDataHandle;
import tech.intellispaces.ixora.test.structures.properties.samples.SimpleDataHandle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for guide {@link PropertiesToDataMapper}.
 */
public abstract class PropertiesToDataMapperTest {

  @BeforeEach
  public void init() {
    IntellispacesFramework.loadModule();
  }

  @AfterEach
  public void destroy() {
    Modules.current().stop();
  }

  public abstract PropertiesToDataMapper guide();

  @Test
  public void testPrimitiveData_whenEmptyProperties() {
    // Given
    PropertiesHandle properties = mock(PropertiesHandle.class);

    // When
    PrimitiveDataHandle data = guide().propertiesToData(properties, PrimitiveDataHandle.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(0);
    assertThat(data.doubleValue()).isEqualTo(0);
  }

  @Test
  public void testSimpleData_whenEmptyProperties() {
    // Given
    PropertiesHandle properties = mock(PropertiesHandle.class);

    // When
    SimpleDataHandle data = guide().propertiesToData(properties, SimpleDataHandle.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isNull();
    assertThat(data.doubleValue()).isNull();
    assertThat(data.stringValue()).isNull();
  }

  @Test
  public void testNestedData_whenEmptyProperties() {
    // Given
    PropertiesHandle properties = mock(PropertiesHandle.class);

    // When
    NestedDataHandle data = guide().propertiesToData(properties, NestedDataHandle.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isNull();
    assertThat(data.nestedValue()).isNull();
  }

  @Test
  public void testPrimitiveData_whenNotEmptyProperties() {
    // Given
    PropertiesHandle properties = mock(PropertiesHandle.class);
    when(properties.value("intValue")).thenReturn(1);
    when(properties.value("doubleValue")).thenReturn(2.2);

    // When
    PrimitiveDataHandle data = guide().propertiesToData(properties, PrimitiveDataHandle.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(1);
    assertThat(data.doubleValue()).isEqualTo(2.2);
  }

  @Test
  public void testSimpleData_whenNotEmptyProperties() {
    // Given
    PropertiesHandle properties = mock(PropertiesHandle.class);
    when(properties.value("intValue")).thenReturn(1);
    when(properties.value("doubleValue")).thenReturn(2.2);
    when(properties.value("stringValue")).thenReturn("abc");

    // When
    SimpleDataHandle data = guide().propertiesToData(properties, SimpleDataHandle.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(1);
    assertThat(data.doubleValue()).isEqualTo(2.2);
    assertThat(data.stringValue()).isEqualTo("abc");
  }

  @Test
  public void testNestedData_whenNotEmptyProperties() {
    // Given
    PropertiesHandle properties = mock(PropertiesHandle.class);
    PropertiesHandle nestedProperties = mock(PropertiesHandle.class);
    when(properties.value("stringValue")).thenReturn("abc");
    when(properties.value("nestedValue")).thenReturn(nestedProperties);
    when(nestedProperties.value("stringValue")).thenReturn("def");

    // When
    NestedDataHandle data = guide().propertiesToData(properties, NestedDataHandle.class);

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isEqualTo("abc");
    assertThat(data.nestedValue()).isNotNull();
    assertThat(data.nestedValue().stringValue()).isEqualTo("def");
    assertThat(data.nestedValue().nestedValue()).isNull();
  }
}
