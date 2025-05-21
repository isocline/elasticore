package io.elasticore.base.util;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PropertyNodeTest {

    private static final String YAML_CONTENT = "transaction:\n" +
            "  dto:\n" +
            "    BoardInfo:\n" +
            "      fields:\n" +
            "        brand: string -- 브랜드명\n" +
            "\n" +
            "    CarInfoOutput:\n" +
            "      fields:\n" +
            "        brand: string -- 브랜드명\n" +
            "        description: string -- 설명\n" +
            "        modelId: string -- 모델 ID\n" +
            "\n" +
            "  port:\n" +
            "    BoardService:\n" +
            "      meta: dbms @datasource(\"main\")\n" +
            "      methods:\n" +
            "\n" +
            "        getBoardInfo:\n" +
            "          params:\n" +
            "            input: CarInfoInput\n" +
            "          return: BoardInfo\n" +
            "\n" +
            "        getArticleList:\n" +
            "          params:\n" +
            "            page: int\n" +
            "            count: int\n" +
            "          return: List<Article>\n";

    @Test
    public void testFromYaml() {
        InputStream is = new ByteArrayInputStream(YAML_CONTENT.getBytes());
        PropertyNode node = PropertyNode.fromYaml(is);

        assertNotNull(node);
        assertEquals("dbms @datasource(\"main\")", node.getMap("transaction.port.BoardService").getString("meta"));
        assertEquals("CarInfoInput", node.getMap("transaction.port.BoardService.methods.getBoardInfo.params").getString("input"));
        assertEquals("BoardInfo", node.getMap("transaction.port.BoardService.methods.getBoardInfo").getString("return"));
        assertEquals("int", node.getMap("transaction.port.BoardService.methods.getArticleList.params").getString("page"));
        assertEquals("List<Article>", node.getMap("transaction.port.BoardService.methods.getArticleList").getString("return"));
    }

    @Test
    public void testGetMapWithInvalidPath() {
        InputStream is = new ByteArrayInputStream(YAML_CONTENT.getBytes());
        PropertyNode node = PropertyNode.fromYaml(is);

        assertNotNull(node);
        PropertyNode invalidNode = node.getMap("invalid.path");
        assertNotNull(invalidNode);
        assertNull(invalidNode.getString("nonexistent"));
    }

    @Test
    public void testDefaultValue() {
        InputStream is = new ByteArrayInputStream(YAML_CONTENT.getBytes());
        PropertyNode node = PropertyNode.fromYaml(is);

        assertNotNull(node);
        assertEquals("default", node.getMap("transaction.port.BoardService").getString("nonexistent", "default"));
        assertEquals(60, node.getMap("transaction.port.BoardService").getInt("nonexistent", 60));
        assertFalse(node.getMap("transaction.port.BoardService").getBoolean("nonexistent", false));
    }
}