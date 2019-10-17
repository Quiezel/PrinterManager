
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import printerservice.PrinterManager;
import printerservice.PrinterService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CREEDurango
 */
public class TestinGround {

    private final String IMPRESORA = "ticket";
    private final PrinterManager pm = PrinterManager.getInstance();
    private final Scanner reader = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODOcode application logic here
        TestinGround tg;
        tg = new TestinGround();
        while (tg.correr()) {
            //que rayos
        }
    }

    public boolean correr() {
        System.out.println("Selecciona accion");
        System.out.println("0 Imprimir texto");
        System.out.println("1 Seleccionar impresora");
        System.out.println("x Salir");
        System.out.print("> ");
        String input = reader.nextLine().trim();
        System.out.println("");
        switch (input) {
            case "0":
                imprimir();
                return true;
            case "1":
                seleccionarImpresora();
                return true;
            case "x":
                System.out.println("Cerrando...");
                return false;
            default:
                System.out.println("Opcion invalida");
                return true;
        }
    }

    private void imprimir() {
        System.out.println("Texto a imprimir:");
        String input = reader.nextLine();
        List<Byte> ticket = new ArrayList<>();
        forByte(ticket, "Prueba uno\n");
        forByte(ticket, "Esta es una prueba\n");
        forByte(ticket, input + " \n\n\n\n");
        forByte(ticket, PrinterService.cutP);
        pm.getPrinterService(IMPRESORA).printBytes(listToByte(ticket));
    }

    private void seleccionarImpresora() {
        System.out.println("Seleccionar:");
        System.out.println("Selecciona Impresora");
        List<String> impresoras = PrinterService.getPrinters();
        for (int i = 0; i < impresoras.size(); i++) {
            System.out.println(i + " " + impresoras.get(i));
        }
        System.out.println("x Cancelar");
        System.out.print("> ");
        String input = reader.nextLine();
        if (input.equals("x")) {
            return;
        }
        int indexImpresora = Integer.parseInt(input);
        if (pm.getPrinterService(IMPRESORA) == null) {
            System.out.println("creando");
            pm.createService(IMPRESORA);
        }
        PrinterService ps = pm.getPrinterService(IMPRESORA);
        ps.configurarImpresora(impresoras.get(indexImpresora));
    }

    private byte[] listToByte(List<Byte> ticket) {
        byte[] bytes = new byte[ticket.size()];
        for (int i = 0; i < ticket.size(); i++) {
            bytes[i] = ticket.get(i);
        }
        return bytes;
    }

    private void forByte(List<Byte> ticket, String texto) {
        byte[] bytes;
        try {
            bytes = texto.getBytes("CP437");
            for (int i = 0; i < bytes.length; i++) {
                ticket.add(bytes[i]);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TestinGround.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void forByte(List<Byte> ticket, byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            ticket.add(bytes[i]);
        }
    }
    
    public void test(){
        PrinterService ps = pm.getPrinterService(IMPRESORA);
    }
}
