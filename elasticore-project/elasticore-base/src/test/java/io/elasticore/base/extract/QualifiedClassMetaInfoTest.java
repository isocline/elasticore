package io.elasticore.base.extract;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QualifiedClassMetaInfoTest {

    @Test
    void testDefaultTypeParsing() {
        QualifiedClassMetaInfo info = QualifiedClassMetaInfo.of("com.example.MyClass");
        assertEquals("java", info.getType());
        assertEquals("java", info.getExtensionName());
        assertEquals("com.example.MyClass", info.getFullClassName());
        assertEquals("MyClass", info.getSimpleClassName());
    }

    @Test
    void testJavaTypeParsing() {
        QualifiedClassMetaInfo info = QualifiedClassMetaInfo.of("java://com.test.exam.TestClass");
        assertEquals("java", info.getType());
        assertEquals("java", info.getExtensionName());
        assertEquals("com.test.exam.TestClass", info.getFullClassName());
        assertEquals("TestClass", info.getSimpleClassName());
    }

    @Test
    void testProtoTypeParsing() {
        QualifiedClassMetaInfo info = QualifiedClassMetaInfo.of("proto://com.proto.Type");
        assertEquals("proto", info.getType());
        assertEquals("proto", info.getExtensionName());
        assertEquals("com.proto.Type", info.getFullClassName());
        assertEquals("Type", info.getSimpleClassName());
    }

    @Test
    void testNoPackageClassName() {
        QualifiedClassMetaInfo info = QualifiedClassMetaInfo.of("SimpleName");
        assertEquals("java", info.getType());
        assertEquals("java", info.getExtensionName());
        assertEquals("SimpleName", info.getFullClassName());
        assertEquals("SimpleName", info.getSimpleClassName());
    }

    @Test
    void testNullInput() {
        QualifiedClassMetaInfo info = QualifiedClassMetaInfo.of(null);
        assertNull(info.getType());
        assertNull(info.getExtensionName());
        assertNull(info.getFullClassName());
        assertNull(info.getSimpleClassName());
    }

    @Test
    void testEmptyInput() {
        QualifiedClassMetaInfo info = QualifiedClassMetaInfo.of("   ");
        assertNull(info.getType());
        assertNull(info.getExtensionName());
        assertNull(info.getFullClassName());
        assertNull(info.getSimpleClassName());
    }
}
