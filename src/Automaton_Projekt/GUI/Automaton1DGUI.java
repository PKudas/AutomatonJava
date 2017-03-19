
package Automaton_Projekt;

import static Automaton_Projekt.NewMain.MapToArray;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Dialog;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Represents GUI for the 1 dimensional automata
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class Automaton1DGUI extends javax.swing.JFrame {

    /**
     * Creates new form Automaton1DGUI
     */
    CellStateFactory factory;
    CellNeighborhood neigh;
    Automaton Automat; 
    Automaton NewAutomat;
    boolean wrap = true;
    final int width = 200;
    final int height = 100;
    final int size = 200;
    private CellState [] [] board = new CellState [height] [width];
    private CellState [] Generation = new CellState [size];
    private boolean play;
    private int [] ChosenRule;
    Image offScreenImage;
    Graphics offScreenGraphic;
    
    /**
     *
     */
    public Automaton1DGUI() {
        String input = JOptionPane.showInputDialog(this,"Which Rule ? 0-255"); 
        try
        {
            int value = Integer.parseInt(input);
            if (value < 0 || value > 255)
                throw new Exception();
            String Bin = Integer.toBinaryString(value);
            int dif = 8 - Bin.length();
            for (int i = 0;i < dif;i++) {
                Bin = "0" + Bin;
            }
            ChosenRule = new int [8];
            for (int i = 0;i < 8;i++) {
                ChosenRule[i] = Character.getNumericValue(Bin.charAt(i));
            }            
        }
        catch (Exception e) {
            System.exit(0);
        }
        int odp = JOptionPane.showConfirmDialog(null,"Wrapped ?","Topology",JOptionPane.YES_NO_OPTION);
        if (odp==JOptionPane.YES_OPTION)
            wrap = true;
        else if (odp == JOptionPane.NO_OPTION)
            wrap = false;
        initComponents();
        offScreenImage = createImage(jPanel1.getWidth(),jPanel1.getHeight());
        offScreenGraphic = offScreenImage.getGraphics();
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                board[i][j] = OneDimState.ZERO;
            }
        }
        Map<CellCoordinates, CellState> s = new HashMap<>();
        for(int i = 0; i<size; i++){
            if (i == size/2)
                s.put(new Coords1D(i), OneDimState.ONE);
            else
                s.put(new Coords1D(i), OneDimState.ZERO);              
            
        }
        Generation = MapToArray(s);
        for(int i = 0;i<width;i++){			
            board[height-1][i] = Generation[i];		
	}
        rePaint();
        factory = new GeneralStateFactory(s);
        
        neigh = new OneDimNeighborhood(size,wrap);
        Automat = new Automaton1D(factory,neigh,size,ChosenRule,wrap);
        Timer time = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if(play) {
                    NewAutomat = Automat.nextState();
                    Generation = MapToArray(NewAutomat.getCells());
                    rePaint(); 
                    Automat = NewAutomat;
                    raise();
                    for(int i = 0;i<width;i++){			
                        board[height-1][i] = Generation[i];		
                    }       
                    rePaint();                     
                }
            }
        };
        time.scheduleAtFixedRate(task, 0, 40);
        rePaint();
    }
    /**
     * Raises all rows to the top 
     */
    private void raise() {
        for(int i = 1; i < height; i++) {
            for(int j = 0; j < width; j++) {
                board[i-1][j] = board[i][j];
            }
        }
        
    }    
    private void rePaint() {
        offScreenGraphic.setColor(jPanel1.getBackground());
        offScreenGraphic.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(board[i][j] == OneDimState.ONE) {
                    offScreenGraphic.setColor(Color.BLACK);
                    int x = j * jPanel1.getWidth() / width;
                    int y = i *  jPanel1.getHeight() / height ;
                    offScreenGraphic.drawRect(x, y, jPanel1.getWidth()/ width, jPanel1.getHeight()/ height);
                    offScreenGraphic.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
                if(board[i][j] == OneDimState.ZERO) {
                    offScreenGraphic.setColor(Color.WHITE);
                    int x = j * jPanel1.getWidth() / width;
                    int y = i *  jPanel1.getHeight() / height ;
                    offScreenGraphic.drawRect(x, y, jPanel1.getWidth()/ width, jPanel1.getHeight()/ height);
                    offScreenGraphic.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
            }
        }
        jPanel1.getGraphics().drawImage(offScreenImage, 0, 0, jPanel1);
    }
    /**
     * Returns Array created from the map
     * @param s map of Cell coordinates and Cell state
     * @return Array created from the map
     */
    private CellState  [] MapToArray(Map<CellCoordinates, CellState> s) {
        CellState [] Board = new CellState [size];
        for(Map.Entry<CellCoordinates,CellState> entry : s.entrySet()) {
            Board[entry.getKey().getX()] = entry.getValue();
        } 

        return Board;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
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
            .addGap(0, 499, Short.MAX_VALUE)
        );

        jButton1.setText("Play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGap(0, 766, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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
            java.util.logging.Logger.getLogger(Automaton1DGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Automaton1DGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Automaton1DGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Automaton1DGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Automaton1DGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
