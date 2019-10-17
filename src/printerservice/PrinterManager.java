/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printerservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import propertiesfilemanager.PropertiesFileManager;

/**
 *
 * @author CREEDurango
 */
public class PrinterManager {
    private static PrinterManager manager;
    private Map<String, PrinterService> printers;
    private PropertiesFileManager pfm;

    private PrinterManager() {
        this.printers = new HashMap<>();
        this.pfm = new PropertiesFileManager("Impresoras");
        pfm.cargar();
        pfm.obtenerLista().forEach(o -> loadPrinter(o.toString()));
    }
    
    public static PrinterManager getInstance(){
        return manager != null? manager : new PrinterManager();
    }
    
    public PrinterService createService(String serviceName){
        PrinterService ps = new PrinterService(serviceName, pfm);
        printers.put(serviceName, ps);
        return ps;
    }
    
    public void loadPrinter(String serviceName){
        PrinterService ps = new PrinterService(serviceName, pfm);
        ps.setImpresora(pfm.obtenerPropiedad(serviceName, ""));
        printers.put(serviceName, ps);
    }
    
    public List<String> getPrinterServices(){
        return new ArrayList<>(printers.keySet());
    }
    
    public PrinterService getPrinterService(String service){
        return printers.get(service);
    }
    
    
    
}
