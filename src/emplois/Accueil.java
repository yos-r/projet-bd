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
import javax.swing.JOptionPane;
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
//        List<String> heures=getHeure();
        String[] jours={"Lundi","Mardi","Mercredi","Jeudi","Vendredi"};
        String[] lesheures={"8H","9H30Mn","11H","13H","14H30Mn","16H","17H30Mn"};

        String[] lesclasses = classes.toArray(new String[0]);
        DefaultComboBoxModel<String> modelclasse = new DefaultComboBoxModel<>(lesclasses);
        DefaultComboBoxModel<String> modelheure = new DefaultComboBoxModel<>(lesheures);
        DefaultComboBoxModel<String> modeljours = new DefaultComboBoxModel<>(jours);
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
            String req = "SELECT DISTINCT(classe) FROM cours;";
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
            String req = "select * from enseignant;";
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
            String req = "select * from cours;";
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
            String req = "SELECT * FROM enseignant where matricule = ?;";
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
            
            String req = "INSERT INTO enseignant VALUES (?,?,?);";
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
            JOptionPane.showMessageDialog(null, 
                                          "Erreur lors de l'ajout de l'enseignant "+matricule, 
                                          "Erreur ajout enseignant", 
                                          JOptionPane.ERROR_MESSAGE);
        }
        return st;
    }
    public static int existeEnseignant(String matricule){
        int st=0;
        try
        {
            String req = "SELECT * from enseignant WHERE matricule=?;";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement)con.prepareCall(req);
            preparedStatement.setString(1, matricule);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() ){
                return 1;
            }
            else{
                return 0;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return st;
    }
    public static int verifCreneauClasse(String classe, String heure,String jour){
        int res=0;
        try
        {
            String req = "SELECT * from cours WHERE classe=? and heure=? and jour=?";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement)con.prepareCall(req);
            preparedStatement.setString(1, classe);
            preparedStatement.setString(2, heure);
            preparedStatement.setString(3, jour);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() ){
                JOptionPane.showMessageDialog(null, 
                                          "Erreur lors de l'ajout du cours. La classe "+classe+" a un cours "+resultSet.getString(3)+" le "+resultSet.getString(5)+" a "+resultSet.getString(6) , 
                                          "Erreur ajout du cours", 
                                          JOptionPane.ERROR_MESSAGE);
                return 1;
            }
            else{
                return 0;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }
    public static int verifCreneauEnseignant(String enseignant, String heure,String jour){
        int res=0;
        try
        {
            String req = "SELECT * from cours WHERE matricule_ens=? and heure=? and jour=?";
            Connection con=Main.con;
            PreparedStatement preparedStatement = (PreparedStatement)con.prepareCall(req);
            preparedStatement.setString(1, enseignant);
            preparedStatement.setString(2, heure);
            preparedStatement.setString(3, jour);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() ){
                JOptionPane.showMessageDialog(null, 
                                          "Erreur lors de l'ajout du cours. L'enseignant "+enseignant+" a un cours "+resultSet.getString(3)+" le "+resultSet.getString(5)+" a "+resultSet.getString(6) , 
                                          "Erreur ajout du cours", 
                                          JOptionPane.ERROR_MESSAGE);
                return 1;
            }
            else{
                return 0;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }
    public static int addCours(String classe,String matiere, String jour, String heure, String matricule)
    {
        int st=0;
        
        String[] jours={"LUNDI","MARDI","MERCREDI","JEUDI","VENDREDI"};
        List<String> listejours=Arrays.asList(jours);
        int indexjour=listejours.indexOf(jour)+1;
        
        if (existeEnseignant(matricule)==0){
        JOptionPane.showMessageDialog(null, 
                                          "L'enseignant "+matricule+" n'existe pas dans la base", 
                                          "Erreur ajout de cours", 
                                          JOptionPane.ERROR_MESSAGE);
        }
        else if (verifCreneauClasse(classe, heure, jour)==1){
            
        }
        else if(verifCreneauEnseignant(matricule,heure,jour)==1){
        }
        else{
        try
        {
            String req = "INSERT INTO cours(classe,matiere,num_jour,jour,heure,matricule_ens) VALUES (?,?,?,?,?,?);";
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
        }
        return st;
    }
    
    public static int updateEnseignant(String matricule,String nom,String contact)
    {
        int st=0;
        try
        {
            String req = "UPDATE enseignant SET nom = ? , contact =?  WHERE matricule=?;";
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
            String req = "DELETE FROM enseignant where matricule = ?;";
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setText("Matricule");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setText("Nom");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        matricule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matriculeActionPerformed(evt);
            }
        });
        add(matricule, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 110, -1));

        nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomActionPerformed(evt);
            }
        });
        add(nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 190, -1));

        chercher.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        chercher.setText("Chercher");
        chercher.setName(""); // NOI18N
        chercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chercherActionPerformed(evt);
            }
        });
        add(chercher, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setText("Contact");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));

        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });
        add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 190, -1));

        requetes.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        requetes.setText("Requêtes");
        requetes.setName(""); // NOI18N
        requetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requetesActionPerformed(evt);
            }
        });
        add(requetes, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, -1, -1));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 460, 259));

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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 460, 208));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setText("Formulaire de gestion des cours");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 324, -1));

        enregistrer.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        enregistrer.setText("Enregistrer");
        enregistrer.setName(""); // NOI18N
        enregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enregistrerActionPerformed(evt);
            }
        });
        add(enregistrer, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 90, -1));

        modifier.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        modifier.setText("Modifier");
        modifier.setName(""); // NOI18N
        modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierActionPerformed(evt);
            }
        });
        add(modifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        supprimer.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        supprimer.setText("Supprimer");
        supprimer.setName(""); // NOI18N
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });
        add(supprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 90, 20));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel6.setText("Classe");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel7.setText("Matière");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel8.setText("Jour");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel9.setText("Heure");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        matiere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matiereActionPerformed(evt);
            }
        });
        add(matiere, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 148, -1));

        matricule2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matricule2ActionPerformed(evt);
            }
        });
        add(matricule2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 148, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel10.setText("Matricule Ens.");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, -1, -1));

        choixClasse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixClasseActionPerformed(evt);
            }
        });
        add(choixClasse, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 148, -1));

        choixJour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixJour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixJourActionPerformed(evt);
            }
        });
        add(choixJour, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 148, -1));

        choixHeure.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choixHeure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixHeureActionPerformed(evt);
            }
        });
        add(choixHeure, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 148, -1));

        enregistrercours.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        enregistrercours.setText("Enregistrer");
        enregistrercours.setName(""); // NOI18N
        enregistrercours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enregistrercoursActionPerformed(evt);
            }
        });
        add(enregistrercours, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 550, -1, -1));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setText("Formulaire d'enregistrement des enseignants");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 33, -1, -1));
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
        DefaultTableModel model2 = (DefaultTableModel) tablecours.getModel();

        model.setRowCount(0);
        model2.setRowCount(0);
        for(Enseignant enseignant:getEnseignants()){
            Object[] row= {enseignant.getMatricule(),enseignant.getNom(),enseignant.getContact()};
            model.addRow(row);
        }
        for(Cours cours:this.getCours()){
            Object[] row= {cours.getClasse(),cours.getMatiere(),cours.getJour(),cours.getHeure(),cours.getEnseignant()};
            model2.addRow(row);
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
    private javax.swing.JPanel jPanel1;
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
