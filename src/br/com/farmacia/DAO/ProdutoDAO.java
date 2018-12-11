package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.connection.Conexao;

import br.com.farmacia.domain.Produtos;

public class ProdutoDAO implements IProdutoDAO{

	public static ArrayList<Produtos> LISTA_PRODUTOS_BD = new ArrayList<Produtos>();
	private Connection connection = null;
	
	@Override
	public void salvar(Produtos produto) throws SQLException {
		connection = Conexao.conectar();
		String sql = "INSERT INTO produtos (descricao, quantidade, preco, fornecedores_codigo) VALUES(?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, produto.getDescricao());
		pstmt.setLong(2, produto.getQuantidade());
		pstmt.setDouble(3, produto.getPreco());
		pstmt.setLong(4, produto.getFornecedor().getCodigo());
		
		pstmt.executeUpdate();
		pstmt.close();
		Conexao.fecharConexao();
		
	}

	@Override
	public void excluir(Produtos produto) throws SQLException {
		connection = Conexao.conectar();
		String sql = "UPDATE produtos SET eliminado = 'S' WHERE codigo = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, produto.getCodigo());

		pstmt.executeUpdate();
		pstmt.close();
		Conexao.fecharConexao();
		
	}

	@Override
	public void recuperar(Produtos produto) throws SQLException {
		connection = Conexao.conectar();
		String sql = "UPDATE produtos SET eliminado = 'N' WHERE codigo = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, produto.getCodigo());

		pstmt.executeUpdate();
		pstmt.close();
		Conexao.fecharConexao();
		
	}

	@Override
	public void alterar(Produtos produto) throws SQLException {
		connection = Conexao.conectar();
		String sql = "UPDATE produtos SET descricao = ?, quantidade = ?, preco = ?, fornecedores_codigo = ? "+
		"WHERE codigo = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, produto.getDescricao());
		pstmt.setInt(2, produto.getQuantidade());
		pstmt.setDouble(3, produto.getPreco());
		pstmt.setLong(4, produto.getFornecedor().getCodigo());
		pstmt.setLong(5, produto.getCodigo());

		pstmt.executeUpdate();
		pstmt.close();
		Conexao.fecharConexao();
		
	}

	@Override
	public Produtos buscarPorCodigo(Produtos produto) throws SQLException {
		Produtos produtoDaConsulta = new Produtos();
		connection = Conexao.conectar();
		String sql = "SELECT codigo, descricao, quantidade, preco, fornecedores_codigo, eliminado FROM produtos WHERE codigo = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		pstmt.setLong(1, produto.getCodigo());
		ResultSet resultSet = pstmt.executeQuery();
		
		while(resultSet.next()){
			produtoDaConsulta.setCodigo(resultSet.getLong("codigo"));
			produtoDaConsulta.setDescricao(resultSet.getString("descricao"));
			produtoDaConsulta.setQuantidade(resultSet.getInt("quantidade"));
			produtoDaConsulta.setPreco(resultSet.getDouble("preco"));
			produtoDaConsulta.getFornecedor().setCodigo(resultSet.getLong("fornecedores_codigo"));
			produtoDaConsulta.setEliminado(resultSet.getString("eliminado"));
		}
		
		pstmt.close();
		Conexao.fecharConexao();
		
		return produtoDaConsulta;
	}

	@Override
	public ArrayList<Produtos> buscarPorDescricao(Produtos produto) throws SQLException {
		LISTA_PRODUTOS_BD.clear();
		
		connection = Conexao.conectar();
		//String sql = "SELECT codigo, descricao, quantidade, preco, fornecedores_codigo, eliminado FROM produtos WHERE descricao like ? ORDER BY descricao ASC";
		String sql = "SELECT p.codigo, p.descricao, p.quantidade, p.preco, p.eliminado, f.codigo, f.nome "+
				"FROM produtos p INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo AND descricao LIKE ?"+
				"ORDER BY p.descricao ASC";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		pstmt.setString(1, "%"+produto.getDescricao()+"%");
		ResultSet resultSet = pstmt.executeQuery();
		
		while(resultSet.next()){
			Produtos produtoDaConsulta = new Produtos();
			produtoDaConsulta.setCodigo(resultSet.getLong("p.codigo"));
			produtoDaConsulta.setDescricao(resultSet.getString("p.descricao"));
			produtoDaConsulta.setQuantidade(resultSet.getInt("p.quantidade"));
			produtoDaConsulta.setPreco(resultSet.getDouble("p.preco"));
			produtoDaConsulta.setEliminado(resultSet.getString("p.eliminado"));
			produtoDaConsulta.getFornecedor().setCodigo(resultSet.getLong("f.codigo"));
			produtoDaConsulta.getFornecedor().setNome(resultSet.getString("f.nome"));
			
			LISTA_PRODUTOS_BD.add(produtoDaConsulta);
		}
		
		pstmt.close();
		Conexao.fecharConexao();
		
		return LISTA_PRODUTOS_BD;
	}

	@Override
	public ArrayList<Produtos> listarProdutos() throws SQLException {
		LISTA_PRODUTOS_BD.clear();
		
		connection = Conexao.conectar();
		String sql = "SELECT p.codigo, p.descricao, p.quantidade, p.preco, p.eliminado, f.codigo, f.nome "+
				"FROM produtos p INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo "+
				"ORDER BY p.descricao ASC";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		ResultSet resultSet = pstmt.executeQuery();
		
		while(resultSet.next()){
			Produtos produtoDaConsulta = new Produtos();
			produtoDaConsulta.setCodigo(resultSet.getLong("p.codigo"));
			produtoDaConsulta.setDescricao(resultSet.getString("p.descricao"));
			produtoDaConsulta.setQuantidade(resultSet.getInt("p.quantidade"));
			produtoDaConsulta.setPreco(resultSet.getDouble("p.preco"));
			produtoDaConsulta.setEliminado(resultSet.getString("p.eliminado"));
			produtoDaConsulta.getFornecedor().setCodigo(resultSet.getLong("f.codigo"));
			produtoDaConsulta.getFornecedor().setNome(resultSet.getString("f.nome"));
			
			
			LISTA_PRODUTOS_BD.add(produtoDaConsulta);
		}
		
		pstmt.close();
		Conexao.fecharConexao();
		
		return LISTA_PRODUTOS_BD;
	}

	public static void main(String[] args) {
		IProdutoDAO produtoDAO = new ProdutoDAO();

		Produtos p = new Produtos();
		//p.setCodigo(1L);
		p.setDescricao("gen");
		//p.setQuantidade(4);
		//p.setPreco(2.00);
		//p.getFornecedor().setCodigo(5L);

		try {
			//produtoDAO.salvar(p);
			//produtoDAO.alterar(p);
			//produtoDAO.excluir(p);
			//produtoDAO.recuperar(p);
			//p = produtoDAO.buscarPorCodigo(p);
			//System.out.println("Sucesso " + ", " + p.getCodigo() + ", " + p.getDescricao() + ", " + p.getEliminado());
			for(Produtos pDaConsulta : produtoDAO.buscarPorDescricao(p)){
				System.out.println("Sucesso " + pDaConsulta.getCodigo()  + ", " +
						pDaConsulta.getDescricao() + " -> Fornecedor: " + pDaConsulta.getFornecedor().getCodigo() + ", " +
						pDaConsulta.getFornecedor().getNome() + ", " + pDaConsulta.getEliminado());
			}
			/*for(Produtos pDaConsulta : produtoDAO.listarProdutos()){
				System.out.println("Sucesso " + pDaConsulta.getCodigo()  + ", " +
						pDaConsulta.getDescricao() + ", " + pDaConsulta.getFornecedor().getCodigo() + ", " +
						pDaConsulta.getFornecedor().getNome() + ", " + pDaConsulta.getEliminado());
			}*/
			System.out.println("Sucesso");
		} catch (SQLException e) {
			System.out.println("Erro");
			e.printStackTrace();
		}
	}
}
