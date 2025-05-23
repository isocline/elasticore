package io.elasticore.base.model.entity;

import io.elasticore.base.model.core.Annotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Field Test Cases")
class FieldTest {

    @Mock
    private Annotation mockAnnotation;

    @Test
    @DisplayName("Test field creation")
    void testFieldCreation() {
        MockitoAnnotations.openMocks(this);
        
        Field field = Field.builder()
                .name("testField")
                .type("String")
                .isPrimaryKey(false)
                .unique(false)
                .build();
        
        assertNotNull(field);
        assertEquals("testField", field.getName());
        assertEquals("String", field.getType());
        assertFalse(field.isPrimaryKey());
        assertTrue(field.isNullable()); // default
    }

    @Test
    @DisplayName("Test primary key field")
    void testPrimaryKeyField() {
        Field field = Field.builder()
                .name("id")
                .type("Long")
                .isPrimaryKey(true)
                .build();
        
        assertTrue(field.isPrimaryKey());
    }

    @Test
    @DisplayName("Test field with length annotation")
    void testFieldWithLength() {
        MockitoAnnotations.openMocks(this);
        when(mockAnnotation.getValue()).thenReturn("255");
        
        Map<String, Annotation> annotationMap = new HashMap<>();
        annotationMap.put(EntityAnnotation.LENGTH, mockAnnotation);
        
        Field field = Field.builder()
                .name("description")
                .type("String")
                .annotationMap(annotationMap)
                .build();
        
        assertEquals(255, field.getLength());
    }

    @Test
    @DisplayName("Test getAnnotation")
    void testGetAnnotation() {
        MockitoAnnotations.openMocks(this);
        Map<String, Annotation> annotationMap = new HashMap<>();
        annotationMap.put("test", mockAnnotation);
        
        Field field = Field.builder()
                .name("testField")
                .annotationMap(annotationMap)
                .build();
        
        assertEquals(mockAnnotation, field.getAnnotation("test"));
        assertNull(field.getAnnotation("nonExistent"));
    }
}