package br.com.drogaria.projeto.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.projeto.domain.Item;
import br.com.drogaria.projeto.domain.Produto;
import br.com.drogaria.projeto.domain.Venda;

public class ItemDAOTest {
	@Test
	@Ignore
	public void salvar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1L);
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(1L);
		
		Item item = new Item();
		item.setProduto(produto);
		item.setVenda(venda);
		item.setQuantidade(450);
		item.setValor(new BigDecimal(80.90));
		
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.salvar(item);
	}
	
	@Test
	@Ignore
	public void listar() {
		ItemDAO itemDAO = new ItemDAO();
		
		List<Item> itens = itemDAO.listar();
		
		System.out.println(itens);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ItemDAO itemDAO = new ItemDAO();
		Item item = itemDAO.buscarPorCodigo(2L);
		
		System.out.println(item);
	}
	
	@Test
	@Ignore
	public void excluir() {
		ItemDAO itemDAO = new ItemDAO();
		
		Item item = itemDAO.buscarPorCodigo(1L);
		
		if (item != null) {
			itemDAO.excluir(item);
		} else {
			System.out.println("Este código não está cadastrado.");
		}
	}
	
	@Test
	public void editar() {
		ItemDAO itemDAO = new ItemDAO();
		Item item = itemDAO.buscarPorCodigo(2L);
		
		item.setQuantidade(5);
		item.setValor(new BigDecimal(250.00));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1L);
		item.setProduto(produto);
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(1L);
		item.setVenda(venda);
		
		itemDAO.editar(item);
	}
	
	
}
