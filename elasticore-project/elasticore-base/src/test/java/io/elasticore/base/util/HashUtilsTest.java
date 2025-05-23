package io.elasticore.base.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HashUtils Test Cases")
class HashUtilsTest {

    @Test
    @DisplayName("Test SHA-256 hash generation")
    void testSha256() {
        String input = "hello world";
        String hash = HashUtils.sha256(input);
        
        assertNotNull(hash);
        assertEquals(64, hash.length());
        assertEquals(hash, HashUtils.sha256(input)); // consistency
    }

    @Test
    @DisplayName("Test MD5 hash generation")
    void testMd5() {
        String input = "hello world";
        String hash = HashUtils.md5(input);
        
        assertNotNull(hash);
        assertEquals(32, hash.length());
        assertEquals(hash, HashUtils.md5(input)); // consistency
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "hello", "123456"})
    @DisplayName("Test hash with various inputs")
    void testHashWithVariousInputs(String input) {
        String sha256Hash = HashUtils.sha256(input);
        String md5Hash = HashUtils.md5(input);
        
        assertEquals(64, sha256Hash.length());
        assertEquals(32, md5Hash.length());
        assertTrue(sha256Hash.matches("[a-f0-9]+"));
        assertTrue(md5Hash.matches("[a-f0-9]+"));
    }

    @Test
    @DisplayName("Test different inputs produce different hashes")
    void testDifferentInputsProduceDifferentHashes() {
        assertNotEquals(HashUtils.sha256("input1"), HashUtils.sha256("input2"));
        assertNotEquals(HashUtils.md5("input1"), HashUtils.md5("input2"));
    }
}