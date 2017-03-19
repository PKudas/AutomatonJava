
package Automaton_Projekt;

import static Automaton_Projekt.NewMain.MapToArray;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Represents GUI for Langton Ant automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class LangtonAntGUI extends javax.swing.JFrame {

    final int width = 200;
    final int height = 100;
    boolean wrap = true;
    CellStateFactory factory;
    CellNeighborhood neigh;
    CellState [][] Board;
    private boolean play;
    Image offScreenImage;
    Graphics offScreenGraphic;
    Automaton Automat; 
    Automaton NewAutomat; 
    public LangtonAntGUI() {
        int odp = JOptionPane.showConfirmDialog(null,"Wrapped ?","Topology",JOptionPane.YES_NO_OPTION);
        if (odp==JOptionPane.YES_OPTION)
            wrap = true;
        else if (odp == JOptionPane.NO_OPTION)
            wrap = false;
        Map<CellCoordinates, CellState> s = new HashMap<>();
        LangtonCell InitialState = new LangtonCell(BinaryState.DEAD);
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                s.put(new Coords2D(j,i), InitialState);                
            }
        }
        Board = new CellState[height][width];
        Board = MapToArray(s,height,width);
        factory = new GeneralStateFactory(s);
        neigh = new VonNeumanNeighborhood(height,width,wrap);
        Automat = new LangtonAnt(factory,neigh,width,height);
        initComponents();
        offScreenImage = createImage(jPanel1.getWidth(),jPanel1.getHeight());
        offScreenGraphic = offScreenImage.getGraphics();
        rePaint();
        Timer time = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if(play) {
                    NewAutomat = Automat.nextState();                    
                    Board = MapToArray(NewAutomat.getCells(),height,width);
                    rePaint();
                    Automat = NewAutomat;
                }
            }
        };
        time.scheduleAtFixedRate(task, 0, 2);
        rePaint();
    }
    private void rePaint() {
        offScreenGraphic.setColor(jPanel1.getBackground());
        offScreenGraphic.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(((LangtonCell)Board[i][j]).cellState == BinaryState.DEAD) {
                    offScreenGraphic.setColor(Color.WHITE);
                    int x = j * jPanel1.getWidth() / width;
                    int y = i *  jPanel1.getHeight() / height ;
                    offScreenGraphic.drawRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                    offScreenGraphic.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
                if(((LangtonCell)Board[i][j]).cellState == BinaryState.ALIVE) {
                    offScreenGraphic.setColor(Color.BLACK);
                    int x = j * jPanel1.getWidth() / width;
                    int y = i *  jPanel1.getHeight() / height ;
                    offScreenGraphic.drawRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                    offScreenGraphic.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
                if(!((LangtonCell)Board[i][j]).antState.isEmpty()) {
                    offScreenGraphic.setColor(Color.RED);
                    int x = j * jPanel1.getWidth() / width;
                    int y = i *  jPanel1.getHeight() / height ;
                    offScreenGraphic.drawRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                    offScreenGraphic.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
            }
        }
        jPanel1.getGraphics().drawImage(offScreenImage, 0, 0, jPanel1);
    }
    /**
     * Returns map of the Cell coordinates and states for the given structure 
     * @param str given structure
     * @param coords Coordinates of where to put given structure
     * @return map of the Cell coordinates and states for the given structure
     */
    private Map<CellCoordinates,CellState> GetMapForTheStructure(Structure str,CellCoordinates coords) {
        Map<CellCoordinates,CellState> map = new HashMap<>();
        CellState Lant = new LangtonCell(BinaryState.DEAD);
        ((LangtonCell) Lant).antState.add(AntState.WEST);
        if (str == Structure.MOUSE)
            map.put(new Coords2D(coords.getX(),coords.getY()), Lant);        
        return map;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 533, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 828, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        offScreenImage = createImage(jPanel1.getWidth(),jPanel1.getHeight());
        offScreenGraphic = offScreenImage.getGraphics();
        rePaint();
    }//GEN-LAST:event_jPanel1ComponentResized

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        int j = width * evt.getX() / jPanel1.getWidth();
        int i = height * evt.getY() / jPanel1.getHeight();
        Automat.insertStructure(GetMapForTheStructure(Structure.MOUSE,new Coords2D(j,i)),height ,width);
        Board = MapToArray(Automat.getFactory().getMapFromFactory(),height,width);
        rePaint();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        play = !play;
        if(play) jButton1.setText("Pause");
        else jButton1.setText("Play");
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LangtonAntGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LangtonAntGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LangtonAntGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LangtonAntGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LangtonAntGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
