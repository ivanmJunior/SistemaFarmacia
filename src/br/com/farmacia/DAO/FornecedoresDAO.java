package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.connection.Conexao;
import br.com.farmacia.domain.Fornecedores;

public class FornecedoresDAO implements IFornecedores {

	public static ArrayList<Fornecedores> LISTA_FORNECEDORES_BD = new ArrayList<Fornecedores>();
	private Connection connection = null;

	@Override
	public void salvar(Fornecedores fornecedor) throws SQLException {
		connection = Conexao.conectar();

		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO fornecedores (nome) VALUES(?)");
		pstmt.setString(1, fornecedor.getNome());

		pstmt.executeUpdate();
		pstmt.close();
		Conexao.fecharConexao();
	}
	
	@Override
	public void alterar(Fornecedores fornecedor) throws SQLException {
		connection = Conexao.conectar();
		String sql = "UPDATE fornecedores SET nome = ? WHERE codigo = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, fornecedor.getNome());
		pstmt.setLong(2, fornecedor.getCodigo());

		pstmt.executeUpdate();
		pstmt.close();
		Conexao.fecharConexao();
	}
	
	@Override
	public void excluir(Fornecedores fornecedor) throws SQLException {
		connection = Conexao.conectar();
		String sql = "UPDATE fornecedores SET eliminado = 'S' WHERE codigo = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, fornecedor.getCodigo());

		pstmt.executeUpdate();
		pstmt.close();
		Conexao.fecharConexao();
	}
	
	@Override
	public void recuperar(Fornecedores fornecedor) throws SQLException {
		connection = Conexao.conectar();
		String sql = "UPDATE fornecedores SET eliminado = 'N' WHERE codigo = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, fornecedor.getCodigo());

		pstmt.executeUpdate();
		pstmt.close();
		Conexao.fecharConexao();
		
	}
	
	@Override
	public Fornecedores buscarPorCodigo(Fornecedores fornecedor) throws SQLException {
		Fornecedores fornecedorDaConsulta = new Fornecedores();
		connection = Conexao.conectar();
		String sql = "SELECT codigo, nome, eliminado FROM fornecedores WHERE codigo = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		pstmt.setLong(1, fornecedor.getCodigo());
		ResultSet resultSet = pstmt.executeQuery();
		
		while(resultSet.next()){
			fornecedorDaConsulta.setCodigo(resultSet.getLong("codigo"));
			fornecedorDaConsulta.setNome(resultSet.getString("nome"));
			fornecedorDaConsulta.setEliminado(resultSet.getString("eliminado"));
		}
		
		pstmt.close();
		Conexao.fecharConexao();
		
		return fornecedorDaConsulta;
	}

	@Override
	public ArrayList<Fornecedores> buscarPorNome(Fornecedores fornecedor) throws SQLException {
		LISTA_FORNECEDORES_BD.clear();
		
		connection = Conexao.conectar();
		String sql = "SELECT codigo, nome, eliminado FROM fornecedores WHERE nome like ? ORDER BY nome ASC";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		pstmt.setString(1, "%"+fornecedor.getNome()+"%");
		ResultSet resultSet = pstmt.executeQuery();
		
		while(resultSet.next()){
			Fornecedores fornecedorDaConsulta = new Fornecedores();
			fornecedorDaConsulta.setCodigo(resultSet.getLong("codigo"));
			fornecedorDaConsulta.setNome(resultSet.getString("nome"));
			fornecedorDaConsulta.setEliminado(resultSet.getString("eliminado"));
			
			LISTA_FORNECEDORES_BD.add(fornecedorDaConsulta);
		}
		
		pstmt.close();
		Conexao.fecharConexao();
		
		return LISTA_FORNECEDORES_BD;
	}
	
	@Override
	public ArrayList<Fornecedores> listarFornecedores() throws SQLException {
		LISTA_FORNECEDORES_BD.clear();
		
		connection = Conexao.conectar();
		String sql = "SELECT codigo, nome, eliminado FROM fornecedores order by nome ASC";
		PreparedStatement pstmt = connection.prepareStatement(sql);

		ResultSet resultSet = pstmt.executeQuery();
		
		while(resultSet.next()){
			Fornecedores fornecedorDaConsulta = new Fornecedores();
			fornecedorDaConsulta.setCodigo(resultSet.getLong("codigo"));
			fornecedorDaConsulta.setNome(resultSet.getString("nome"));
			fornecedorDaConsulta.setEliminado(resultSet.getString("eliminado"));
			
			LISTA_FORNECEDORES_BD.add(fornecedorDaConsulta);
		}
		
		pstmt.close();
		Conexao.fecharConexao();
		
		return LISTA_FORNECEDORES_BD;
	}

	public static void main(String[] args) {
		IFornecedores fornecedoresDAO = new FornecedoresDAO();

		Fornecedores f = new Fornecedores();
		f.setNome("Laboratórios");
		//f.setCodigo(1L);

		try {
			//fornecedoresDAO.salvar(f);
			//fornecedoresDAO.alterar(f);
			//fornecedoresDAO.excluir(f);
			//fornecedoresDAO.recuperar(f);
			//f = fornecedoresDAO.buscarPorCodigo(f);
			//System.out.println("Sucesso " + ", " + f.getCodigo() + ", " + f.getNome() + ", " + f.getEliminado());
			for(Fornecedores fornecedorDaConsulta : fornecedoresDAO.buscarPorNome(f)){
				System.out.println("Sucesso " + fornecedorDaConsulta.getNome());
			}
			//System.out.println("Sucesso");
		} catch (SQLException e) {
			System.out.println("Erro");
			e.printStackTrace();
		}
	}


}
