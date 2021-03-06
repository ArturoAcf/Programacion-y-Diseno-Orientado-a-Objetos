/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import civitas.civitasJuego;
import civitas.Jugador;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author arturo
 */
public class CivitasView extends javax.swing.JFrame {
    civitasJuego juego;
    JugadorPanel jugadorPanel;
    CasillaPanel casillaPanel;
    
    GestionarDialog gestionarD=new GestionarDialog(this);void setCivitasJuego(civitasJuego civitas){
        this.juego=civitas;
        setVisible(true);
    }
    /**
     * Creates new form CivitasView
     */
    public CivitasView() {
        initComponents();
        jugadorPanel=new JugadorPanel();
        casillaPanel=new CasillaPanel();
        contenedorVistaJugador.add(jugadorPanel);
        contenedorVistaCasilla.add(casillaPanel);
        repaint();
        revalidate();
    }
    
    void actualizarVista(){
        jugadorPanel.setJugador(juego.getJugadorActual());
        jugadorPanel.setVisible(true);
        
        casillaPanel.setCasilla(juego.getCasillaActual());
        casillaPanel.setVisible(true);
        
        Ranking.setVisible(false);
        AreaRanking.setVisible(false);
        
        if(juego.finalDelJuego()==true){
             ArrayList<Jugador> rank=juego.ranking();            
             String r="";  
             
             for(int i=0; i<rank.size(); i++){                
                 r=r+rank.get(i).toString()+"\n";            
             }    
             
             AreaRanking.setText(r);     
             AreaRanking.setVisible(true);
             Ranking.setVisible(true);            
        }
        
        repaint();
        revalidate();
    }
    
    void mostrarSiguienteOperacion(OperacionesJuego operacion){
        ProximaOperacion.setText("Siguiente operaci??n: ");
        jTextField1.setText(operacion.toString());
        jTextField1.setVisible(true);
        actualizarVista();
    }
    
    void mostrarEventos(){
        DiarioDialog diarioD=new DiarioDialog(this);
    }
    
    Respuestas Comprar(){ 
        int opcion=JOptionPane.showConfirmDialog(null,"??Quieres comprar la calle actual?","Compra",JOptionPane.YES_NO_OPTION);
        return Respuestas.values()[opcion];
    }
    
    int getGestion(){
        return gestionarD.getGestion();
    }
    
    int getPropiedad(){
        return gestionarD.getPropiedad();
    }
    
    public void gestionar(){
        gestionarD.gestionar(juego.getJugadorActual());
        gestionarD.pack();
        gestionarD.repaint();
        gestionarD.revalidate();
        gestionarD.setVisible(true);
    }
    
    SalidasCarcel salirCarcel(){
        String[] opciones={"Pagando","Tirando"};
        
        int res=JOptionPane.showOptionDialog(null,"??C??mo quieres salir de la c??rcel?","Salir de la c??rcel",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        
        return (SalidasCarcel.values()[res]);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        contenedorVistaJugador = new javax.swing.JPanel();
        contenedorVistaCasilla = new javax.swing.JPanel();
        ProximaOperacion = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Ranking = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreaRanking = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CIVITAS");
        jLabel1.setEnabled(false);

        ProximaOperacion.setText("ProximaOperacion");
        ProximaOperacion.setEnabled(false);

        jTextField1.setText("jTextField1");
        jTextField1.setEnabled(false);

        Ranking.setText("Ranking");
        Ranking.setEnabled(false);

        AreaRanking.setColumns(20);
        AreaRanking.setRows(5);
        AreaRanking.setEnabled(false);
        jScrollPane1.setViewportView(AreaRanking);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                .addGap(136, 136, 136))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ProximaOperacion)
                        .addGap(33, 33, 33)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contenedorVistaCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Ranking))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProximaOperacion))
                .addGap(15, 15, 15)
                .addComponent(contenedorVistaCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addComponent(Ranking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CivitasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaRanking;
    private javax.swing.JLabel ProximaOperacion;
    private javax.swing.JLabel Ranking;
    private javax.swing.JPanel contenedorVistaCasilla;
    private javax.swing.JPanel contenedorVistaJugador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
