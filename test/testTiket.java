
import java.awt.Font;
import printermanager.PrinterManager;
import printermanager.Ticket;
import printermanager.Ticket.Alinear;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CREEDurango
 */
public class testTiket extends javax.swing.JFrame {

    private final String IMPRESORA = "ticket";
    private final PrinterManager pm = PrinterManager.getInstance();
    private Ticket ticket;

    /**
     * Creates new form testTiket
     */
    public testTiket() {
        initComponents();
        ticket = new Ticket();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        radioPlain = new javax.swing.JRadioButton();
        radioBold = new javax.swing.JRadioButton();
        radioItalic = new javax.swing.JRadioButton();
        textSize = new javax.swing.JTextField();
        textTexto = new javax.swing.JTextField();
        botonImprimir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        botonAgregarLinea = new javax.swing.JButton();
        rbd = new javax.swing.JRadioButton();
        rbc = new javax.swing.JRadioButton();
        rbi = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Prueba Impresion Ticket");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        buttonGroup1.add(radioPlain);
        radioPlain.setSelected(true);
        radioPlain.setText("Plain");

        buttonGroup1.add(radioBold);
        radioBold.setText("Bold");

        buttonGroup1.add(radioItalic);
        radioItalic.setText("Italic");

        textSize.setText("10");
        textSize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textSizeKeyTyped(evt);
            }
        });

        textTexto.setToolTipText("");

        botonImprimir.setText("Imprimir");
        botonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImprimirActionPerformed(evt);
            }
        });

        jLabel2.setText("Texto");

        jLabel3.setText("Tamaño");

        botonAgregarLinea.setText("Agregar Linea");
        botonAgregarLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarLineaActionPerformed(evt);
            }
        });

        buttonGroup2.add(rbd);
        rbd.setSelected(true);
        rbd.setText("Derecha");

        buttonGroup2.add(rbc);
        rbc.setText("Centrar");

        buttonGroup2.add(rbi);
        rbi.setText("Izquierda");

        jLabel4.setText("Alinear");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textTexto, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(botonAgregarLinea, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(botonImprimir, javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textSize, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 190, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbi))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radioPlain)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioBold)
                                .addGap(9, 9, 9)
                                .addComponent(radioItalic))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioPlain)
                    .addComponent(radioBold)
                    .addComponent(radioItalic))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbd)
                    .addComponent(rbc)
                    .addComponent(rbi))
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAgregarLinea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonImprimir)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textSizeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textSizeKeyTyped
        try {
            Integer.parseInt(textSize.getText());
        } catch (NumberFormatException e) {
            if (textSize.getText().isEmpty()) {
                textSize.setText("0");
            }
            evt.consume();
        }
    }//GEN-LAST:event_textSizeKeyTyped

    private void botonAgregarLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarLineaActionPerformed
        int size = Integer.parseInt(textSize.getText());
        Font fuente = new Font(Font.SANS_SERIF, Font.PLAIN, size);
        if (radioBold.isSelected()) {
            fuente = fuente.deriveFont(Font.BOLD);
        } else if (radioItalic.isSelected()) {
            fuente = fuente.deriveFont(Font.ITALIC);
        }
        ticket.addLinea(textTexto.getText() + "\n", fuente,alineamiento());
        textTexto.setText("");
        textTexto.requestFocus();
    }//GEN-LAST:event_botonAgregarLineaActionPerformed

    private void botonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImprimirActionPerformed
        pm.getPrinterService(IMPRESORA).printTicket(ticket);
        ticket = new Ticket();
    }//GEN-LAST:event_botonImprimirActionPerformed

    private Alinear alineamiento(){
        return (rbc.isSelected()? Alinear.CENTRAR : (rbi.isSelected()? Alinear.IZQUIERDA : Alinear.DERECHA));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new testTiket().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregarLinea;
    private javax.swing.JButton botonImprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton radioBold;
    private javax.swing.JRadioButton radioItalic;
    private javax.swing.JRadioButton radioPlain;
    private javax.swing.JRadioButton rbc;
    private javax.swing.JRadioButton rbd;
    private javax.swing.JRadioButton rbi;
    private javax.swing.JTextField textSize;
    private javax.swing.JTextField textTexto;
    // End of variables declaration//GEN-END:variables
}
