package io.elasticore.base;

import io.elasticore.base.model.DataModelComponent;
import io.elasticore.base.model.loader.ModelLoaderContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("ECoreModelContext Test Cases")
class ECoreModelContextTest {

    @Mock
    private ECoreModelContext mockContext;
    
    @Mock
    private ModelDomain mockDomain;
    
    @Mock
    private DataModelComponent mockComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getInternalDomainNames")
    void testGetInternalDomainNames() {
        String[] expectedDomains = {"domain1", "domain2"};
        when(mockContext.getInternalDomainNames()).thenReturn(expectedDomains);
        
        String[] actualDomains = mockContext.getInternalDomainNames();
        
        assertArrayEquals(expectedDomains, actualDomains);
    }

    @Test
    @DisplayName("Test getDomain operations")
    void testGetDomainOperations() {
        when(mockContext.getDomain()).thenReturn(mockDomain);
        when(mockContext.getDomain("testDomain")).thenReturn(mockDomain);
        
        assertEquals(mockDomain, mockContext.getDomain());
        assertEquals(mockDomain, mockContext.getDomain("testDomain"));
    }

    @Test
    @DisplayName("Test findModelComponent")
    void testFindModelComponent() {
        when(mockContext.findModelComponent("testModel")).thenReturn(mockComponent);
        when(mockContext.findModelComponent("domain1", "testModel")).thenReturn(mockComponent);
        
        assertEquals(mockComponent, mockContext.findModelComponent("testModel"));
        assertEquals(mockComponent, mockContext.findModelComponent("domain1", "testModel"));
    }

    @Test
    @DisplayName("Test component not found")
    void testComponentNotFound() {
        when(mockContext.findModelComponent("nonExistent")).thenReturn(null);
        
        assertNull(mockContext.findModelComponent("nonExistent"));
    }
}