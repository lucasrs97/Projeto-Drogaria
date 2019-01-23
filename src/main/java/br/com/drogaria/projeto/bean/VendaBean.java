package br.com.drogaria.projeto.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.projeto.dao.FuncionarioDAO;
import br.com.drogaria.projeto.dao.ItemDAO;
import br.com.drogaria.projeto.dao.ProdutoDAO;
import br.com.drogaria.projeto.dao.VendaDAO;
import br.com.drogaria.projeto.domain.Funcionario;
import br.com.drogaria.projeto.domain.Item;
import br.com.drogaria.projeto.domain.Produto;
import br.com.drogaria.projeto.domain.Venda;
import br.com.drogaria.projeto.filter.VendaFilter;
import br.com.drogaria.projeto.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;

	private Venda vendaCadastro;
	private List<Item> listaItens;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private VendaFilter filtro;
	private List<Venda> listaVendasFiltradas;
	
	public List<Produto> getListaProdutos() {
		if (listaItens == null) {
			listaItens = new ArrayList<>();
		}
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

	public List<Item> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}
	
	public Venda getVendaCadastro() {
		if(vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setTotal(new BigDecimal("0.00"));
			vendaCadastro.setQuantidade(0);
		}
		return vendaCadastro;
	}
	
	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}
	
	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}
	
	public VendaFilter getFiltro() {
		if(filtro == null) {
			filtro = new VendaFilter();
		}
		return filtro;
	}
	
	public void setFiltro(VendaFilter filtro) {
		this.filtro = filtro;
	}
	
	public List<Venda> getListaVendasFiltradas() {
		return listaVendasFiltradas;
	}
	
	public void setListaVendasFiltradas(List<Venda> listaVendasFiltradas) {
		this.listaVendasFiltradas = listaVendasFiltradas;
	}

	public void carregarProdutos() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os produtos: " + e.getMessage());
		}
	}

	public void adicionar(Produto produtoSelecionado) {
		int posicaoEncontrada = -1;

		for (int posicao = 0; posicao < listaItens.size() && posicaoEncontrada < 0; posicao++) {
			Item itemTemporario = listaItens.get(posicao);

			if (itemTemporario.getProduto().equals(produtoSelecionado)) {
				posicaoEncontrada = posicao;
			}
		}

		Item item = new Item();
		item.setProduto(produtoSelecionado);

		if (posicaoEncontrada < 0) {
			item.setQuantidade(1);
			item.setValor(produtoSelecionado.getPreco());
			listaItens.add(item);
		} else {
			Item itemTemporario = listaItens.get(posicaoEncontrada);
			item.setQuantidade(itemTemporario.getQuantidade()+1);
			
			item.setValor(produtoSelecionado.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			listaItens.set(posicaoEncontrada, item);
		}
		
		vendaCadastro.setTotal(vendaCadastro.getTotal().add(produtoSelecionado.getPreco()));
		vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() + 1);
	}
	
	public void remover(Item itemSelecionado) {
		int posicaoEncontrada = -1;

		for (int posicao = 0; posicao < listaItens.size() && posicaoEncontrada < 0; posicao++) {
			Item itemTemporario = listaItens.get(posicao);

			if (itemTemporario.getProduto().equals(itemSelecionado.getProduto())) {
				posicaoEncontrada = posicao;
			}
		}
		
		if(posicaoEncontrada > -1) {
			listaItens.remove(posicaoEncontrada);
			vendaCadastro.setTotal(vendaCadastro.getTotal().subtract(itemSelecionado.getValor()));
			vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() - itemSelecionado.getQuantidade());
		}
	}
	
	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());
	
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodigo());
		vendaCadastro.setFuncionario(funcionario);
	}
	
	public void salvar() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			Long codigoVenda = vendaDAO.salvar(vendaCadastro);
			Venda vendaFK = vendaDAO.buscarPorCodigo(codigoVenda);
			
			for (Item item : listaItens) {
				item.setVenda(vendaFK);
				
				ItemDAO itemDAO = new ItemDAO();
				itemDAO.salvar(item);
			}
			
			vendaCadastro = new Venda();
			vendaCadastro.setTotal(new BigDecimal("0.00"));
			
			listaItens = new ArrayList<Item>();
			FacesUtil.adicionarMsgInfo("Venda salva com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar salvar a venda: " + e.getMessage());
		}
	}
	
	public void pesquisarVendas() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			listaVendasFiltradas = vendaDAO.buscar(filtro);
			
			for (Venda venda : listaVendasFiltradas) {
				System.out.println(venda);
			}
			
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar pesquisar as vendas: " + e.getMessage());
		}
	}

}
