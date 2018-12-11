package br.com.farmacia.connection;


import java.sql.DriverManager;
import java.sql.SQLException;


import javax.swing.JOptionPane;
import java.sql.Connection;
//import com.mysql.jdbc.Connection;

public class Conexao {

	public Conexao () {    } //Possibilita instancias
	public static Connection con = null;
	 
	public static Connection getConexao() {
	
		try {
		  Class.forName("com.mysql.jdbc.Driver");
		  con =  DriverManager.getConnection("jdbc:mysql://localhost/farmacia","root","");
		  System.out.println("conectado");
		  return con;
		
		} catch (ClassNotFoundException ex) {
		    JOptionPane.showMessageDialog(null, "O driver expecificado nao foi encontrado. " + ex);
		    return null;
		} catch(SQLException e) {
		    JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados. "+e);
		    return null;
		}
	}
	
	public static Connection getConexaoCep() {
		
		try {
		  Class.forName("com.mysql.jdbc.Driver");
		  con =  DriverManager.getConnection("jdbc:mysql://localhost/cep","root","");
		  System.out.println("conectado");
		  return con;
		
		} catch (ClassNotFoundException ex) {
		    JOptionPane.showMessageDialog(null, "O driver expecificado nao foi encontrado. " + ex);
		    return null;
		} catch(SQLException e) {
		    JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados. "+e);
		    return null;
		}
	}
	
	public static Connection conectar(){
		return Conexao.getConexao();
	}
	
    public static boolean fecharConexao() {
	   
        try {
            con.close();
            System.out.println("Desconectado");
            return true;
 
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erro fechar conexão! "+e);
            return false;
        }
     } 

}