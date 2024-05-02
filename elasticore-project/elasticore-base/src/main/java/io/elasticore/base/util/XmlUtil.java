package io.elasticore.base.util;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 */
public class XmlUtil {

    private final static XPath xPath = XPathFactory.newInstance().newXPath();


    public static NodeList getNodeList(Node node, String xpath) {

        try {
            NodeList list = (NodeList) xPath.evaluate(xpath, node, XPathConstants.NODESET);
            return list;
        } catch (XPathExpressionException xpe) {
            xpe.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param nodeList
     * @return
     */
    public static Iterable<ElementInfo> iterable(final NodeList nodeList) {
        return () -> new Iterator<ElementInfo>() {
            private int index = 0;

            public boolean hasNext() {
                if(nodeList==null) {
                    return false;
                }
                return index < nodeList.getLength();
            }

            public ElementInfo next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                int i = index++;
                return new ElementInfo(nodeList.item(i), i);

            }
        };
    }

    /**
     *
     *
     * @deprecated  too slow
     * @param node
     * @param xpath
     * @return
     */
    public static Node getNode(Node node, String xpath) {

        try {
            Node item = (Node) xPath.evaluate(xpath, node, XPathConstants.NODE);
            return item;
        } catch (XPathExpressionException xpe) {
            xpe.printStackTrace();
        }

        return null;
    }

    public static NodeList getChildNodeList(Element node, String elementName) {

        String[] elementNameArray = elementName.split("\\/");

        Element e = node;
        for(int i=0;i<elementNameArray.length-1;i++) {
            e = (Element) getNodeByName(e, elementNameArray[i]);
            if(e==null) {
                return null;
            }
        }
        return e.getElementsByTagName( elementNameArray[elementNameArray.length-1]);
    }

    public static Node getNodeByName(Node node, String name) {

        if (name == null || name.length() == 0) {
            return null;
        }

        NodeList nodeList = node.getChildNodes();

        int sz = nodeList.getLength();

        for (int i = 0; i < sz; i++) {
            Node n = nodeList.item(i);
            if (name.equals(n.getNodeName())) {
                return n;
            }
        }

        return null;
    }

    public static String getNodeText(Node node, String name) {
        Node n = getNodeByName(node, name);
        if(n!=null)
            return n.getTextContent();

        return null;
    }

    public static String getAttributeValue(Node node, String attributeName) {
        try {
            Element e = (Element) node;
            return e.getAttribute(attributeName);
        }catch (ClassCastException cce) {

        }
        return null;
    }

    public static class ElementInfo {
        private Node element;
        private int index;

        private ElementInfo(Node element, int index) {
            this.element=element;
            this.index = index;
        }

        public Element getElement() {
            return (Element) element;
        }

        public int getIndex() {
            return index;
        }
    }

}
