package XMLDomParser;
import java.io.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.validation.*;
import javax.xml.transform.dom.*;


import org.w3c.dom.Document;

public class DomParserSchema {
	public static void main(String args[]) {
		//strings to specify the location of each file
		String schemaName = "./nfl.xsd";
		String xmlName = "./nfl.xml";
		
		 Schema schema = loadSchema(schemaName);
		 Document document = parseXmlDom(xmlName);

		  validateXml(schema, document);
		
		
	}

	private static void validateXml(Schema schema, Document document) {
		//Get Validator from Schema
		 try {
			   Validator validator = schema.newValidator();
			   System.out.println("Validator Class: " + validator.getClass().getName());

			   // validating the document against the schema
			   validator.validate(new DOMSource(document));
			   System.out.println("Validation passed.");

			  } catch (Exception e) {
			   // catching all validation exceptions
			   System.out.println(e.toString());
			  }
	}
	
	private static Schema loadSchema(String schemaName) {
		Schema schema = null;
		try {
			   //// 1. Lookup a factory for the W3C XML Schema language
			   String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			   SchemaFactory factory = SchemaFactory.newInstance(language);

			   /*
			    * 2. Compile the schema. Here the schema is loaded from a java.io.File, but
			    * you could use a java.net.URL or a javax.xml.transform.Source instead.
			    */
			   schema = factory.newSchema(new File(schemaName));
			  } 
		catch(Exception e){
			 System.out.println(e.toString());
		}
		return schema;
	}


	private static Document parseXmlDom(String xmlName) {
		Document document = null;
		try {
			   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			   DocumentBuilder builder = factory.newDocumentBuilder();
			   document = builder.parse(new File(xmlName));
		   } catch (Exception e) {
			   System.out.println(e.toString());
			  }
		return document;
	}

	
}
