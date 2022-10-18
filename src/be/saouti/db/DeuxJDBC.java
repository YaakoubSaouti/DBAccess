package be.saouti.db;


import java.sql.*;
import javax.swing.JOptionPane;

public class DeuxJDBC {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		try {
			Class.forName ("net.ucanaccess.jdbc.UcanaccessDriver");
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Classe de driver introuvable" +ex.getMessage());
		}
		Connection connec = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			String url = "jdbc:ucanaccess://./MuscleTou.accdb";
			connec = DriverManager.getConnection(url);
			//DriverManager.getConnection("jdbc:oracle:thin:@char-oracle11.condorcet.be:1521/")
			String requete = "SELECT NomProduit,PrixVente FROM Produits";
			stmt = connec.createStatement();
			res = stmt.executeQuery(requete);
			String nom;
			float prix;
			while(res.next()) {
				nom = res.getString(1);
				prix = res.getFloat(2);
				System.out.println(prix+" "+nom);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erreur SQL" +ex.getMessage());
		}finally {
			try {
				if(res != null) res.close();
				if(stmt != null) stmt.close();
				if(connec!= null) connec.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
