package io.elasticore.base.model.loader.px;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.model.loader.module.AbstractModelLoader;
import io.elasticore.base.util.StringUtils;
import io.elasticore.base.util.XmlUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class PxDataTransferModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<DataTransfer> {

    private ModelLoaderContext ctx;

    private boolean isRequestAttr(Node n) {
        try {
            Node node = XmlUtil.getNodeByName(XmlUtil.getNodeByName(n, "ValueSource"), "Name");
            if ("Entered".equals(node.getTextContent()))
                return true;
        } catch (NullPointerException npe) {
        }

        return false;
    }


    @Override
    public boolean loadModel(ModelLoaderContext ctx, FileSource source) {
        this.ctx = ctx;
        Element e = source.getElement();

        loadMappingPathMap(e);
        loadStructureInfo(e);
        loadDataTypeInfo(e);

        loadStrucuture(ctx, e);

        return false;
    }

    protected void loadStrucuture(ModelLoaderContext ctx, Element e) {
        NodeList list = XmlUtil.getNodeList(e, "/DeploymentPackage/DeploymentItem/DeployedPXML/FPMExport/StructureElement");

        XmlUtil.iterable(list).forEach(info -> {
            Element StructureElement = info.getElement();

            DataTransfer dataTransfer = loadDataTransfer(StructureElement);

            ctx.getDataTransferItems().addItem(dataTransfer);

        });
    }

    private Map<String, ValueDefinition> ValueDefinitionMap = new HashMap<>();


    private StringBuilder attributeTextContent = new StringBuilder();

    protected void loadMappingPathMap(Element e) {
        NodeList list = null;
        list = XmlUtil.getNodeList(e, "/DeploymentPackage/DeploymentItem/DeployedPXML/FPMExport/StructureElement/Operation/ValueSource/Expression/expr/csymbol");

        XmlUtil.iterable(list).forEach(info -> {
            String content = info.getElement().getAttribute("definitionURL");
            attributeTextContent.append(content);
        });


        list = XmlUtil.getNodeList(e, "/DeploymentPackage/DeploymentItem/DeployedPXML/FPMExport/Function/Expression/expr/piecewise/piece/apply/piecewise/piece/apply/apply/cn");

        XmlUtil.iterable(list).forEach(info -> {
            String content = info.getElement().getTextContent();
            attributeTextContent.append(content);
        });

    }


    protected void loadStructureInfo(Element e) {
        NodeList list = XmlUtil.getNodeList(e, "/DeploymentPackage/DeploymentItem/DeployedPXML/FPMExport/StructureElement");

        XmlUtil.iterable(list).forEach(info -> {
            Element vdElement = info.getElement();

            ValueDefinition valueDefinition = getValueDefinition(vdElement);
            ValueDefinitionMap.put(valueDefinition.getLid(), valueDefinition);
        });
    }

    ///DeploymentPackage/DeploymentItem[@id='FPM:7aed5b1e-e5de-41c6-82d7-42796dc6cc67.FinProductDeliverable']/DeployedPXML/FPMExport/ValueDefinition[@id='ID:02d46520-2ee5-4a78-b785-eef824f51dfd']
    protected void loadDataTypeInfo(Element e) {
        NodeList list = XmlUtil.getNodeList(e, "/DeploymentPackage/DeploymentItem/DeployedPXML/FPMExport/ValueDefinition");

        XmlUtil.iterable(list).forEach(info -> {
            Element vdElement = info.getElement();

            ValueDefinition valueDefinition = getValueDefinition(vdElement);
            ValueDefinitionMap.put(valueDefinition.getLid(), valueDefinition);
        });
    }

    private ValueDefinition getValueDefinition(Element e) {

        String id = XmlUtil.getAttributeValue(e, "id");
        String vid = XmlUtil.getAttributeValue(e, "vid");
        String lid = XmlUtil.getAttributeValue(e, "lid");

        String name = XmlUtil.getNodeText(e, "Name");
        String documentation = XmlUtil.getNodeText(e, "Documentation");
        String structure = XmlUtil.getNodeText(e, "Structure");
        String dataType = XmlUtil.getNodeText(e, "DataType");

        return ValueDefinition.builder()
                .id(id)
                .vid(vid)
                .lid(lid)
                .name(name)
                .documentation(documentation)
                .structure(structure)
                .dataType(dataType)
                .build();
    }


    protected DataTransfer loadDataTransfer(Element StructureElement) {
        Node node = XmlUtil.getNodeByName(StructureElement, "Name");
        String name = node.getTextContent();
        //System.err.println(">> " + name);

        Items<Field> fieldItems = loadFieldData(StructureElement);
        return DataTransfer.create(ctx.getDomainId(), name, fieldItems, createMetaInfo(StructureElement));
    }

    private MetaInfo createMetaInfo(Element StructureElement) {

        Map<String, Annotation> infoAnnotation = new HashMap<>();
        Map<String, Annotation> metaAnnotation = new HashMap<>();

        Node node = XmlUtil.getNodeByName(StructureElement, "Documentation");
        if (node != null) {
            String desc = node.getTextContent();
            if (desc != null && desc.length() > 0) {
                Annotation ant = Annotation.create("description", desc);
                infoAnnotation.put(ant.getName(), ant);
            }
        }

        return MetaInfo.creat(infoAnnotation, metaAnnotation);
    }


    protected void setAnnotationMap(Map<String, Annotation> annotationMap, ValueDefinition vd) {

        String id = vd.getId();
        String vid = vd.getVid();
        String lid = vd.getLid();
        Annotation annotation1 = Annotation.create("id", id);
        Annotation annotation2 = Annotation.create("vid", vid);
        Annotation annotation3 = Annotation.create("lid", lid);

        annotationMap.put(annotation1.getName(), annotation1);
        annotationMap.put(annotation2.getName(), annotation2);
        annotationMap.put(annotation3.getName(), annotation3);
    }

    protected Items<Field> loadFieldData(Element StructureElement) {

        Items<Field> fieldItems = Items.create(Field.class);

        NodeList attrList = XmlUtil.getNodeList(StructureElement, "Attribute");
        XmlUtil.iterable(attrList).forEach(attr -> {
            Element attrElmnt = attr.getElement();
            Node node = XmlUtil.getNodeByName(attrElmnt, "Name");
            String fieldName = node.getTextContent();

            Node node2 = XmlUtil.getNodeByName(attrElmnt, "Documentation");
            String desc = null;
            if (node2 != null)
                desc = node2.getTextContent();


            //System.err.println(" --  " + fieldName + " " + isRequestAttr(attrElmnt));

            Map<String, Annotation> annotationMap = getAnnotationMap(attrElmnt);


            String type = "String";
            try {
                Annotation typeAnnotation = annotationMap.get("typeref");
                String typeLid = typeAnnotation.getValue();

                ValueDefinition vd = this.ValueDefinitionMap.get(typeLid);
                type = getTypeName(vd);

                //setAnnotationMap(annotationMap, vd);

            } catch (NullPointerException npe) {
            }


            Field f = Field.builder().annotationMap(annotationMap)
                    .name(fieldName).type(type)
                    .description(desc)
                    .build();
            fieldItems.addItem(f);

        });

        //StructureLink
        NodeList structureLinkList = XmlUtil.getNodeList(StructureElement, "StructureLink");
        XmlUtil.iterable(structureLinkList).forEach(attr -> {
            Element structureLink = attr.getElement();

            Element target = (Element) XmlUtil.getNodeByName(structureLink, "Target");
            String lidRef = target.getAttribute("lid-ref");


            ValueDefinition vd = ValueDefinitionMap.get(lidRef);
            if (vd != null) {
                Element multiple = (Element) XmlUtil.getNodeByName(structureLink, "Multiplicity");
                String name = vd.getName();
                String type = multiple.getAttribute("type");
                //<Multiplicity type='multiple' mandatory='false'/>
                String fieldType = null;
                String fieldName = null;
                if ("multiple".equals(type)) {
                    fieldType = "List<" + name + ">";
                    fieldName = StringUtils.uncapitalize(name) + "List";
                    //fieldName = StringUtils.uncapitalize(name);
                } else {
                    fieldType = name;
                    fieldName = StringUtils.uncapitalize(name);
                }

                Map<String, Annotation> annotationMap = getAnnotationMap(multiple);
                Field f = Field.builder()
                        .annotationMap(annotationMap)
                        .name(fieldName)
                        .type(fieldType)
                        .description(vd.getDocumentation())
                        .build();

                fieldItems.addItem(f);
            }


        });

        return fieldItems;
    }

    private String getTypeName(ValueDefinition vd) {
        String dt = vd.getDataType();
        if ("real".equals(dt))
            return "Double";


        return "String";
    }

    protected Map<String, Annotation> getAnnotationMap(Element attrElmnt) {
        Map<String, Annotation> annotationMap = new HashMap<>();

        NamedNodeMap namedNodeMap = attrElmnt.getAttributes();

        for (int i = 0; i < namedNodeMap.getLength(); i++) {
            Node n = namedNodeMap.item(i);
            String key = n.getNodeName();
            String val = n.getNodeValue();
            Annotation annotation = Annotation.create(key, val);
            annotationMap.put(key, annotation);
        }

        try {
            String kind = XmlUtil.getNodeList(attrElmnt, "ValueSource/Kind").item(0).getTextContent();
            Annotation annotation = Annotation.create("kind", kind);
            annotationMap.put("kind", annotation);
        } catch (NullPointerException npe) {
        }

        //<TypeRef lid-ref='ID:151ea245-6afb-4650-bba0-31d0f33cc9d4' type='ValueDefinition' def='external'/>
        try {
            NamedNodeMap namedNodeMap1 = XmlUtil.getNodeByName(attrElmnt, "TypeRef").getAttributes();
            String typeref = namedNodeMap1.getNamedItem("lid-ref").getNodeValue();
            Annotation annotation = Annotation.create("typeref", typeref);
            annotationMap.put("typeref", annotation);
        } catch (NullPointerException npe) {
        }

        String lid = null;
        try {
            lid = attrElmnt.getAttributes().getNamedItem("lid").getNodeValue();

            String lidOnly = lid.substring(3);
            String findKey = "@attribute(id:" + lidOnly + ")";


            if( attributeTextContent.indexOf(findKey)>=0) {
                Annotation reqAnnt = Annotation.create("calcRequired", "true");
                annotationMap.put(reqAnnt.getName(), reqAnnt);
            }


        } catch (RuntimeException re) {
        }

        return annotationMap;


    }

    /**
     * <ValueDefinition id='ID:02d46520-2ee5-4a78-b785-eef824f51dfd' vid='ID:a91b863c-de34-4adf-bcc3-4d836467a69c' lid='ID:e5d0e982-8434-4a46-b436-d34e45a0c94c'>
     * <Name>Table ID</Name>
     * <CreationTime>2012-07-18 12:32:08</CreationTime><VersionNumber>3.0</VersionNumber>
     * <Documentation>Value definition used to represent a ProductXpress table.</Documentation>
     * <Structure>scalar</Structure>
     * <DataType>tableId</DataType>
     * </ValueDefinition>
     */
    @Getter
    @Builder
    private static class ValueDefinition {
        private String id;
        private String vid;
        private String lid;
        private String name;
        private String documentation;
        private String structure;
        private String dataType;


    }

}
