package dao;

import model.Planta; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlantaDAO extends DAO{
	public PlantaDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Planta Planta) {
		boolean status = false;
		try {
			String sql = "INSERT INTO Planta (descricao, preco, idade, nome) "
		               + "VALUES ('" + Planta.getDescricao() + "', "
		               + Planta.getPreco() + ", " + Planta.getIdade() + ", '"+Planta.getNome()+"');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Planta get(int id) {
		Planta Planta = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Planta WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 Planta = new Planta(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), 
	        			 (float)rs.getDouble("preco"), rs.getInt("idade"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Planta;
	}
	
	
	public List<Planta> get() {
		return get("");
	}

	
	public List<Planta> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Planta> getOrderByDescricao() {
		return get("descricao");		
	}
	
	
	public List<Planta> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Planta> get(String orderBy) {
		List<Planta> Plantas = new ArrayList<Planta>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Planta" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Planta p = 	new Planta(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), 
	        			 (float)rs.getDouble("preco"), rs.getInt("idade"));
	            Plantas.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Plantas;
	}
	
	
	public boolean update(Planta Planta) {
		boolean status = false;
		try {  
			String sql = "UPDATE Planta SET descricao = '" + Planta.getDescricao() + "', "
					   + "preco = " + Planta.getPreco() + ", " 
					   + "nome = " + Planta.getNome() + ","
					   + "idade = " + Planta.getIdade();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Planta WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
