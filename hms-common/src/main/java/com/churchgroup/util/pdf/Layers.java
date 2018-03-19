package com.churchgroup.util.pdf;


import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Explains the concept concerning PdfContentByte layers.
 */
public class Layers {
	private static final Log log = LogFactory.getLog(Layers.class);
    private PdfWriter pdfWriter;
    private Document document;
    private Paragraph paragraph;
    
    /**
     * Draws different things into different layers.
     * @param args no arguments needed
     */
    public Layers()
    {
        
        log.debug("Layers");
        
        // step 1: creation of a document-object
        Document document = new Document();
        
        try {
            
            // step 2: creation of the writer
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("layers.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4:
            
            // high level
            Paragraph p = new Paragraph();
            for (int i = 0; i < 100; i++) p.add(new Chunk("Blah blah blah blah blah. "));
            document.add(p);
           // Image img = Image.getInstance("hitchcock.png");
           // img.setAbsolutePosition(100, 500);
            //document.add(img);
            
            // low level
            PdfContentByte cb = writer.getDirectContent();
            PdfContentByte cbu = writer.getDirectContentUnder();
            cb.setRGBColorFill(0xFF, 0xFF, 0xFF);
            cb.circle(250.0f, 500.0f, 50.0f);
          //  cb.fill();
            cbu.setRGBColorFill(0xFF, 0x00, 0x00);
            cbu.circle(250.0f, 500.0f, 100.0f);
            cbu.fill();
            
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        
        // step 5: we close the document
        document.close();
    }

    public PdfWriter getPdfWriter() {
        return pdfWriter;
    }

    public void setPdfWriter(PdfWriter pdfWriter) {
        this.pdfWriter = pdfWriter;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }
}
