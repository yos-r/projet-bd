package emplois;

import static emplois.Accueil.getClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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
        List<String> classes=getClasses();
        String[] lesclasses = classes.toArray(new String[0]);
        DefaultComboBoxModel<String> modelclasse = new DefaultComboBoxModel<>(lesclasses);
        choixClasse.setModel(modelclasse);
        choixClasse1.setModel(modelclasse);

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
       public static List<EnseignantCours> getEnseignantsCoursByClasseAndMatiere(String classe,String matiere){
            List<EnseignantCours> list = new ArrayList<EnseignantCours>(); 
try {
            String req = "select * from enseignant_cours where classe=? and matiere LIKE ?;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(req);
            preparedStatement.setString(1,classe);
            preparedStatement.setString(2, "%"+matiere+"%");
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
       public static List<EnseignantCours> getEnseignantsCoursByClasse(String classe){
                   List<EnseignantCours> list = new ArrayList<EnseignantCours>(); 
try {
            String req = "select * from enseignant_cours where classe=? ORDER BY num_jour";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(req);
            preparedStatement.setString(1,classe);
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
       public static int deleteEnseignantCoursById(String id){
       int st=0;
        try
        {
            String req = "DELETE FROM cours where id = ?";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement)con.prepareCall(req);
            preparedStatement.setString(1, id);
            st = preparedStatement.executeUpdate();
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
        return st;
       }
  
    public Seances() {
        initComponents();
    }
    public void refreshTable(){
      DefaultTableModel model = (DefaultTableModel) tablecours.getModel();
        model.setRowCount(0);

        for(EnseignantCours ec:getEnseignantsCours()){
            Object[] row= {ec.getId(),ec.getClasse(),ec.getMatiere(),ec.getJour(),ec.getHeure(),ec.getNom(),ec.getContact()};
            model.addRow(row);
        } 
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        matricule = new javax.swing.JTextField();
        chercher = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablecours = new javax.swing.JTable();
        choixClasse = new javax.swing.JComboBox<>();
        matiere = new javax.swing.JTextField();
        retour = new javax.swing.JButton();
        idcours = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        supprimer = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        chercher2 = new javax.swing.JButton();
        choixClasse1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        chercher3 = new javax.swing.JButton();
        suppAlerte = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Les séances de cours dans la semaine d'une matière dans une classe");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setText("Matière");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, 20));

        matricule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matriculeActionPerformed(evt);
            }
        });
        add(matricule, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 106, 80, 0));

        chercher.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        chercher.setText("Chercher");
        chercher.setName(""); // NOI18N
        chercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chercherActionPerformed(evt);
            }
        });
        add(chercher, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setText("Classe");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, 20));

        tablecours.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "classe", "matiere", "jour", "heure", "nom", "contact"
            }
        ));
        jScrollPane1.setViewportView(tablecours);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 699, 245));

        choixClasse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixClasseActionPerformed(evt);
            }
        });
        add(choixClasse, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        matiere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matiereActionPerformed(evt);
            }
        });
        add(matiere, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 93, -1));

        retour.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        retour.setText("Retour");
        retour.setMaximumSize(new java.awt.Dimension(77, 22));
        retour.setMinimumSize(new java.awt.Dimension(77, 22));
        retour.setName(""); // NOI18N
        retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourActionPerformed(evt);
            }
        });
        add(retour, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, -1, -1));
        add(idcours, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 93, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel4.setText("ID");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, 20));

        supprimer.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        supprimer.setText("Supprimer");
        supprimer.setMaximumSize(new java.awt.Dimension(77, 22));
        supprimer.setMinimumSize(new java.awt.Dimension(77, 22));
        supprimer.setName(""); // NOI18N
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });
        add(supprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, -1, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setText("Classe");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, -1, -1));

        chercher2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        chercher2.setText("Chercher");
        chercher2.setName(""); // NOI18N
        chercher2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chercher2ActionPerformed(evt);
            }
        });
        add(chercher2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 80, -1));

        choixClasse1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixClasse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixClasse1ActionPerformed(evt);
            }
        });
        add(choixClasse1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, 87, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Emploi du temps de la semaine dans une classe");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 515, -1));

        chercher3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        chercher3.setText("Afficher tous les cours");
        chercher3.setName(""); // NOI18N
        chercher3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chercher3ActionPerformed(evt);
            }
        });
        add(chercher3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, -1));
        add(suppAlerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 398, 167, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void matriculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matriculeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matriculeActionPerformed

    private void chercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chercherActionPerformed
       DefaultTableModel model = (DefaultTableModel) tablecours.getModel();
        String matieretexte=matiere.getText();
        String classetexte=choixClasse.getSelectedItem().toString();
        model.setRowCount(0);
        for(EnseignantCours ec:getEnseignantsCoursByClasseAndMatiere(classetexte, matieretexte)){
            Object[] row= {ec.getId(),ec.getClasse(),ec.getMatiere(),ec.getJour(),ec.getHeure(),ec.getNom(),ec.getContact()};
            model.addRow(row);
        } // TODO add your handling code here:
    }//GEN-LAST:event_chercherActionPerformed

    private void choixClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixClasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choixClasseActionPerformed

    private void retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourActionPerformed
                mainApp.afficherAccueil();// TODO add your handling code here:
// TODO add your handling code here:
    }//GEN-LAST:event_retourActionPerformed

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        String id=idcours.getText();        // TODO add your handling code here:
        int st=deleteEnseignantCoursById(id);
        if (st==0){
            System.out.println("Le cours "+id+ " n'existe pas dans la base");
            JOptionPane.showMessageDialog(null, 
                                          "Erreur lors de la suppression. Le cours d'id "+id+" n'existe pas dans la base", 
                                          "Erreur suppression du cours", 
                                          JOptionPane.ERROR_MESSAGE);
        }else{
            suppAlerte.setText("");
        }
        refreshTable();
        idcours.setText("");
    }//GEN-LAST:event_supprimerActionPerformed

    private void chercher2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chercher2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tablecours.getModel();
        String classetexte=choixClasse1.getSelectedItem().toString();
        model.setRowCount(0);
        for(EnseignantCours ec:getEnseignantsCoursByClasse(classetexte)){
            Object[] row= {ec.getId(),ec.getClasse(),ec.getMatiere(),ec.getJour(),ec.getHeure(),ec.getNom(),ec.getContact()};
            model.addRow(row);
        } // T
        
    }//GEN-LAST:event_chercher2ActionPerformed

    private void choixClasse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixClasse1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choixClasse1ActionPerformed

    private void matiereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matiereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matiereActionPerformed

    private void chercher3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chercher3ActionPerformed
         refreshTable();       // TODO add your handling code here:
    }//GEN-LAST:event_chercher3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chercher;
    private javax.swing.JButton chercher2;
    private javax.swing.JButton chercher3;
    private javax.swing.JComboBox<String> choixClasse;
    private javax.swing.JComboBox<String> choixClasse1;
    private javax.swing.JTextField idcours;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField matiere;
    private javax.swing.JTextField matricule;
    private javax.swing.JButton retour;
    private javax.swing.JLabel suppAlerte;
    private javax.swing.JButton supprimer;
    private javax.swing.JTable tablecours;
    // End of variables declaration//GEN-END:variables
}
