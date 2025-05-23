package io.elasticore.base.model.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TypeInfo Test Cases")
class TypeInfoTest {

    @Test
    @DisplayName("Test simple type")
    void testSimpleType() {
        TypeInfo typeInfo = new TypeInfo("String");
        
        assertEquals("String", typeInfo.getBaseTypeName());
        assertFalse(typeInfo.isGenericType());
        assertTrue(typeInfo.isStringType());
    }

    @Test
    @DisplayName("Test generic type")
    void testGenericType() {
        TypeInfo typeInfo = new TypeInfo("List<String>");
        
        assertEquals("List", typeInfo.getBaseTypeName());
        assertEquals("String", typeInfo.getTypeParameterName());
        assertTrue(typeInfo.isGenericType());
        assertTrue(typeInfo.isList());
    }

    @ParameterizedTest
    @ValueSource(strings = {"String", "Integer", "Long", "Boolean"})
    @DisplayName("Test base types")
    void testBaseTypes(String typeName) {
        TypeInfo typeInfo = new TypeInfo(typeName);
        assertTrue(typeInfo.isBaseType());
    }

    @Test
    @DisplayName("Test invalid type")
    void testInvalidType() {
        assertThrows(IllegalArgumentException.class, () -> new TypeInfo(""));
        assertThrows(IllegalArgumentException.class, () -> new TypeInfo(null));
    }

    @Test
    @DisplayName("Test core item type")
    void testCoreItemType() {
        TypeInfo listType = new TypeInfo("List<String>");
        assertEquals("String", listType.getCoreItemType());
        
        TypeInfo simpleType = new TypeInfo("Integer");
        assertEquals("Integer", simpleType.getCoreItemType());
    }
}