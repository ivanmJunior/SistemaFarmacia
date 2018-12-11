package br.com.farmacia.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Produtos;

public interface IProdutoDAO {

	void salvar(Produtos produto) throws SQLException;
	void excluir(Produtos produto) throws SQLException;
	void recuperar(Produtos produto) throws SQLException;
	void alterar(Produtos produto) throws SQLException;
	Produtos buscarPorCodigo(Produtos produto) throws SQLException;
	ArrayList<Produtos> buscarPorDescricao(Produtos produto) throws SQLException;
	ArrayList<Produtos> listarProdutos() throws SQLException;
}
