package com.jbrown.core.util;

import org.apache.xerces.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.*;
import org.xml.sax.*;

public final class BrownParser {
	private DOMParser _parser;

	/**
	 * Constructor
	 * @param xml
	 */
	public BrownParser(String xml) {
		_parser = new DOMParser();
		try {
			// Since the argument is a XML string - convert it to reader
			InputSource xmlSource = new InputSource(new StringReader(xml));

			// Read the entire xml into memory
			_parser.parse(xmlSource);
		} catch (SAXException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public Document getDocument() {
		return _parser.getDocument();
	}

	/**
	 * Given the tag name this method returns the number of tag occurrences 
	 * @param tagName
	 * @return
	 */
	public int getElementCount(String tagName) {
		Document doc = _parser.getDocument();
		NodeList list = doc.getDocumentElement().getElementsByTagName(tagName);
		return list.getLength();
	}

	/**
	 * given an tag and index return an Element at a given index
	 * @param tagName
	 * @param index
	 * @return
	 */
	public Element getElement(String tagName, int index) {
		Document doc = _parser.getDocument();
		NodeList list = doc.getDocumentElement().getElementsByTagName(tagName);
		return (Element) list.item(index);
	}

	/**
	 * Given an element, it gets an element specified by the tagName. Then it
	 * traverses that element node to get its value.
	 * 
	 * Step 1. get Element of name tagName from e Step 2. cast element to Node
	 * and then traverse it for its non-whitespace
	 * 
	 * @param start
	 *            as the top Element
	 * @param tagName
	 *            for a tag name
	 * @param index
	 *            the element index position
	 * @return string the value of a Node
	 */
	public static String getElementValue(Element start, String tagName,
			int index) {
		String result = "";
		try {
			// get nodes list of a tag name from a start Element
			NodeList elements = start.getElementsByTagName(tagName);

			Node node = elements.item(index);
			if (node == null) {
				// make a note that call is for non-existing tag
				// System.out.println( "Warning: "+tagName+" does not exist." );
			} else {
				// this below nodes are text nodes
				NodeList nodes = node.getChildNodes();

				// find a value whose value is non-whitespace
				String s;
				for (int i = 0; i < nodes.getLength(); i++) {
					s = ((Node) nodes.item(i)).getNodeValue().trim();
					if (s.equals("") || s.equals("\r")) {
						continue;
					} else {
						result = s;
						break;
					}
				}
			}
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * display all nodes (including empty one and attributes)
	 * @param n
	 * @param parents
	 */
	public static void displayNodes(Node n, String parents) {
		String nodeName = parents;
		String value = "";
		switch (n.getNodeType()) {
		case Node.ATTRIBUTE_NODE:
		case Node.ELEMENT_NODE:
			nodeName = (parents.length() == 0) ? n.getNodeName() : parents
					+ "." + n.getNodeName();

			if (n.hasAttributes()) {
				// Handle attributes -attributes are unique by definition
				NamedNodeMap nodeMap = n.getAttributes();
				for (int x = 0; x < nodeMap.getLength(); x++) {
					Node a = nodeMap.item(x);
					displayNodes(a, nodeName);
				}
			}

			// Handle children nodes
			NodeList list = n.getChildNodes();
			if (list.getLength() == 0) {
				// That is a node with no value (no text node)
				System.out.println(nodeName + "=" + value);
			} else {
				for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					displayNodes(node, nodeName);
					// Need to check for syblings
				}
			}
			break;

		case Node.TEXT_NODE:
			String s = n.getNodeValue().trim();
			if (s.indexOf("\n") < 0 && s.length() > 0) {
				value = s;
			}
			System.out.println(nodeName + "=" + value);
			break;
		}
	}
	
	/**
	 * Sample xml = <xyz><abc id="1" name="1"></abc></xyz> Uses - If we call
	 * this methid with param ("abc") then this method will return all internal
	 * attribute of node <abc>.
	 * 
	 * @param doc
	 * @param tagName
	 * @return
	 */
	public List<Map<String, String>> getAttributes(String tagName) {
		Document doc = _parser.getDocument();
		NodeList nList = doc.getElementsByTagName(tagName);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Map<String, String> map = new HashMap<String, String>();
				Element eElement = (Element) nNode;

				// Element eElement0 = (Element)
				// eElement.getElementsByTagName("photo");

				for (int i = 0; i < eElement.getAttributes().getLength(); i++) {
					map.put(eElement.getAttributes().item(i).getNodeName(),
							eElement.getAttributes().item(i).getNodeValue());
				}

				list.add(map);
			}
		}

		return list;
	}

}
