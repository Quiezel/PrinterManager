/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printerservice;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CREEDurango
 */
public class Ticket implements Printable {
    public static enum Alinear{
        DERECHA(0), IZQUIERDA(1), CENTRAR(2);
        
        private final int valor;

        private Alinear(int alineamiento) {
            this.valor = alineamiento;
        }
    }

    private List<Linea> lineas;

    public Ticket() {
        lineas = new ArrayList<>();
    }

    public void addLinea(String texto) {
        lineas.add(new Linea(texto));
    }

    public void addLinea(String texto, Font fuente) {
        lineas.add(new Linea(texto, fuente));
    }

    public void addLinea(String texto, Font fuente, Alinear alinear) {
        lineas.add(new Linea(texto, fuente, alinear));
    }

    public PageFormat getPageFormat() {
        PageFormat format = new PageFormat();
        Paper paper = new Paper();

        double paperWidth = 3;//3.25
        double paperHeight = 11.69;//11.69
        double leftMargin = 0.10;
        double rightMargin = 0.05;
        double topMargin = 0;
        double bottomMargin = 0.01;
        int resolucion = 72;
        

        paper.setSize(paperWidth * resolucion, paperHeight * resolucion);
        paper.setImageableArea(leftMargin * resolucion, topMargin * resolucion,
                (paperWidth - leftMargin - rightMargin) * resolucion,
                (paperHeight - topMargin - bottomMargin) * resolucion);

        format.setPaper(paper);
        return format;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        Graphics2D g2d = (Graphics2D) graphics;
        if (pageIndex != 0) {
            return Printable.NO_SUCH_PAGE;
        }
        int posY = 0;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        List<Linea> wraped = wrapText(lineas, g2d);
        for (int i = 0; i < wraped.size(); i++) {
            Linea linea = wraped.get(i);
            Font fuente = linea.getFont();
            g2d.setFont(fuente);
            posY += getStringHeight(g2d, linea);
            g2d.drawString(linea.getText(), getAlineamiento(linea.alinear.valor, getStringWidth(g2d, linea)), posY);
        }
        return PAGE_EXISTS;
    }
    
    private int getStringHeight(Graphics2D g2d, Linea l){
        FontMetrics fm = g2d.getFontMetrics(l.getFont());
        return fm.getAscent() - fm.getDescent() + fm.getLeading() + 5;// El 5 es la distancia minima entre lineas
    }
    
    private int getStringWidth(Graphics2D g2d, Linea l){
        FontMetrics fm = g2d.getFontMetrics(l.getFont());
        return fm.stringWidth(l.getText());
    }
    
    private int getAlineamiento(int alinear , int stringWidth){
        int posX = 0;
        if (alinear > 0) {
            posX = (int) getPageFormat().getImageableWidth() - stringWidth;
        }
        if (alinear > 1) {
            posX = posX/2;
        }
        return posX;
    }
    
    private List<Linea> wrapText(List<Linea> lineas , Graphics2D g2d){
        List<Linea> wraped = new ArrayList<>();
        double width = getPageFormat().getImageableWidth();
        for (int i = 0; i < lineas.size(); i++) {
            Linea linea = lineas.get(i);
            Font fuente = linea.getFont();
            g2d.setFont(fuente);
            if (width < getStringWidth(g2d, linea)) {
                StringUtils.wrap(linea.getText(), g2d.getFontMetrics(linea.getFont()), (int)width)
                        .forEach(text -> {
                            wraped.add(linea.deriveLine(text));
                        });
            }else{
                wraped.add(linea);
            }
        }
        return wraped;
    }
    
    private class Linea {

        private String texto;
        private Font fuente;
        private Alinear alinear;

        public Linea(String texto) {
            this(texto, new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        }

        public Linea(String texto, Font fuente) {
            this(texto, fuente, Alinear.DERECHA);
        }

        public Linea(String texto, Font fuente, Alinear alinear) {
            this.texto = texto;
            this.fuente = fuente;
            this.alinear = alinear;
        }

        private Font getFont() {
            return fuente;
        }

        private String getText() {
            return texto;
        }

        public Alinear getAlinear() {
            return alinear;
        }
        
        public Linea deriveLine(String texto){
            return new Linea(texto, fuente, alinear);
        }

        @Override
        public String toString() {
            return "Linea{" + "texto=" + texto + ", fuente=" + fuente + ", alinear=" + alinear + '}';
        }
    }

}
