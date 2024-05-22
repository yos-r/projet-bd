package emplois;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JPanel;

public class Main extends javax.swing.JFrame {
    // crucial elements
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Accueil accueilPanel;
    private Seances seancesPanel;
  
    static Connection con = null; 
    public static Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Driver class
            String url = "jdbc:mysql://localhost:3306/edt"; //jdbc: API, mysql is the database, localhost: server, 3306 port
            con = DriverManager.getConnection(url,"root","system");
            System.out.println("Connexion réussie");
        }catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }
    public Main() {
        initComponents();
        con=getConnection();
        setTitle("Gestion des emplois du temps");
        this.setSize(820,700);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        getContentPane().add(mainPanel);
        accueilPanel = new Accueil(this); 
        seancesPanel = new Seances(this); 

        // Add home and settings panels to the main panel
        mainPanel.add(accueilPanel, "accueilPanel");
        mainPanel.add(seancesPanel, "seancesPanel");

        // Show the home panel by default
        cardLayout.show(mainPanel, "homePanel");
    }
    public void afficherSeances() {
        cardLayout.show(mainPanel, "seancesPanel");
    }

    // Method to switch to the home panel
    public void afficherAccueil() {
        cardLayout.show(mainPanel, "accueilPanel");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
