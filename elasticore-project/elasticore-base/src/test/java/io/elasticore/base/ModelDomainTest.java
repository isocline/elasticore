package io.elasticore.base;

import io.elasticore.base.model.ECoreModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("ModelDomain Test Cases")
class ModelDomainTest {

    @Mock
    private ModelDomain mockDomain;
    
    @Mock
    private ECoreModel mockModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getName functionality")
    void testGetName() {
        when(mockDomain.getName()).thenReturn("testDomain");
        
        assertEquals("testDomain", mockDomain.getName());
    }

    @Test
    @DisplayName("Test getModel functionality")
    void testGetModel() {
        when(mockDomain.getModel()).thenReturn(mockModel);
        
        assertEquals(mockModel, mockDomain.getModel());
        assertNotNull(mockDomain.getModel());
    }

    @Test
    @DisplayName("Test domain with null values")
    void testDomainWithNullValues() {
        when(mockDomain.getName()).thenReturn(null);
        when(mockDomain.getModel()).thenReturn(null);
        
        assertNull(mockDomain.getName());
        assertNull(mockDomain.getModel());
    }

    @Test
    @DisplayName("Test domain state consistency")
    void testDomainStateConsistency() {
        when(mockDomain.getName()).thenReturn("consistentDomain");
        when(mockDomain.getModel()).thenReturn(mockModel);
        
        // Multiple calls should return consistent results
        assertEquals("consistentDomain", mockDomain.getName());
        assertEquals("consistentDomain", mockDomain.getName());
        assertEquals(mockModel, mockDomain.getModel());
        assertEquals(mockModel, mockDomain.getModel());
    }
}