package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.IFornecedores;
import br.com.farmacia.DAO.IProdutoDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean (name = "MBProdutos")
@ViewScoped
public class ProdutosBean {

	private Produtos produto;
	private ArrayList<Produtos>listaProdutos;
	private ArrayList<Produtos>listaProdutosFiltrados;
	
	private ArrayList<Fornecedores>listaFornecedors; //Usado para alimentar o comboBox no dialogo de Novo Produto
	
	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public ArrayList<Produtos> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(ArrayList<Produtos> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public ArrayList<Produtos> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(ArrayList<Produtos> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public ArrayList<Fornecedores> getListaFornecedors() {
		return listaFornecedors;
	}

	public void setListaFornecedors(ArrayList<Fornecedores> listaFornecedors) {
		this.listaFornecedors = listaFornecedors;
	}

	@PostConstruct
	public void prepararPesquisa(){
		try {
			IProdutoDAO produtosDAO = new ProdutoDAO();
			listaProdutos = produtosDAO.listarProdutos();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void prepararNovo(){
		
		try {
			produto = new Produtos();
			IFornecedores fornecedoroDAO = new FornecedoresDAO();
			listaFornecedors = fornecedoroDAO.listarFornecedores();
			
		} catch (SQLException e) {
			JSFUtil.mensagemErro("Erro ao carregar Fornecedores!\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void novoProduto(){
		try {
			IProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produto);
			prepararPesquisa();
			JSFUtil.mensagemSucesso("Produto salvo com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.mensagemErro("Erro ao Salvar produto!\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEditar(){
		
		try {
			produto = new Produtos();
			IFornecedores fornecedoroDAO = new FornecedoresDAO();
			listaFornecedors = fornecedoroDAO.listarFornecedores();
			
		} catch (SQLException e) {
			JSFUtil.mensagemErro("Erro ao carregar Fornecedores!\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void editarProduto(){
		try {
			IProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.alterar(produto);
			prepararPesquisa();
			JSFUtil.mensagemSucesso("Produto Alterado com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.mensagemErro("Erro ao Alterar Produto!\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void excluirProduto(){
		try {
			IProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			prepararPesquisa();
			JSFUtil.mensagemSucesso("Produto excluido com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.mensagemErro("Erro ao Excluir Produto!\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
}
