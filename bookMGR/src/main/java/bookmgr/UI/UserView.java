package bookmgr.UI;

import bookmgr.models.User;

/**
 * Basic view for all user functionality
 */
public class UserView extends javax.swing.JFrame {
    
    private User user;

    public UserView(User user) {
        initComponents();
        this.user = user;
        PendingFeesLabel.setText("Your pending fees: " + user.getInteger("fees") + " €.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        Home = new javax.swing.JPanel();
        ExitButton = new javax.swing.JButton();
        InfoLabel = new javax.swing.JLabel();
        MyAccount = new javax.swing.JPanel();
        ChangePasswordButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        PendingFeesLabel = new javax.swing.JLabel();
        Loans = new javax.swing.JPanel();
        RentBookButton = new javax.swing.JButton();
        ReturnBookButton = new javax.swing.JButton();
        ExtendLoanButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("bookMGR");
        setMinimumSize(new java.awt.Dimension(523, 371));
        setPreferredSize(new java.awt.Dimension(350, 350));
        setResizable(false);

        Home.setLayout(new java.awt.GridBagLayout());

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        Home.add(ExitButton, gridBagConstraints);

        InfoLabel.setText("Select action from tabs, press exit when ready.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        Home.add(InfoLabel, gridBagConstraints);

        jTabbedPane1.addTab("Home", Home);

        MyAccount.setLayout(new java.awt.GridBagLayout());

        ChangePasswordButton.setText("Change password");
        MyAccount.add(ChangePasswordButton, new java.awt.GridBagConstraints());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        MyAccount.add(jSeparator1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        MyAccount.add(PendingFeesLabel, gridBagConstraints);

        jTabbedPane1.addTab("My account", MyAccount);

        Loans.setLayout(new java.awt.GridBagLayout());

        RentBookButton.setText("Rent book");
        RentBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RentBookButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        Loans.add(RentBookButton, gridBagConstraints);

        ReturnBookButton.setText("Return book");
        ReturnBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnBookButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        Loans.add(ReturnBookButton, gridBagConstraints);

        ExtendLoanButton.setText("Extend loan");
        ExtendLoanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExtendLoanButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        Loans.add(ExtendLoanButton, gridBagConstraints);

        jTabbedPane1.addTab("Loans", Loans);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExtendLoanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExtendLoanButtonActionPerformed
        ExtendLoanView newView = new ExtendLoanView(user);
        newView.render();
    }//GEN-LAST:event_ExtendLoanButtonActionPerformed

    private void ReturnBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnBookButtonActionPerformed
        ReturnBookView newView = new ReturnBookView(user);
        newView.render();
    }//GEN-LAST:event_ReturnBookButtonActionPerformed

    private void RentBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RentBookButtonActionPerformed
        RentBookView newView = new RentBookView(user);
        newView.render();
    }//GEN-LAST:event_RentBookButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    public void render() {
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
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserView(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChangePasswordButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton ExtendLoanButton;
    private javax.swing.JPanel Home;
    private javax.swing.JLabel InfoLabel;
    private javax.swing.JPanel Loans;
    private javax.swing.JPanel MyAccount;
    private javax.swing.JLabel PendingFeesLabel;
    private javax.swing.JButton RentBookButton;
    private javax.swing.JButton ReturnBookButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
