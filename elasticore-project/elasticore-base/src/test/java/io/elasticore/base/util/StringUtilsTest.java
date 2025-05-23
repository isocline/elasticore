package io.elasticore.base.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StringUtils Test Cases")
class StringUtilsTest {

    @Test
    @DisplayName("Test capitalize method")
    void testCapitalize() {
        assertEquals("Hello", StringUtils.capitalize("hello"));
        assertEquals("World", StringUtils.capitalize("WORLD"));
        assertNull(StringUtils.capitalize(null));
    }

    @Test
    @DisplayName("Test uncapitalize method")
    void testUncapitalize() {
        assertEquals("hello", StringUtils.uncapitalize("Hello"));
        assertEquals("wORLD", StringUtils.uncapitalize("WORLD"));
    }

    @ParameterizedTest
    @CsvSource({
        "camelCase, camel_case",
        "CamelCase, camel_case",
        "userName, user_name"
    })
    @DisplayName("Test camelToSnake conversion")
    void testCamelToSnake(String input, String expected) {
        assertEquals(expected, StringUtils.camelToSnake(input));
    }

    @ParameterizedTest
    @CsvSource({
        "snake_case, snakeCase",
        "user_name, userName"
    })
    @DisplayName("Test snakeToCamel conversion")
    void testSnakeToCamel(String input, String expected) {
        assertEquals(expected, StringUtils.snakeToCamel(input));
    }

    @Test
    @DisplayName("Test extractVariables from SQL")
    void testExtractVariables() {
        String sql = "SELECT * FROM users WHERE id = :userId AND name = :userName";
        List<String> variables = StringUtils.extractVariables(sql);
        
        assertEquals(2, variables.size());
        assertTrue(variables.contains("userId"));
        assertTrue(variables.contains("userName"));
    }

    @Test
    @DisplayName("Test removeQuotes")
    void testRemoveQuotes() {
        assertEquals("hello", StringUtils.removeQuotes("'hello'"));
        assertEquals("hello", StringUtils.removeDoubleQuotes("\"hello\""));
        assertNull(StringUtils.removeQuotes(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"hello", "world", "  test  "})
    @DisplayName("Test hasValue with valid strings")
    void testHasValueWithValidStrings(String input) {
        assertTrue(StringUtils.hasValue(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t\n"})
    @DisplayName("Test hasValue with invalid strings")
    void testHasValueWithInvalidStrings(String input) {
        assertFalse(StringUtils.hasValue(input));
    }
}