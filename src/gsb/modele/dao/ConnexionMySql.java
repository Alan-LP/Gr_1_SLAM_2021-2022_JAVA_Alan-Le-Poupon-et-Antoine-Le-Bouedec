package gsb.modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnexionMySql
{
	
	static Connection cnx;
	
	public ConnexionMySql()
	{
		cnx = null;
	}
	
	/**
	 * methode qui permet la connexion à la base de données
	 */
	public static void connecterBd(){
		String url = "jdbc:mysql://192.171.1.13:3306/GSB"; // url : chaine de connexion
		//String url = "jdbc:mysql://localhost/GSB";
		// try permet d'essayer de lancer la connexion
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnx = DriverManager.getConnection(url,"admindb","password"); 
		} 
		// si la connexion echoue un message d'erreur est affiché
        catch(Exception e) 
		{
        	System.out.println("Echec lors de la connexion");
        } 

	}

	
	/**
	 * @param laRequete requête SQL de type SELECT
	 * @return un curseur qui contient les lignes obtenues lors de l'exécution de la requête, null sinon
	 */
	public static ResultSet execReqSelection(String laRequete)
	{ 
		connecterBd();
		ResultSet resultatReq = null;
		try
		{
				Statement requete = cnx.createStatement(); 
				resultatReq = requete.executeQuery(laRequete); 
		} 
		catch(Exception e)
		{
			System.out.println("Erreur requete : " + laRequete); 
		}
		return resultatReq;
	}
	
	/**
	 * @param laRequete requête SQL de type INSERT, UPDATE ou DELETE
	 * @return 1 si la MAJ s'est bien déroulée, 0 sinon
	 */
	public static int execReqMaj(String laRequete)
	{
		connecterBd();
		int nbMaj = 0;
		try
		{
			Statement s = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        nbMaj = s.executeUpdate(laRequete);
	        s.close();
        }
		catch (Exception er)
		{
			er.printStackTrace(); 
			System.out.println("echec requête : " + laRequete); 
		}
		fermerConnexionBd();
		return nbMaj;       
	}
	
	/**
	 * attention : tant que la connexion n'est pas fermée, 
	 * les MAJ ne sont pas effectives, on reste en mode déconnecté
	 */
	public static void fermerConnexionBd()
	{
		try
		{
			cnx.close();
		}
		catch(Exception e)
		{
			System.out.println("Erreur sur fermeture connexion");
		} 
	}

}
