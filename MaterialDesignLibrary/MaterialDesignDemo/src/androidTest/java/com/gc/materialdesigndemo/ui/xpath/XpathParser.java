package com.gc.materialdesigndemo.ui.xpath;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.gc.materialdesigndemo.temp.AutomationCore;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;

public class XpathParser {

    public static UiSelector parseMe(String myXpath, File f) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = new FileInputStream(f.getAbsolutePath());
            Document doc = builder.parse(inputStream);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath
                    .compile(myXpath);
            NodeList nodes = (NodeList) expr.evaluate(doc,
                    XPathConstants.NODESET);
            Log.d("SIZER", String.valueOf(nodes.getLength()));
            String androidClass = nodes.item(0).getAttributes().getNamedItem("class").getNodeValue();
            String id = nodes.item(0).getAttributes().getNamedItem("resource-id").getNodeValue();
            String index = nodes.item(0).getAttributes().getNamedItem("index").getNodeValue();
            String desc = nodes.item(0).getAttributes().getNamedItem("content-desc").getChildNodes().item(0).getNodeValue();
            String text = nodes.item(0).getAttributes().getNamedItem("text").getNodeValue();
            return new UiSelector().className(androidClass).resourceId(id).index(Integer.parseInt(index)).description(desc).text(text);

        } catch (Exception e) {
            Log.d("IAMTHROWN",e.getMessage());
        }
        return null;
    }
//    private static void annotateNodes(Node node, HashMap<String, Integer> instances) {
//        NodeList children = node.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
//                visitNode(children.item(i), instances);
//                annotateNodes(children.item(i), instances);
//            }
//        }
//    }
//
//    // set the node's tag name to the same as it's android class.
//    // also number all instances of each class with an "instance" number. It increments for each class separately.
//    // this allows use to use class and instance to identify a node.
//    // we also take this chance to clean class names that might have dollar signs in them (and other odd characters)
//    private static void visitNode(Node node, HashMap<String, Integer> instances) {
//
//        Document doc = node.getOwnerDocument();
//        NamedNodeMap attributes = node.getAttributes();
//
//        String androidClass;
//        try {
//            androidClass = attributes.getNamedItem("class").getNodeValue();
//        } catch (Exception e) {
//            return;
//        }
//
//        androidClass = cleanTagName(androidClass);
//
//        if (!instances.containsKey(androidClass)) {
//            instances.put(androidClass, 0);
//        }
//        Integer instance = instances.get(androidClass);
//
//        Node attrNode = doc.createAttribute("instance");
//        attrNode.setNodeValue(instance.toString());
//        attributes.setNamedItem(attrNode);
//
//        doc.renameNode(node, node.getNamespaceURI(), androidClass);
//
//        instances.put(androidClass, instance + 1);
//    }
//
//    private static String cleanTagName(String name) {
//        name = name.replaceAll("[$@#&]", ".");
//        return name.replaceAll("\\s", "");
//    }

}