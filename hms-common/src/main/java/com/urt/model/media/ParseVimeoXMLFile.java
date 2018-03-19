package com.urt.model.media;
/*
 * ParseXMLFile.java
 *
 * Created on 4. listopad 2003, 10:14
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.churchgroup.util.object.ObjectFunctions;

/** This class represents short example how to parse XML file,
 * get XML nodes values and its values.<br><br>
 * It implements method to save XML document to XML file too
 * @author Martin Glogar
 */
public class ParseVimeoXMLFile {
    
	private static final Log log = LogFactory.getLog(ParseVimeoXMLFile.class);
    private DocumentBuilderFactory dbf ;
	private DocumentBuilder docBuilder;
	private Document document;
	private Class mediaClass;
	private VimeoMedia media;
	private List vimeoMeidaList;
	private Boolean itemFound=Boolean.FALSE;
	private Class[] stringParamType = {String.class};
	//private Class[] longParamType = {Long.class};

    
    public ParseVimeoXMLFile() {
		try {
			this.dbf = DocumentBuilderFactory.newInstance();
			this.docBuilder = dbf.newDocumentBuilder();
			media = new VimeoMedia();
			mediaClass=media.getClass();
			    //Class.forName("com.urt.model.media.LightCastMedia");
            //log.debug(mediaClass.getFields().toString());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
	}

    
    /** Returns element value
     * @param elem element (it is XML tag)
     * @return Element value otherwise empty String
     */
    public final static String getElementValue( Node elem ) {
        Node kid;
        if( elem != null && elem.hasChildNodes() ){
           // if (elem.hasChildNodes() ){
                for( kid = elem.getFirstChild(); kid != null; kid = kid.getNextSibling() ){
                    if( kid.getNodeType() == Node.TEXT_NODE  ){
                        return kid.getNodeValue();
                    }
                }
          //  }
        }
        return "";
    }
    
   /* private String getIndentSpaces(int indent) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < indent; i++) {
            buffer.append(" ");
        }
        return buffer.toString();
    }*/
    
    /** Writes node and all child nodes into System.out
     * @param node XML node from from XML tree wrom which will output statement start
     * @param indent number of spaces used to indent output
     */
    public void createVimeoVedioList(Node node,int indent) {
        // get element name
        String nodeName = node.getNodeName();
        // get element value
        String nodeValue = getElementValue(node);
        // get attributes of element
        NamedNodeMap attributes = node.getAttributes();
        //log.debug(getIndentSpaces(indent) + "NodeName: " + nodeName + ", NodeValue: " + nodeValue);
        if(getItemFound())
        {
            setFieldValue(nodeName,nodeValue);
            if("tags".equalsIgnoreCase(nodeName))
            {
                setItemFound(false);
                //  log.debug("Media Object : " + media.toString());
                getVimeoMeidaList().add(media);
                media = new VimeoMedia();
            }
        }
        if("video".equalsIgnoreCase(nodeName))
        {
            setItemFound(true);            
        }
        
        for (int i = 0; i < attributes.getLength(); i++) {
            //Node attribute = attributes.item(i);
            //log.debug(getIndentSpaces(indent + 2) + "AttributeName: " + attribute.getNodeName() + ", attributeValue: " + attribute.getNodeValue());
        }
        // write all child nodes recursively
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                createVimeoVedioList(child,indent + 2);
            }
        }
    }
    
    public void setFieldValue(String nodeName, String nodeValue)
    {
        try {
            String prop = Character.toUpperCase(nodeName.charAt(0)) +
            nodeName.substring(1);
            
            String mname = "set" + prop;
            /*if("id".equalsIgnoreCase(nodeName))
            {
                Object[] parameters= { new Long(nodeValue) };
                Method  method = mediaClass.getMethod(mname, longParamType);
            }
            else
            {*/
                Method  method = mediaClass.getMethod(mname, stringParamType);
                Object[] parameters= { new String(nodeValue) };
                method.invoke(media, parameters);
           // }
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
          

           // Field fld = mediaClass.getField(nodeName);
            //fld.set(media, nodeValue);
            
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void writeDocumentToOutput() {
        // write node and its child nodes into System.out
    	// get root node of xml tree structure
        //Node root = getDocument().getDocumentElement();
        log.debug("Statemend of XML document...");
       // writeDocumentToOutput(root,0);
        log.debug("... end of statement");
    }
    public void createVimeoVideoList() {
        Node root = getDocument().getDocumentElement();
        log.debug("Statemend of XML document...");
        createVimeoVedioList(root,0);
        log.debug("... end of statement");
    }
    
    /** Saves XML Document into XML file.
     * @param fileName XML file name
     * @param doc XML document to save
     * @return <B>true</B> if method success <B>false</B> otherwise
     */    
    public boolean saveXMLDocument(String fileName, Document doc) {
        log.debug("Saving XML file... " + fileName);
        // open output stream where XML Document will be saved
        File xmlOutputFile = new File(fileName);
        FileOutputStream fos;
        Transformer transformer;
        try {
            fos = new FileOutputStream(xmlOutputFile);
        }
        catch (FileNotFoundException e) {
            log.debug("Error occured: " + e.getMessage());
            return false;
        }
        // Use a Transformer for output
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer();
        }
        catch (TransformerConfigurationException e) {
            log.debug("Transformer configuration error: " + e.getMessage());
            return false;
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(fos);
        // transform source into result will do save
        try {
            transformer.transform(source, result);
        }
        catch (TransformerException e) {
            log.debug("Error transform: " + e.getMessage());
        }
        log.debug("XML file saved.");
        return true;
    }
    
    /** Parses XML file and returns XML document.
     * @param fileName XML file to parse
     * @return XML document or <B>null</B> if error occured
     */
    public Document parseFile(String fileName) {
        log.debug("Parsing XML file... " + fileName);
        DocumentBuilder docBuilder;
        Document doc = null;
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setIgnoringElementContentWhitespace(true);
        try {
            docBuilder = docBuilderFactory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            log.debug("Wrong parser configuration: " + e.getMessage());
            return null;
        }
        File sourceFile = new File(fileName);
        try {
            doc = docBuilder.parse(sourceFile);
            setDocument(doc);
        }
        catch (SAXException e) {
            log.debug("Wrong XML file structure: " + e.getMessage());
            return null;
        }
        catch (IOException e) {
            log.debug("Could not read source file: " + e.getMessage());
        }
        log.debug("XML file parsed");
        return doc; 
    }
    
    /** Parses XML file and returns XML document.
     * @param fileName XML file to parse
     * @return XML document or <B>null</B> if error occured
     */
    public Document parseFile(InputStream inputStream) {
        
        try {
            setDocument(docBuilder.parse(inputStream));
        }
        catch (SAXException e) {
            log.debug("Wrong XML file structure: " + e.getMessage());
            return null;
        }
        catch (IOException e) {
        	e.printStackTrace();
            log.debug("Could not read source file: " + e.getMessage());
        }
        log.debug("XML file parsed");
        return getDocument();
    }
    
    /** Starts XML parsing example
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ParseVimeoXMLFile();
    }


	public DocumentBuilderFactory getDbf() {
		return dbf;
	}


	public DocumentBuilder getDocBuilder() {
		return docBuilder;
	}


	public void setDocBuilder(DocumentBuilder docBuilder) {
		this.docBuilder = docBuilder;
	}


	public void setDbf(DocumentBuilderFactory dbf) {
		this.dbf = dbf;
	}


	public Document getDocument() {
		return document;
	}


	public void setDocument(Document document) {
		this.document = document;
	}


    public Boolean getItemFound() {
        return itemFound;
    }


    public void setItemFound(Boolean itemFound) {
        this.itemFound = itemFound;
    }


    public List getVimeoMeidaList() {
        if(ObjectFunctions.isNullOrEmpty(this.vimeoMeidaList))
        {
            this.vimeoMeidaList = new ArrayList<VimeoMedia>();
        }
        return vimeoMeidaList;
    }


    public void setVimeoMediaList(List vimeoMeidaList) {
        this.vimeoMeidaList = vimeoMeidaList;
    }
    
}

