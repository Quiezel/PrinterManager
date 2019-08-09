/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printerservice;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import propertiesfilemanager.PropertiesFileManager;

/**
 *
 * @author CREEDurango
 */
public class PrinterService {
    private String serviceName;
    private PropertiesFileManager pfm;
    private String impresora;

    public PrinterService(String serviceName, PropertiesFileManager pfm) {
        this.serviceName = serviceName;
        this.pfm = pfm;
        if (pfm.obtenerPropiedad(serviceName, null) != null) {
            impresora = pfm.obtenerPropiedad(serviceName, null);
        }
    }

    public String getImpresora() {
        return impresora;
    }

    public void setImpresora(String impresora) {
        this.impresora = impresora;
    }
    
    public void configurarImpresora(String impresora){
        setImpresora(impresora);
        pfm.definirConfiguracion(serviceName, impresora);
        pfm.Guardar("");
    }

    public static List<String> getPrinters() {
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;//
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printServices[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        List<String> printerList;
        printerList = new ArrayList();
        for (PrintService printerService : printServices) {
            printerList.add(printerService.getName());
        }
        return printerList;
    }

//        public void printString(String archivo) {
//        FileInputStream inputStream; //
//        try {
//            inputStream = new FileInputStream(archivo);//LOCALIZA EL ARCHIVO EN FORMATO TXT// 
//            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;// FORMATO DE DOCUMENTO
//            DocFlavor arreglo;// DE FORMATO de arreglo
//            arreglo = DocFlavor.BYTE_ARRAY.AUTOSENSE;
//            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
//            // CONFIGURACION DE ATRIBUTOS: DE SOLICITUD DE RESPUESTA DE IMPRESORA
////        attributeSet.add(MediaSizeName.NA_LETTER);
//            PrintService defaultPrintService
//                    = PrintServiceLookup.lookupDefaultPrintService();
//            //BUSCA LA IMPRESORA POR DEFECTO, SERVICIO DE IMPRESION POR DEFECTO 
//
//            if (defaultPrintService != null) {//SI EXISTE SERVICIO DE IMPRESORA POR DEFAULT
//                Doc document = new SimpleDoc(inputStream, docFormat, null);//CREA UN DOCUMENTO SIMPLE CON FORMATO DE ENTRADA DE STREAM, 
//                //y el contenido del archivo "impresion.txt" QUE SERÁ IMPRESO DESPUES
//                DocPrintJob printJob = defaultPrintService.createPrintJob();
//                try {
//                    printJob.print(document, null); //IMPRIMe UN DOCUMENTO en EL TRABAJO DE IMPRESIÓN (impresora porfault) con atributos
//                } catch (PrintException ex) {
//                    Logger.getLogger(PrinterService.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                inputStream.close();
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(PrinterService.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(PrinterService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public void printString(String text) {
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob job = service.createPrintJob();
        try {
            byte[] bytes;
            bytes = text.getBytes("CP437");
            Doc doc = new SimpleDoc(bytes, flavor, null);
            job.print(doc, null);
        } catch (PrintException | UnsupportedEncodingException ex) {
            Logger.getLogger(PrinterService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param texto
     * @param cutP
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void imprimir(String texto, byte[] cutP) throws FileNotFoundException, IOException {
        printString(texto);
        printBytes("EPSON TM-T20II Receipt", cutP);
        FileInputStream inputStream = new FileInputStream(texto);
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        // CONFIGURACION DE ATRIBUTOS: DE SOLICITUD DE RESPUESTA DE IMPRESORA
//        boolean add = attributeSet.add(MediaSizeName.NA_LETTER);
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultPrintService != null) {
            try {
                Doc document = new SimpleDoc(inputStream, docFormat, null);
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                printJob.print(document, null);
                byte[] bytes;
//                bytes = text.getBytes("CP437");
//                Doc doc = new SimpleDoc(bytes, arreglo, null);
                DocPrintJob job = defaultPrintService.createPrintJob();
//                printJob.print(doc, null);

                inputStream.close();
            } catch (PrintException ex) {
            }
        } else {
            System.err.println("No existen impresoras instaladas");
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(PrinterService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void printInputStreamPostScript(String printerName, String text) throws FileNotFoundException {
        DocFlavor flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT;//
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        //aset.add(MediaSizeName.NA_LETTER);
        PrintService pService[]
                = PrintServiceLookup.lookupPrintServices(flavor, aset);
        if (pService.length > 0) {
//                PrintService service = findPrintService(printerName, printService);
            DocPrintJob pj = pService[0].createPrintJob();
            try {
                FileInputStream fis = new FileInputStream("test.ps");
                Doc doc = new SimpleDoc(fis, flavor, null);
                // important for umlaut chars
                //byte[] bytes;
                //bytes = text.getBytes("CP437");
                //Doc doc = new SimpleDoc(bytes, flavor, null);	
                pj.print(doc, aset);
            } catch (PrintException e) {
                // TODO Auto-generated catch block
            }
        }
    }

    /**
     *
     * @param g
     * @param pf
     * @param page
     * @return
     * @throws PrinterException
     */
    public int print(Graphics g, PageFormat pf, int page)
            throws PrinterException {
        if (page > 0) {
            /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        /* Now we perform our rendering */

        g.setFont(new Font("Roman", 0, 8));
        g.drawString("Hello world !", 0, 10);

        return PAGE_EXISTS;
    }

    public void printBytes(String printerName, byte[] bytes) {
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

        PrintService printService[] = PrintServiceLookup.lookupPrintServices(
                flavor, pras);
        PrintService printer = EncuentraPrintService(printerName, printService);
        DocPrintJob job = printer.createPrintJob();
        try {

            Doc doc = new SimpleDoc(bytes, flavor, null);
            //Crea un documento con la información especificada y el formato 
            job.print(doc, pras);
            //Imprime el documento creado

        } catch (PrintException e) {
        }
    }

    //encuentra la impresora especificada (printerName) dentro de los servicios de impresión capaces de imprimir el archivo
    //Parametros: Nombre de la impresora, Una matriz con el nombre de la impresoras buscada
    private PrintService EncuentraPrintService(String printerName, PrintService[] servicios) {

        for (PrintService service : servicios) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                return service;
            }
        }

        return null;
    }
}
