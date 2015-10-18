package bookmgr.UI;

import bookmgr.main.Connection;
import bookmgr.exceptions.AuthorDoesntExistException;
import bookmgr.exceptions.AuthorHasNoBooksException;
import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.repos.AdminRepo;
import java.util.ArrayList;

/**
 * View for printing a report of a specific authors books
 */
public class AuthorReportView extends javax.swing.JFrame {

    public AuthorReportView() {
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

        AuthorName = new javax.swing.JLabel();
        AuthorNameField = new javax.swing.JTextField();
        GetButton = new javax.swing.JButton();
        ErrorBox = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("bookMGR - Report");
        setResizable(false);

        AuthorName.setLabelFor(AuthorNameField);
        AuthorName.setText("Author name");

        GetButton.setText("Get");
        GetButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetButtonActionPerformed(evt);
            }
        });

        ErrorBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ErrorBox.setText("Author doesn't exist.");
        ErrorBox.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ErrorBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AuthorName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AuthorNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(GetButton)
                        .addGap(116, 116, 116))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AuthorName)
                    .addComponent(AuthorNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GetButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ErrorBox, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void GetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetButtonActionPerformed
        Connection conn = new Connection();
        AdminRepo newRepo = new AdminRepo();
        
        try {
            ArrayList<String> list = newRepo.reportBooksByAuthor(AuthorNameField.getText());
            ReportView newView = new ReportView(list);
            newView.render();
            this.dispose();
        } catch (AuthorDoesntExistException ex) {
            ErrorBox.setVisible(true);
        } catch (BookDoesntExistException | AuthorHasNoBooksException ex) {
            ErrorBox.setText("Author has no books to show.");
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
            java.util.logging.Logger.getLogger(AuthorReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthorReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthorReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthorReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthorReportView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AuthorName;
    private javax.swing.JTextField AuthorNameField;
    private javax.swing.JLabel ErrorBox;
    private javax.swing.JButton GetButton;
    // End of variables declaration//GEN-END:variables
}
