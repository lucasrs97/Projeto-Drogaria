package br.com.drogaria.projeto.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.projeto.dao.FabricanteDAO;
import br.com.drogaria.projeto.dao.ProdutoDAO;
import br.com.drogaria.projeto.domain.Fabricante;
import br.com.drogaria.projeto.domain.Produto;
import br.com.drogaria.projeto.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private Produto produtoCadastro;
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	private String acao;
	private Long codigoProduto;
	private List<Fabricante> listaFabricantes;

	public Produto getProdutoCadastro() {
		return produtoCadastro;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	public void novo() {
		produtoCadastro = new Produto();
	}

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);

			produtoCadastro = new Produto();

			FacesUtil.adicionarMsgInfo("Produto salvo com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar incluir um produto: " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();			
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os produtos: " + e.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {			
			if(codigoProduto != null) {	
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoCadastro = produtoDAO.buscarPorCodigo(codigoProduto);
			} else {
				produtoCadastro = new Produto();
			}
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricantes = fabricanteDAO.listar();
			
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar obter os dados do produto. " + e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produtoCadastro);

			FacesUtil.adicionarMsgInfo("Produto excluído com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluír um produto: " + e.getMessage());
		}
	}
	
	public void editar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);

			FacesUtil.adicionarMsgInfo("Produto editado com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o produto. " + e.getMessage());
		}
	}
	
}
