package bookmgr.UI;

import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.main.Connection;
import bookmgr.exceptions.UserDoesntExistException;
import bookmgr.models.User;
import bookmgr.repos.AdminRepo;
import bookmgr.repos.UserRepo;
import java.util.ArrayList;

/**
 * View for printing a report of a specific user's loans
 */
public class RentReportForAUserView extends javax.swing.JFrame {

    public RentReportForAUserView() {
        initComponents();
        ErrorBox.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UsernameLabel = new javax.swing.JLabel();
        UsernameField = new javax.swing.JTextField();
        GetButton = new javax.swing.JButton();
        ErrorBox = new javax.swing.JLabel();
        CurrentBox = new javax.swing.JCheckBox();
        ReturnedBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("bookMGR - Report");
        setResizable(false);

        UsernameLabel.setLabelFor(UsernameField);
        UsernameLabel.setText("Username");

        GetButton.setText("Get");
        GetButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetButtonActionPerformed(evt);
            }
        });

        ErrorBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ErrorBox.setEnabled(false);

        CurrentBox.setText("Current loans");

        ReturnedBox.setText("Returned");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ErrorBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(UsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ReturnedBox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CurrentBox))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(GetButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsernameLabel)
                    .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CurrentBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReturnedBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GetButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ErrorBox, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void GetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetButtonActionPerformed
        Connection conn = new Connection();
        AdminRepo newRepo = new AdminRepo();
        UserRepo aRepo = new UserRepo();
        try {
            User user = aRepo.getUser(UsernameField.getText());
            if (CurrentBox.isSelected() && ReturnedBox.isSelected()) {
                ArrayList<String> list = newRepo.reportForRents(user.getInteger("id"), 2);
                ReportView newView = new ReportView(list);
                newView.render();
            } else if (CurrentBox.isSelected()) {
                ArrayList<String> list = newRepo.reportForRents(user.getInteger("id"), 0);
                ReportView newView = new ReportView(list);
                newView.render();
            } else if (ReturnedBox.isSelected()) {
                ArrayList<String> list = newRepo.reportForRents(user.getInteger("id"), 1);
                ReportView newView = new ReportView(list);
                newView.render();
            } else {
                ErrorBox.setText("Please select what to form reports on.");
                ErrorBox.setVisible(true);
            }
        } catch (UserDoesntExistException ex) {
            ErrorBox.setText("User doesn't exist.");
            ErrorBox.setVisible(true);
        } catch (BookDoesntExistException ex) {
            ErrorBox.setText("A report could not be formed.");
            ErrorBox.setVisible(true);
        }
        conn.close();
    }//GEN-LAST:event_GetButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RentReportForAUserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RentReportForAUserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RentReportForAUserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RentReportForAUserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RentReportForAUserView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CurrentBox;
    private javax.swing.JLabel ErrorBox;
    private javax.swing.JButton GetButton;
    private javax.swing.JCheckBox ReturnedBox;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsernameLabel;
    // End of variables declaration//GEN-END:variables
}
