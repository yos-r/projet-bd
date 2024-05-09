package emplois;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 * @author yosrb
 */
public class Accueil extends javax.swing.JPanel {

    private Main mainApp; //link to the main card layout

    public Accueil(Main mainApp) {
        this.mainApp = mainApp;
        initComponents();
        DefaultTableModel model = (DefaultTableModel) tableenseignants.getModel();
        DefaultTableModel modelcours = (DefaultTableModel) tablecours.getModel();

        List<String> classes=getClasses();
        List<String> heures=getHeure();
        List<String> jours=getJour();
        String[] lesclasses = classes.toArray(new String[0]);
         String[] lesheures = heures.toArray(new String[0]);
        String[] lesjours = jours.toArray(new String[0]);
        DefaultComboBoxModel<String> modelclasse = new DefaultComboBoxModel<>(lesclasses);
        DefaultComboBoxModel<String> modelheure = new DefaultComboBoxModel<>(lesheures);
        DefaultComboBoxModel<String> modeljours = new DefaultComboBoxModel<>(lesjours);
        choixClasse.setModel(modelclasse);
        choixHeure.setModel(modelheure);
        choixJour.setModel(modeljours);

        for(Enseignant enseignant:this.getEnseignants()){
            Object[] row= {enseignant.getMatricule(),enseignant.getNom(),enseignant.getContact()};
            model.addRow(row);
        }
        for(Cours cours:this.getCours()){
            Object[] row= {cours.getClasse(),cours.getMatiere(),cours.getJour(),cours.getHeure(),cours.getEnseignant()};
            modelcours.addRow(row);
        }
        
    }
    public static List<String> getClasses(){
        List<String> list = new ArrayList<String>(); 
        try{
            String req = "SELECT DISTINCT(classe) FROM tb_cours;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
     public static List<String> getHeure(){
        List<String> list = new ArrayList<String>(); 
        try{
            String req = "SELECT DISTINCT(heure) FROM tb_cours;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
      public static List<String> getJour(){
        List<String> list = new ArrayList<String>(); 
        try{
            String req = "SELECT DISTINCT(Jour) FROM tb_cours;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    

    public static List<Enseignant> getEnseignants() 
    {
        List<Enseignant> list = new ArrayList<Enseignant>(); 
        try {
            String req = "select * from tb_enseignant;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Enseignant(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
     public static List<Cours> getCours() 
    {
        List<Cours> list = new ArrayList<Cours>(); 
        try {
            String req = "select * from tb_cours;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Cours(resultSet.getString(2),resultSet.getString(3),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //recherche enseignants par matricule
    public static List<Enseignant> getEnseignantByMatricule(String matricule)
    {
        List<Enseignant> liste = new ArrayList<Enseignant>();
        try
        {
            String req = "SELECT * FROM tb_enseignant where matricule = ?;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement)con.prepareCall(req);
            preparedStatement.setString(1, matricule);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                liste.add(new Enseignant(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return liste;
    }
    public static int addEnseignant(String matricule,String nom,String contact)
    {
        int st=0;
        try
        {
            String req = "INSERT INTO tb_enseignant VALUES (?,?,?);";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement)con.prepareCall(req);
            preparedStatement.setString(1, matricule);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, contact);
            st = preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return st;
    }
    public static int addCours(String classe,String matiere, String jour, String heure, String matricule)
    {
        int st=0;
        
        String[] jours={"LUNDI","MARDI","MERCREDI","JEUDI","VENDREDI"};
        List<String> listejours=Arrays.asList(jours);
        int indexjour=listejours.indexOf(jour)+1;
        
        try
        {
            String req = "INSERT INTO tb_cours(classe,matiere,num_jour,jour,heure,matricule_ens) VALUES (?,?,?,?,?,?);";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement)con.prepareCall(req);
            preparedStatement.setString(1, classe);
            preparedStatement.setString(2,matiere);
            preparedStatement.setString(3, Integer.toString(indexjour));
            preparedStatement.setString(4, jour);
            preparedStatement.setString(5, heure);
            preparedStatement.setString(6, matricule);
            
            st = preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return st;
    }
    
    public static int updateEnseignant(String matricule,String nom,String contact)
    {
        int st=0;
        try
        {
            String req = "UPDATE tb_enseignant SET nom = ? , contact =?  WHERE matricule=?;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement)con.prepareCall(req);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, contact);
            preparedStatement.setString(3, matricule);
            st = preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return st;
    }
    
    public static int deleteEnseignantByMatricule(String matricule)
    {
        int st=0;
        try
        {
            String req = "DELETE FROM tb_enseignant where matricule = ?;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement)con.prepareCall(req);
            preparedStatement.setString(1, matricule);
            st = preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return st;
    }
    public Accueil() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        matricule = new javax.swing.JTextField();
        nom = new javax.swing.JTextField();
        chercher = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        contact = new javax.swing.JTextField();
        requetes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablecours = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableenseignants = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        enregistrer = new javax.swing.JButton();
        modifier = new javax.swing.JButton();
        supprimer = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        matiere = new javax.swing.JTextField();
        matricule2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        choixClasse = new javax.swing.JComboBox<>();
        choixJour = new javax.swing.JComboBox<>();
        choixHeure = new javax.swing.JComboBox<>();
        enregistrercours = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Formulaire d'enregistrement des enseignants");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setText("Matricule");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setText("Nom");

        matricule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matriculeActionPerformed(evt);
            }
        });

        nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomActionPerformed(evt);
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

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setText("Contact");

        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });

        requetes.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        requetes.setText("Requêtes");
        requetes.setName(""); // NOI18N
        requetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requetesActionPerformed(evt);
            }
        });

        tablecours.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Classe", "Matiere", "Jour", "Heure", "Enseignant"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablecours);
        if (tablecours.getColumnModel().getColumnCount() > 0) {
            tablecours.getColumnModel().getColumn(0).setResizable(false);
            tablecours.getColumnModel().getColumn(2).setResizable(false);
        }

        tableenseignants.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricule", "Nom", "Contact"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableenseignants);
        if (tableenseignants.getColumnModel().getColumnCount() > 0) {
            tableenseignants.getColumnModel().getColumn(0).setResizable(false);
            tableenseignants.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setText("Formulaire de gestion des cours");

        enregistrer.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        enregistrer.setText("Enregistrer");
        enregistrer.setName(""); // NOI18N
        enregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enregistrerActionPerformed(evt);
            }
        });

        modifier.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        modifier.setText("Modifier");
        modifier.setName(""); // NOI18N
        modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierActionPerformed(evt);
            }
        });

        supprimer.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        supprimer.setText("Supprimer");
        supprimer.setName(""); // NOI18N
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel6.setText("Classe");

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel7.setText("Matière");

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel8.setText("Jour");

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel9.setText("Heure");

        matiere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matiereActionPerformed(evt);
            }
        });

        matricule2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matricule2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel10.setText("Matricule Ens.");

        choixClasse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixClasseActionPerformed(evt);
            }
        });

        choixJour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixJour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixJourActionPerformed(evt);
            }
        });

        choixHeure.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixHeure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixHeureActionPerformed(evt);
            }
        });

        enregistrercours.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        enregistrercours.setText("Enregistrer");
        enregistrercours.setName(""); // NOI18N
        enregistrercours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enregistrercoursActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(45, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel3))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(chercher))
                                                    .addComponent(nom)
                                                    .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(enregistrer)
                                                .addGap(27, 27, 27)
                                                .addComponent(modifier)))
                                        .addGap(35, 35, 35))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(supprimer)
                                        .addGap(104, 104, 104)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(matricule2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                            .addComponent(matiere, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                            .addComponent(choixClasse, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(choixJour, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(choixHeure, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(31, 31, 31))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(enregistrercours)
                                            .addComponent(requetes))
                                        .addGap(112, 112, 112)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chercher))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enregistrer)
                            .addComponent(modifier))
                        .addGap(51, 51, 51)
                        .addComponent(supprimer))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(choixClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(matiere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(choixJour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(choixHeure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(matricule2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(28, 28, 28)
                        .addComponent(enregistrercours)
                        .addGap(9, 9, 9)
                        .addComponent(requetes)))
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void matriculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matriculeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matriculeActionPerformed

    private void nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomActionPerformed
        
    }//GEN-LAST:event_nomActionPerformed

    private void chercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chercherActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableenseignants.getModel();
        String matriculetexte=matricule.getText();
        model.setRowCount(0);
        for(Enseignant enseignant:getEnseignantByMatricule(matriculetexte)){
            Object[] row= {enseignant.getMatricule(),enseignant.getNom(),enseignant.getContact()};
            model.addRow(row);
        }
        if (matriculetexte.equals("")){
            for(Enseignant enseignant:getEnseignants()){
            Object[] row= {enseignant.getMatricule(),enseignant.getNom(),enseignant.getContact()};
            model.addRow(row);
        }
        }
    }//GEN-LAST:event_chercherActionPerformed

    private void contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactActionPerformed

    private void requetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requetesActionPerformed
        mainApp.afficherSeances();        // TODO add your handling code here:
    }//GEN-LAST:event_requetesActionPerformed

    private void enregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enregistrerActionPerformed
        addEnseignant(matricule.getText(),nom.getText(),contact.getText());    
        DefaultTableModel model = (DefaultTableModel) tableenseignants.getModel();
        System.out.println("Ajout de l'enseignant "+ matricule.getText()+ " réussie ");

        model.setRowCount(0);
        for(Enseignant enseignant:getEnseignants()){
            Object[] row= {enseignant.getMatricule(),enseignant.getNom(),enseignant.getContact()};
            model.addRow(row);
        }
        matricule.setText("");
        nom.setText("");
        contact.setText("");

    }//GEN-LAST:event_enregistrerActionPerformed

    private void modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierActionPerformed
        String matriculetexte=matricule.getText();
        updateEnseignant(matricule.getText(), nom.getText(), contact.getText());
        System.out.println("Maj de l'enseignant "+ matriculetexte+ " réussie ");
        DefaultTableModel model = (DefaultTableModel) tableenseignants.getModel();
        model.setRowCount(0);
        for(Enseignant enseignant:getEnseignants()){
            Object[] row= {enseignant.getMatricule(),enseignant.getNom(),enseignant.getContact()};
            model.addRow(row);
        }
        matricule.setText("");
        nom.setText("");
        contact.setText("");
    }//GEN-LAST:event_modifierActionPerformed

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        String matriculetexte=matricule.getText();
        deleteEnseignantByMatricule(matriculetexte);    
        System.out.println("Suppression de l'enseignant "+ matriculetexte+ " réussie ");
        DefaultTableModel model = (DefaultTableModel) tableenseignants.getModel();
        model.setRowCount(0);
        for(Enseignant enseignant:getEnseignants()){
            Object[] row= {enseignant.getMatricule(),enseignant.getNom(),enseignant.getContact()};
            model.addRow(row);
        }
        matricule.setText("");
        
    }//GEN-LAST:event_supprimerActionPerformed

    private void matiereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matiereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matiereActionPerformed

    private void matricule2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matricule2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matricule2ActionPerformed

    private void choixClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixClasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choixClasseActionPerformed

    private void choixJourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixJourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choixJourActionPerformed

    private void choixHeureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixHeureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choixHeureActionPerformed

    private void enregistrercoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enregistrercoursActionPerformed
        DefaultTableModel model = (DefaultTableModel) tablecours.getModel();
        String choixclasse=this.choixClasse.getSelectedItem().toString();
        String choixmatiere=this.matiere.getText();
        String choixheure=this.choixHeure.getSelectedItem().toString();
        String choixjour=this.choixJour.getSelectedItem().toString();
        String choixens=this.matricule2.getText();
        System.out.println("Classe: "+choixclasse+" heure "+choixheure + " jour "+choixjour);
        addCours(choixclasse, choixmatiere, choixjour, choixheure, choixens);
        model.setRowCount(0);
        for(Cours cours:this.getCours()){
            Object[] row= {cours.getClasse(),cours.getMatiere(),cours.getJour(),cours.getHeure(),cours.getEnseignant()};
            model.addRow(row);
        }
        matiere.setText("");
        matricule2.setText("");
    }//GEN-LAST:event_enregistrercoursActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chercher;
    private javax.swing.JComboBox<String> choixClasse;
    private javax.swing.JComboBox<String> choixHeure;
    private javax.swing.JComboBox<String> choixJour;
    private javax.swing.JTextField contact;
    private javax.swing.JButton enregistrer;
    private javax.swing.JButton enregistrercours;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField matiere;
    private javax.swing.JTextField matricule;
    private javax.swing.JTextField matricule2;
    private javax.swing.JButton modifier;
    private javax.swing.JTextField nom;
    private javax.swing.JButton requetes;
    private javax.swing.JButton supprimer;
    private javax.swing.JTable tablecours;
    private javax.swing.JTable tableenseignants;
    // End of variables declaration//GEN-END:variables
}
