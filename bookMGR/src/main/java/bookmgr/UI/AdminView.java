package bookmgr.UI;

import bookmgr.models.User;

/**
 * Basic view for all functionalities performed by admin
 */
public class AdminView extends javax.swing.JFrame {

    User user;

    public AdminView(User user) {
        initComponents();
        this.user = user;
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
        jPanel1 = new javax.swing.JPanel();
        ChangePasswordButton = new javax.swing.JButton();
        Books = new javax.swing.JPanel();
        AddBook = new javax.swing.JButton();
        RemoveBook = new javax.swing.JButton();
        EditBook = new javax.swing.JButton();
        Authors = new javax.swing.JPanel();
        AddAuthor = new javax.swing.JButton();
        RemoveAuthor = new javax.swing.JButton();
        Users = new javax.swing.JPanel();
        AddUser = new javax.swing.JButton();
        RemoveUser = new javax.swing.JButton();
        SettleFee = new javax.swing.JButton();
        ChangeAPasswordButton = new javax.swing.JButton();
        Report = new javax.swing.JPanel();
        LoansByUser = new javax.swing.JButton();
        AllBooks = new javax.swing.JButton();
        BooksByAuthor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("bookMGR");
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

        jPanel1.setLayout(new java.awt.GridBagLayout());

        ChangePasswordButton.setText("Change password");
        ChangePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangePasswordButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ChangePasswordButton, new java.awt.GridBagConstraints());

        jTabbedPane1.addTab("Settings", jPanel1);

        Books.setLayout(new java.awt.GridBagLayout());

        AddBook.setText("Add book");
        AddBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBookActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        Books.add(AddBook, gridBagConstraints);

        RemoveBook.setText("Remove book");
        RemoveBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveBookActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        Books.add(RemoveBook, gridBagConstraints);

        EditBook.setText("Edit book");
        EditBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditBookActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        Books.add(EditBook, gridBagConstraints);

        jTabbedPane1.addTab("Books", Books);

        Authors.setLayout(new java.awt.GridBagLayout());

        AddAuthor.setText("Add author");
        AddAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAuthorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        Authors.add(AddAuthor, gridBagConstraints);

        RemoveAuthor.setText("Remove author");
        RemoveAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveAuthorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        Authors.add(RemoveAuthor, gridBagConstraints);

        jTabbedPane1.addTab("Authors", Authors);

        Users.setLayout(new java.awt.GridBagLayout());

        AddUser.setText("Add user");
        AddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddUserActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        Users.add(AddUser, gridBagConstraints);

        RemoveUser.setText("Remove user");
        RemoveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveUserActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        Users.add(RemoveUser, gridBagConstraints);

        SettleFee.setText("Settle fee");
        SettleFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettleFeeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        Users.add(SettleFee, gridBagConstraints);

        ChangeAPasswordButton.setText("Change a password");
        ChangeAPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeAPasswordButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        Users.add(ChangeAPasswordButton, gridBagConstraints);

        jTabbedPane1.addTab("Users", Users);

        Report.setLayout(new java.awt.GridBagLayout());

        LoansByUser.setText("Loans by user");
        LoansByUser.setAlignmentX(0.5F);
        LoansByUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoansByUserActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        Report.add(LoansByUser, gridBagConstraints);

        AllBooks.setText("All books");
        AllBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllBooksActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        Report.add(AllBooks, gridBagConstraints);

        BooksByAuthor.setText("Books by author");
        BooksByAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BooksByAuthorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        Report.add(BooksByAuthor, gridBagConstraints);

        jTabbedPane1.addTab("Report", Report);

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

    private void AddBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBookActionPerformed
        AddBookView newView = new AddBookView();
        newView.render();
    }//GEN-LAST:event_AddBookActionPerformed

    private void RemoveBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveBookActionPerformed
        RemoveBookView newView = new RemoveBookView();
        newView.render();
    }//GEN-LAST:event_RemoveBookActionPerformed

    private void EditBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBookActionPerformed
        EditBookView newView = new EditBookView();
        newView.render();
    }//GEN-LAST:event_EditBookActionPerformed

    private void AddAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAuthorActionPerformed
        AddAuthorView newView = new AddAuthorView();
        newView.render();
    }//GEN-LAST:event_AddAuthorActionPerformed

    private void RemoveAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveAuthorActionPerformed
        RemoveAuthorView newView = new RemoveAuthorView();
        newView.render();
    }//GEN-LAST:event_RemoveAuthorActionPerformed

    private void AddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddUserActionPerformed
        AddUserView newView = new AddUserView();
        newView.render();
    }//GEN-LAST:event_AddUserActionPerformed

    private void RemoveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveUserActionPerformed
        RemoveUserView newView = new RemoveUserView();
        newView.render();
    }//GEN-LAST:event_RemoveUserActionPerformed

    private void SettleFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettleFeeActionPerformed
        SettleAFeeView newView = new SettleAFeeView();
        newView.render();
    }//GEN-LAST:event_SettleFeeActionPerformed

    private void BooksByAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BooksByAuthorActionPerformed
        AuthorReportView newView = new AuthorReportView();
        newView.render();
    }//GEN-LAST:event_BooksByAuthorActionPerformed

    private void AllBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllBooksActionPerformed
        RentReportView newView = new RentReportView();
        newView.render();
    }//GEN-LAST:event_AllBooksActionPerformed

    private void LoansByUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoansByUserActionPerformed
        RentReportForAUserView newView = new RentReportForAUserView();
        newView.render();
    }//GEN-LAST:event_LoansByUserActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void ChangePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePasswordButtonActionPerformed
     ChangeAdminPasswordView newView = new ChangeAdminPasswordView(user);
     newView.render();
    }//GEN-LAST:event_ChangePasswordButtonActionPerformed

    private void ChangeAPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeAPasswordButtonActionPerformed
        ChangeAPasswordView newView = new ChangeAPasswordView();
        newView.render();
    }//GEN-LAST:event_ChangeAPasswordButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminView(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAuthor;
    private javax.swing.JButton AddBook;
    private javax.swing.JButton AddUser;
    private javax.swing.JButton AllBooks;
    private javax.swing.JPanel Authors;
    private javax.swing.JPanel Books;
    private javax.swing.JButton BooksByAuthor;
    private javax.swing.JButton ChangeAPasswordButton;
    private javax.swing.JButton ChangePasswordButton;
    private javax.swing.JButton EditBook;
    private javax.swing.JButton ExitButton;
    private javax.swing.JPanel Home;
    private javax.swing.JLabel InfoLabel;
    private javax.swing.JButton LoansByUser;
    private javax.swing.JButton RemoveAuthor;
    private javax.swing.JButton RemoveBook;
    private javax.swing.JButton RemoveUser;
    private javax.swing.JPanel Report;
    private javax.swing.JButton SettleFee;
    private javax.swing.JPanel Users;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
