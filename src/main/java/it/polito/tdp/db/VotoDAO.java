package it.polito.tdp.db;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import it.polito.tdp.libretto.model.Voto;

//creo dei metodi di accesso ai dati 
//DAO sta per data access object
//le operazioni della classe DAO sono di ipo CRUD (Create, Read, Update, Delete, List, Search)
public class VotoDAO {
	
	public List <Voto> listVoti(){
String jdbcURL = "jdbc:mariadb://localhost/librettovoti?user=root&password=root";
		
		
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			String sql = "SELECT corso, punti, data " + "FROM voto";
			ResultSet res = st.executeQuery(sql);
			
			List<Voto> voti = new ArrayList<Voto>();
			
			while(res.next()) {
				String corso = res.getString("corso");
				int punti = res.getInt("punti");
				LocalDate data = res.getDate("data").toLocalDate();
				

				Voto v = new Voto(corso, punti, data);
				voti.add(v);
			}
			conn.close();
			return voti;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void createVoto(Voto v) {
		String sql = "INSERT INTO `voto` (`corso`, `punti`, `data`) VALUES (?, ?, ?);";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, v.getNomeCorso());
			st.setInt(2, v.getVotoOttenuto());
			//st.setDate(3, new Date(v.getDataEsame()));
			st.executeUpdate();
			
		} catch (SQLException e) {
			
		}
	}
	
	public Voto readVoto (String corso) {
		return null;
		
	}
	
	public List<Voto> searchVotoPuntiMaggiore(int punti){
		return null;
		
	}
	
	
	

}
