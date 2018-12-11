package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.IFornecedores;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean (name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private Fornecedores fornecedor;
	private ArrayList<Fornecedores>listaFornecedores;
	private ArrayList<Fornecedores>listaFornecedoresFiltrados;

	public ArrayList<Fornecedores> getListaFornecedores() {
		return listaFornecedores;
	}

	public void setListaFornecedores(ArrayList<Fornecedores> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}
	
	public Fornecedores getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedores fornecedor) {
		this.fornecedor = fornecedor;
	}

	public ArrayList<Fornecedores> getListaFornecedoresFiltrados() {
		return listaFornecedoresFiltrados;
	}

	public void setListaFornecedoresFiltrados(ArrayList<Fornecedores> listaFornecedoresFiltrados) {
		this.listaFornecedoresFiltrados = listaFornecedoresFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa(){
		try {
			IFornecedores fornecedoresDAO = new FornecedoresDAO();
			listaFornecedores = fornecedoresDAO.listarFornecedores();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void prepararNovo(){
		fornecedor = new Fornecedores();
	}
	
	public void novoFornecedor(){
		try {
			IFornecedores fornecedoresDAO = new FornecedoresDAO();
			fornecedoresDAO.salvar(fornecedor);
			prepararPesquisa();
			JSFUtil.mensagemSucesso("Fornecedor salvo com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.mensagemErro("Erro ao Salvar Fornecedor!\n"+e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluirFornecedor(){
		try {
			IFornecedores fornecedoresDAO = new FornecedoresDAO();
			fornecedoresDAO.excluir(fornecedor);
			prepararPesquisa();
			JSFUtil.mensagemSucesso("Excluido com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.mensagemErro("Erro ao Excluir!\nFornecedor associado a produtos não pode ser excluido!"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void editarFornecedor(){
		try {
			IFornecedores fornecedoresDAO = new FornecedoresDAO();
			fornecedoresDAO.alterar(fornecedor);
			prepararPesquisa();
			JSFUtil.mensagemSucesso("Alterado com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.mensagemErro("Erro ao Alterar!\n"+e.getMessage());
			e.printStackTrace();
		}
	}
}
