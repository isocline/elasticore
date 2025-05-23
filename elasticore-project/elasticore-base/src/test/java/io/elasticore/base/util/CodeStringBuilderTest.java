package io.elasticore.base.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CodeStringBuilder Test Cases")
class CodeStringBuilderTest {

    private CodeStringBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new CodeStringBuilder();
    }

    @Test
    @DisplayName("Test basic append functionality")
    void testBasicAppend() {
        builder.append("hello");
        builder.append(" world");
        
        assertEquals("hello world", builder.toString());
    }

    @Test
    @DisplayName("Test append with newline")
    void testAppendWithNewline() {
        builder.appendLine("line1");
        builder.appendLine("line2");
        
        String result = builder.toString();
        assertTrue(result.contains("line1"));
        assertTrue(result.contains("line2"));
    }

    @Test
    @DisplayName("Test indentation")
    void testIndentation() {
        builder.increaseIndent();
        builder.appendLine("indented line");
        
        String result = builder.toString();
        assertTrue(result.trim().length() > "indented line".length());
    }

    @Test
    @DisplayName("Test clear functionality")
    void testClear() {
        builder.append("some text");
        builder.clear();
        
        assertEquals("", builder.toString());
    }

    @Test
    @DisplayName("Test isEmpty functionality")
    void testIsEmpty() {
        assertTrue(builder.isEmpty());
        
        builder.append("test");
        assertFalse(builder.isEmpty());
    }
}