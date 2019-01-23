package br.com.drogaria.projeto.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.projeto.domain.Fabricante;
import br.com.drogaria.projeto.domain.Produto;

public class ProdutoDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(4L);
		
		Produto produto = new Produto();
		produto.setDescricao("Produto B");
		produto.setPreco(new BigDecimal(10.90D));
		produto.setQuantidade(20);
		produto.setFabricante(fabricante);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
	
	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		List<Produto> produtos = produtoDAO.listar();
		
		System.out.println(produtos);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1L);
		
		System.out.println(produto);
	}
	
	@Test
	@Ignore
	public void excluir() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		Produto produto = produtoDAO.buscarPorCodigo(2L);
		
		if (produto != null) {
			produtoDAO.excluir(produto);
		} else {
			System.out.println("Este código não está cadastrado.");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1L);
		
		produto.setDescricao("Produto A.");
		produto.setPreco(new BigDecimal(11.80D));
		produto.setQuantidade(50);
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(1L);
		produto.setFabricante(fabricante);
		
		produtoDAO.editar(produto);
	}

}
