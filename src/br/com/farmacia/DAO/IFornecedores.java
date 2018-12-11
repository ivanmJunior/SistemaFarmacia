package br.com.farmacia.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;

public interface IFornecedores {

	void salvar(Fornecedores fornecedor) throws SQLException;
	void excluir(Fornecedores fornecedor) throws SQLException;
	void recuperar(Fornecedores fornecedor) throws SQLException;
	void alterar(Fornecedores fornecedor) throws SQLException;
	Fornecedores buscarPorCodigo(Fornecedores fornecedor) throws SQLException;
	ArrayList<Fornecedores> buscarPorNome(Fornecedores fornecedor) throws SQLException;
	ArrayList<Fornecedores> listarFornecedores() throws SQLException;
}
