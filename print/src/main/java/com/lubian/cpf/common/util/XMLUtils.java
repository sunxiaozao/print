package com.lubian.cpf.common.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Referenced classes of package ins.framework.utils:
//			NoOpEntityResolver, DataUtils

public final class XMLUtils {

	private XMLUtils() {
	}

	public static Document newDocument() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.newDocument();
	}

	public static Document parse(File file) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setEntityResolver(new NoOpEntityResolver());
		Document document = builder.parse(file);
		document.normalize();
		return document;
	}

	public static Document parse(InputStream is) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		builder.setEntityResolver(new NoOpEntityResolver());
		Document document = builder.parse(is);
		document.normalize();
		return document;
	}

	public static Document parse(String fileName) throws Exception {
		return parse(new File(fileName));
	}

	public static Node getSingleNodeByTag(Document document, String tagName) {
		NodeList nodeList = document.getElementsByTagName(tagName);
		int length = nodeList.getLength();
		if (length == 1)
			return nodeList.item(0);
		else
			return null;
	}

	public static String getNodeAttribute(Node currentNode, String attrName) {
		String value = "";
		if (currentNode.getAttributes().getNamedItem(attrName) != null)
			value = currentNode.getAttributes().getNamedItem(attrName)
					.getNodeValue();
		value = StringUtils.nullToEmpty(value);
		return value;
	}

	public static Node getChildNodeByTagName(Node currentNode, String tagName) {
		Node returnNode = null;
		NodeList nodeList = currentNode.getChildNodes();
		Node node = null;
		int i = 0;
		int n = nodeList.getLength();
		do {
			if (i >= n)
				break;
			node = nodeList.item(i);
			if (node.getNodeName().equals(tagName)) {
				returnNode = node;
				break;
			}
			i++;
		} while (true);
		return returnNode;
	}

	public static Node[] getChildNodesByTagName(Node currentNode, String tagName) {
		ArrayList nodes = new ArrayList();
		if (currentNode == null || !currentNode.hasChildNodes())
			return new Node[0];
		NodeList nodeList = currentNode.getChildNodes();
		Node node = null;
		int i = 0;
		for (int n = nodeList.getLength(); i < n; i++) {
			node = nodeList.item(i);
			if (node.getNodeName().equals(tagName))
				nodes.add(node);
		}

		Node tempNodes[] = new Node[nodes.size()];
		nodes.toArray(tempNodes);
		return tempNodes;
	}

	public static Node[] getChildElements(Node currentNode) {
		ArrayList nodes = new ArrayList();
		if (currentNode == null || !currentNode.hasChildNodes())
			return new Node[0];
		NodeList nodeList = currentNode.getChildNodes();
		Node node = null;
		int i = 0;
		for (int n = nodeList.getLength(); i < n; i++) {
			node = nodeList.item(i);
			if (node.getNodeType() == 1)
				nodes.add(node);
		}

		Node tempNodes[] = new Node[nodes.size()];
		nodes.toArray(tempNodes);
		return tempNodes;
	}

	public static String getChildNodeValue(Node currentNode, String nodeName) {
		String value = "";
		NodeList nodeList = currentNode.getChildNodes();
		Node node = null;
		int i = 0;
		int n = nodeList.getLength();
		do {
			if (i >= n)
				break;
			node = nodeList.item(i);
			if (node.getNodeName().equals(nodeName)) {
				if (node.getFirstChild() != null)
					value = StringUtils.nullToEmpty(node.getFirstChild()
							.getNodeValue());
				break;
			}
			i++;
		} while (true);
		return value;
	}

	public static void writeXMLFile(Document document, String fileName)
			throws Exception {
		DOMSource source = new DOMSource(document);
		writeXMLFile(source, fileName);
	}

	public static void writeXMLFile(DOMSource source, String fileName)
			throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult fileResult = new StreamResult(new File(fileName));
		transformer.transform(source, fileResult);
	}

	public static void writeXMLFile(Node node, String fileName)
			throws Exception {
		DOMSource source = new DOMSource(node);
		writeXMLFile(source, fileName);
	}

	public static Node getChildNodeByPath(Node currentNode, String path) {
		String tagName = path;
		int pos = path.indexOf('/');
		if (pos > -1) {
			tagName = path.substring(0, pos);
			path = path.substring(pos + 1);
			if ("".equals(tagName))
				return getChildNodeByPath(currentNode, path);
			if ("/".equals(tagName))
				return getChildNodeByPath(
						((Node) (currentNode.getOwnerDocument())), path);
			Node node = getChildNodeByTagName(currentNode, tagName);
			if (node == null)
				return null;
			else
				return getChildNodeByPath(node, path);
		} else {
			return getChildNodeByTagName(currentNode, tagName);
		}
	}
}
