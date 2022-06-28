/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printermanager;

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
    private final Map<String, PrinterService> printers;
    private final PropertiesFileManager pfm;

    private PrinterManager() {
        this.printers = new HashMap<>();
        this.pfm = new PropertiesFileManager("Impresoras");
        pfm.cargar();
        pfm.obtenerLista().forEach(o -> loadPrinter(o.toString()));
    }
    
    public static PrinterManager getInstance(){
        return manager != null? manager : new PrinterManager();
    }
    
    private PrinterService createService(String serviceName){
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
        PrinterService ps = printers.get(service);
        if (ps == null) {
            ps = this.createService(service);
        }
        return ps;
    }
    
    
    
}
