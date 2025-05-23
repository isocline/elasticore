package io.elasticore.base.model.entity;

import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Items;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Entity Test Cases")
class EntityTest {

    @Mock
    private Items<Field> mockFields;
    
    @Mock
    private MetaInfo mockMetaInfo;

    @Test
    @DisplayName("Test entity creation")
    void testEntityCreation() {
        MockitoAnnotations.openMocks(this);
        
        Entity entity = Entity.create("testDomain", "TestEntity", mockMetaInfo, mockFields);
        
        assertNotNull(entity);
        assertEquals("TestEntity", entity.getIdentity().getName());
        assertEquals("testDomain", entity.getIdentity().getDomainId());
    }

    @Test
    @DisplayName("Test getTableName with camelCase conversion")
    void testGetTableName() {
        MockitoAnnotations.openMocks(this);
        when(mockMetaInfo.getMetaAnnotationValue(anyString())).thenReturn(null);
        
        Entity entity = Entity.create("testDomain", "UserAccount", mockMetaInfo, mockFields);
        
        assertEquals("user_account", entity.getTableName());
    }

    @Test
    @DisplayName("Test entity with null metaInfo")
    void testEntityWithNullMetaInfo() {
        MockitoAnnotations.openMocks(this);
        
        Entity entity = Entity.create("testDomain", "TestEntity", null, mockFields);
        
        assertNotNull(entity.getMetaInfo());
    }
}