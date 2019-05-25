package sticks;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author rcbgalido
 */
public class Main extends javax.swing.JFrame {
    
    private static final int MAXIMUM_STICKS = 21;
    private static final int PLAYER = 1;
    private static final int COMPUTER = 2;
    private static final int ENTER_KEYCODE = 10;
    private static final int COMPUTER_THINKING_DELAY = 1500; //1.5 seconds delay
    private static final String RESOURCE_LOCATION = "resources/";
    
    private final Computer computer;
    private final String[] gameImageFilenames;

    private int totalPickedUpSticks;
    private ImageIcon gameImage;
    
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
        
        computer = new Computer();
        gameImageFilenames = new String[] {
            "title.png", "01.png", "02.png", "03.png", "04.png", "05.png", "06.png", "07.png",
            "08.png", "09.png", "10.png", "11.png", "12.png", "13.png", "14.png", "15.png", 
            "16.png", "17.png", "18.png", "19.png", "20.png", "21.png"
        };
    }
    
    private void startNewGame() {
        resetTotalPickedUpSticks();
        enableNumberButtons();
        disableStartButton();
        logTA.setText("Game started.");
    }
    
    private void resetTotalPickedUpSticks() {
        totalPickedUpSticks = 0;
        updateGameImage(); // called after setting totalPickedUpSticks to 0 (to show title image)
    }
    
    private void updateGameImage() {
        gameImage = new ImageIcon(getClass().getResource(RESOURCE_LOCATION + gameImageFilenames[MAXIMUM_STICKS - totalPickedUpSticks]));
        sticksLBL.setIcon(gameImage);
    }
    
    private void enableNumberButtons() {
        oneBTN.setEnabled(true);
        twoBTN.setEnabled(true);
        threeBTN.setEnabled(true);
        fourBTN.setEnabled(true);
    }
    
    private void disableNumberButtons() {
        oneBTN.setEnabled(false);
        twoBTN.setEnabled(false);
        threeBTN.setEnabled(false);
        fourBTN.setEnabled(false);
    }
    
    private void enableStartButton() {
        startBTN.setEnabled(true);
    }
    
    private void disableStartButton() {
        startBTN.setEnabled(false);
    }
    
    private void executeMove(int moveBy, int pickedUpSticks) {
        disableNumberButtons();
        incrementTotalPickedUpSticks(pickedUpSticks);
        addMoveToLog(moveBy, pickedUpSticks);
        
        if (isWon()) {
            if (moveBy == PLAYER) {
                logTA.append("\nPlayer won the game.");
            } else if (moveBy == COMPUTER) {
                logTA.append("\nComputer won the game.");
            }
            logTA.setCaretPosition(logTA.getDocument().getLength());
            enableStartButton();
        } else {
            enableNumberButtons();
            limitEnabledNumberButtons();
            if (moveBy == PLAYER) { // if current move is by PLAYER and game is not yet won, start computer turn
                new computerTurnThread().start();
            }
        }
    }
    
    private void incrementTotalPickedUpSticks(int pickedUpSticks) {
        totalPickedUpSticks += pickedUpSticks;
        updateGameImage(); // update game image base on remaining sticks
    }
    
    private void addMoveToLog(int moveBy, int pickedUpSticks){
        String moveByStr = (moveBy == PLAYER) ? "Player" : "Computer";
        if (pickedUpSticks == 1) {
            logTA.append("\n" + moveByStr + " picked up 1 stick.");
        } else {
            logTA.append("\n" + moveByStr + " picked up " + pickedUpSticks + " sticks.");
        }
        logTA.setCaretPosition(logTA.getDocument().getLength());
    }
    
    private boolean isWon() {
        return totalPickedUpSticks == MAXIMUM_STICKS;
    }
    
    // limit enabled number buttons when sticks remaining are less than 4
    private void limitEnabledNumberButtons() {
        if (totalPickedUpSticks >= MAXIMUM_STICKS - 3) { // 18, 19, 20
            fourBTN.setEnabled(false);
        }
        if (totalPickedUpSticks >= MAXIMUM_STICKS - 2) { // 19, 20
            threeBTN.setEnabled(false);
        }
        if (totalPickedUpSticks >= MAXIMUM_STICKS - 1) { // 20
            twoBTN.setEnabled(false);
        }
    }
    
    private void delay(long time) {
        try {
            sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class computerTurnThread extends Thread {

        @Override
        public void run() {
            disableNumberButtons();
            loadingTF.setText("Computer thinking.....");
            delay(COMPUTER_THINKING_DELAY);
            executeMove(COMPUTER, computer.move(totalPickedUpSticks));
            loadingTF.setText("--");
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        gameImage=new ImageIcon(getClass().getResource("resources/title.png"));
        sticksLBL = new JLabel(gameImage);
        oneBTN = new javax.swing.JButton();
        threeBTN = new javax.swing.JButton();
        twoBTN = new javax.swing.JButton();
        fourBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTA = new javax.swing.JTextArea();
        startBTN = new javax.swing.JButton();
        loadingTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("21 Sticks");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(91, 125, 135));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 60), 10));

        sticksLBL.setMaximumSize(new java.awt.Dimension(464, 215));
        sticksLBL.setMinimumSize(new java.awt.Dimension(464, 215));

        oneBTN.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        oneBTN.setText("1");
        oneBTN.setEnabled(false);
        oneBTN.setFocusable(false);
        oneBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneBTNActionPerformed(evt);
            }
        });

        threeBTN.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        threeBTN.setText("3");
        threeBTN.setEnabled(false);
        threeBTN.setFocusable(false);
        threeBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeBTNActionPerformed(evt);
            }
        });

        twoBTN.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        twoBTN.setText("2");
        twoBTN.setEnabled(false);
        twoBTN.setFocusable(false);
        twoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoBTNActionPerformed(evt);
            }
        });

        fourBTN.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        fourBTN.setText("4");
        fourBTN.setEnabled(false);
        fourBTN.setFocusable(false);
        fourBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourBTNActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        logTA.setEditable(false);
        logTA.setBackground(new java.awt.Color(204, 204, 204));
        logTA.setColumns(20);
        logTA.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        logTA.setRows(5);
        logTA.setText("Instructions:\n- Player has to start the game. (Opponent: Computer)\n- Each can take a maximum of four (4) sticks and \n   a minimum of one (1) stick at a time.\n- The one who takes the 21st stick wins the game.");
        logTA.setFocusable(false);
        jScrollPane1.setViewportView(logTA);

        startBTN.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        startBTN.setText("Start Game");
        startBTN.setFocusable(false);
        startBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBTNActionPerformed(evt);
            }
        });

        loadingTF.setEditable(false);
        loadingTF.setBackground(new java.awt.Color(204, 204, 204));
        loadingTF.setFont(new java.awt.Font("Arial Unicode MS", 2, 11)); // NOI18N
        loadingTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        loadingTF.setText("--");
        loadingTF.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sticksLBL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(threeBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fourBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(oneBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(twoBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loadingTF)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(sticksLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startBTN)
                    .addComponent(loadingTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(oneBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(twoBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fourBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                            .addComponent(threeBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBTNActionPerformed
        startNewGame();
    }//GEN-LAST:event_startBTNActionPerformed

    private void oneBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneBTNActionPerformed
        executeMove(PLAYER, 1);
    }//GEN-LAST:event_oneBTNActionPerformed

    private void twoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoBTNActionPerformed
        executeMove(PLAYER, 2);
    }//GEN-LAST:event_twoBTNActionPerformed

    private void threeBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeBTNActionPerformed
        executeMove(PLAYER, 3);
    }//GEN-LAST:event_threeBTNActionPerformed

    private void fourBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourBTNActionPerformed
        executeMove(PLAYER, 4);
    }//GEN-LAST:event_fourBTNActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        char key = evt.getKeyChar();
        if (key == '1' && oneBTN.isEnabled()) {
            executeMove(PLAYER, 1);
        } else if (key == '2' && twoBTN.isEnabled()) {
            executeMove(PLAYER, 2);
        } else if (key == '3' && threeBTN.isEnabled()) {
            executeMove(PLAYER, 3);
        } else if (key == '4' && fourBTN.isEnabled()) {
            executeMove(PLAYER, 4);
        } else if (evt.getKeyCode() == ENTER_KEYCODE && startBTN.isEnabled()) {
            startNewGame();
        }
    }//GEN-LAST:event_formKeyPressed
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fourBTN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField loadingTF;
    private javax.swing.JTextArea logTA;
    private javax.swing.JButton oneBTN;
    private javax.swing.JButton startBTN;
    private javax.swing.JLabel sticksLBL;
    private javax.swing.JButton threeBTN;
    private javax.swing.JButton twoBTN;
    // End of variables declaration//GEN-END:variables
}
