package emplois;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * @author yosrb
 */
public class Seances extends javax.swing.JPanel {
    private Main mainApp; //link to the main card layout

    public Seances(Main mainApp) {
        this.mainApp = mainApp;
        initComponents();
        DefaultTableModel model = (DefaultTableModel) tablecours.getModel();
        for(EnseignantCours ec:getEnseignantsCours()){
            Object[] row= {ec.getId(),ec.getClasse(),ec.getMatiere(),ec.getJour(),ec.getHeure(),ec.getNom(),ec.getContact()};
            model.addRow(row);
        }

    }
       public static List<EnseignantCours> getEnseignantsCours() 
    {
        List<EnseignantCours> list = new ArrayList<EnseignantCours>(); 
        try {
            String req = "select * from enseignant_cours;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //public EnseignantCours(String id, String classe, String matiere, String jour, String heure, String nom, String contact) {
                list.add(new EnseignantCours(resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(8),resultSet.getString(9),resultSet.getString(2),resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
  
    public Seances() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        matricule = new javax.swing.JTextField();
        chercher = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablecours = new javax.swing.JTable();
        choixClasse = new javax.swing.JComboBox<>();
        matricule1 = new javax.swing.JTextField();
        retour = new javax.swing.JButton();
        matricule2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        chercher1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        chercher2 = new javax.swing.JButton();
        choixClasse1 = new javax.swing.JComboBox<>();

        jLabel1.setText("L es séances de cours dans une classe");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setText("Matière");

        matricule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matriculeActionPerformed(evt);
            }
        });

        chercher.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        chercher.setText("Chercher");
        chercher.setName(""); // NOI18N
        chercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chercherActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setText("Classe");

        tablecours.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "classe", "matiere", "jour", "heure", "nom", "contact"
            }
        ));
        jScrollPane1.setViewportView(tablecours);

        choixClasse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixClasseActionPerformed(evt);
            }
        });

        retour.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        retour.setText("Retour");
        retour.setName(""); // NOI18N
        retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel4.setText("ID");

        chercher1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        chercher1.setText("Supprmier");
        chercher1.setName(""); // NOI18N
        chercher1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chercher1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setText("Classe");

        chercher2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        chercher2.setText("Chercher");
        chercher2.setName(""); // NOI18N
        chercher2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chercher2ActionPerformed(evt);
            }
        });

        choixClasse1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixClasse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixClasse1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(688, 688, 688)
                                .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(choixClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(matricule1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(chercher))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(12, 12, 12)
                                            .addComponent(choixClasse1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(chercher2))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(matricule2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(chercher1))))))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(retour)
                        .addGap(43, 43, 43))))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(chercher)
                    .addComponent(choixClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(matricule1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(chercher1)
                    .addComponent(matricule2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(retour)
                    .addComponent(jLabel5)
                    .addComponent(chercher2)
                    .addComponent(choixClasse1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(124, 124, 124))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void matriculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matriculeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matriculeActionPerformed

    private void chercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chercherActionPerformed

    private void choixClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixClasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choixClasseActionPerformed

    private void retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourActionPerformed
                mainApp.afficherAccueil();// TODO add your handling code here:
// TODO add your handling code here:
    }//GEN-LAST:event_retourActionPerformed

    private void chercher1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chercher1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chercher1ActionPerformed

    private void chercher2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chercher2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chercher2ActionPerformed

    private void choixClasse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixClasse1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choixClasse1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chercher;
    private javax.swing.JButton chercher1;
    private javax.swing.JButton chercher2;
    private javax.swing.JComboBox<String> choixClasse;
    private javax.swing.JComboBox<String> choixClasse1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField matricule;
    private javax.swing.JTextField matricule1;
    private javax.swing.JTextField matricule2;
    private javax.swing.JButton retour;
    private javax.swing.JTable tablecours;
    // End of variables declaration//GEN-END:variables
}
