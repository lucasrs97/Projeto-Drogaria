package br.com.drogaria.projeto.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.projeto.domain.Fabricante;

public class FabricanteDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Fabricante f1 = new Fabricante();
		f1.setDescricao("Descrição A");

		Fabricante f2 = new Fabricante();
		f2.setDescricao("Descrição B");

		Fabricante f3 = new Fabricante();
		f3.setDescricao("Descrição C");

		Fabricante f4 = new Fabricante();
		f4.setDescricao("Descrição D");

		FabricanteDAO fabricanteDAO = new FabricanteDAO();

		fabricanteDAO.salvar(f1);
		fabricanteDAO.salvar(f2);
		fabricanteDAO.salvar(f3);
		fabricanteDAO.salvar(f4);
	}

	@Test
	@Ignore
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.listar();

		for (Fabricante fabricante : fabricantes) {
			System.out.println("Fabricantes encontrados: " + fabricante);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();

		Fabricante f1 = fabricanteDAO.buscarPorCodigo(9L);

		Fabricante f2 = fabricanteDAO.buscarPorCodigo(1L);

		System.out.println("Resultado da busca por código:");
		System.out.println(f1);
		System.out.println(f2);
	}

	@Test
	@Ignore
	public void excluir() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();

		Fabricante f1 = fabricanteDAO.buscarPorCodigo(4L);

		Fabricante f2 = fabricanteDAO.buscarPorCodigo(5L);

		Fabricante f3 = fabricanteDAO.buscarPorCodigo(6L);

		Fabricante f4 = fabricanteDAO.buscarPorCodigo(20L);

		if (f1 != null) {
			fabricanteDAO.excluir(f1);
		} else {
			System.out.println("O código procurado não está cadastrado!");
		}
		
		if (f2 != null) {
			fabricanteDAO.excluir(f2);
		} else {
			System.out.println("O código procurado não está cadastrado!");
		}
		
		if (f3 != null) {
			fabricanteDAO.excluir(f3);
		} else {
			System.out.println("O código procurado não está cadastrado!");
		}
		
		if (f4 != null) {
			fabricanteDAO.excluir(f4);
		} else {
			System.out.println("O código procurado não está cadastrado!");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(4L);

		fabricante.setDescricao("Descrição X");
		fabricanteDAO.editar(fabricante);
	}

}
