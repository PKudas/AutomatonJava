
package Automaton_Projekt;

import static Automaton_Projekt.NewMain.MapToArray;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
/**
 * Represents GUI for WireWorld automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class WireWorldGUI extends javax.swing.JFrame {
    Structure str;
    final int width = 60;
    final int height = 30;
    boolean wrap = true;
    CellStateFactory factory;
    CellNeighborhood neigh;
    CellState [][] Board;
    private boolean play;
    Image offScreenImage;
    Graphics offScreenGraphic;
    Automaton Automat; 
    Automaton NewAutomat; 
    /**
     * Creates new form WireWorldGUI
     */
    public WireWorldGUI() {
        Map<CellCoordinates, CellState> s = new HashMap<>();
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                s.put(new Coords2D(j,i), WireElectronState.VOID);
                
            }
        }
        Board = new CellState[height][width];
        Board = MapToArray(s,height,width);
        factory = new GeneralStateFactory(s);
        neigh = new MoorNeighborhood(height,width,wrap);
        Automat = new WireWorld(factory,neigh,width,height);
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
        time.scheduleAtFixedRate(task, 0, 300);
        rePaint();
    }
    private void rePaint() {
        offScreenGraphic.setColor(jPanel1.getBackground());
        offScreenGraphic.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(Board[i][j] == WireElectronState.VOID) {
                    offScreenGraphic.setColor(Color.BLACK);
                    int x = j * jPanel1.getWidth() / width;
                    int y = i *  jPanel1.getHeight() / height ;
                    offScreenGraphic.drawRect(x, y, jPanel1.getWidth()/ width, jPanel1.getHeight()/ height);
                    offScreenGraphic.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
                if(Board[i][j] == WireElectronState.ELECTRON_HEAD) {
                    offScreenGraphic.setColor(Color.BLUE);
                    int x = j * jPanel1.getWidth() / width;
                    int y = i *  jPanel1.getHeight() / height ;
                    offScreenGraphic.drawRect(x, y, jPanel1.getWidth()/ width, jPanel1.getHeight()/ height);
                    offScreenGraphic.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
                if(Board[i][j] == WireElectronState.ELECTRON_TAIL) {
                    offScreenGraphic.setColor(Color.RED);
                    int x = j * jPanel1.getWidth() / width;
                    int y = i *  jPanel1.getHeight() / height ;
                    offScreenGraphic.drawRect(x, y, jPanel1.getWidth()/ width, jPanel1.getHeight()/ height);
                    offScreenGraphic.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
                if(Board[i][j] == WireElectronState.WIRE) {
                    offScreenGraphic.setColor(Color.YELLOW);
                    int x = j * jPanel1.getWidth() / width;
                    int y = i *  jPanel1.getHeight() / height ;
                    offScreenGraphic.drawRect(x, y, jPanel1.getWidth()/ width, jPanel1.getHeight()/ height);
                    offScreenGraphic.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
            }
        }
        offScreenGraphic.setColor(Color.WHITE);
        for(int i = 1; i < width; i++) {
            int y = i *  jPanel1.getHeight() / height ;
            offScreenGraphic.drawLine(0, y, jPanel1.getWidth(), y);
        }
        for(int i = 1; i < width; i++) {
            int x = i * jPanel1.getWidth() / width;
            offScreenGraphic.drawLine(x, 0, x, jPanel1.getHeight());
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
        if (str == Structure.VOID)
            map.put(new Coords2D(coords.getX(),coords.getY()), WireElectronState.VOID);
        else if (str == Structure.HEAD)
            map.put(new Coords2D(coords.getX(),coords.getY()), WireElectronState.ELECTRON_HEAD);
        else if (str == Structure.TAIL)
            map.put(new Coords2D(coords.getX(),coords.getY()), WireElectronState.ELECTRON_TAIL);
        else if (str == Structure.WIRE)
            map.put(new Coords2D(coords.getX(),coords.getY()), WireElectronState.WIRE);    
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
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
            .addGap(0, 528, Short.MAX_VALUE)
        );

        jButton1.setText("Play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Void");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Wire");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Electron Head");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Electron Tail");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addGap(0, 453, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2)
                        .addComponent(jRadioButton3))
                    .addComponent(jRadioButton4, javax.swing.GroupLayout.Alignment.TRAILING))
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
            Automat.insertStructure(GetMapForTheStructure(str,new Coords2D(j,i)),height ,width);
            Board = MapToArray(Automat.getFactory().getMapFromFactory(),height,width);
        
        rePaint();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        str = Structure.VOID;
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        str = Structure.WIRE;
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        str = Structure.HEAD;
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        str = Structure.TAIL;
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        play = !play;
        if(play) jButton1.setText("Pause");
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
            java.util.logging.Logger.getLogger(WireWorldGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WireWorldGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WireWorldGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WireWorldGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WireWorldGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration//GEN-END:variables
}
