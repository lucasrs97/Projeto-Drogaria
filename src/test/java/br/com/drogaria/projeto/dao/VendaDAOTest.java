package br.com.drogaria.projeto.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.projeto.domain.Funcionario;
import br.com.drogaria.projeto.domain.Venda;
import br.com.drogaria.projeto.filter.VendaFilter;

public class VendaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(1L);

		Venda venda = new Venda();
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setTotal(new BigDecimal(100.00D));

		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
	}

	@Test
	@Ignore
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> vendas = vendaDAO.lista();

		System.out.println(vendas);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		 VendaDAO vendaDAO = new VendaDAO();
		 Venda venda = vendaDAO.buscarPorCodigo(1L);
		 
		 System.out.println(venda);
	}
	
	@Test
	@Ignore
	public void excluir() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(2L);
		
		if(venda != null) {
			vendaDAO.excluir(venda);
		} else {
			System.out.println("O código procurado não está cadastrado.");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(1L);
		
		venda.setHorario(new Date());
		venda.setTotal(new BigDecimal(50.00));
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(1L);
		
		venda.setFuncionario(funcionario);
		
		vendaDAO.editar(venda);
	}
	
	@Test
	public void buscar() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		VendaFilter filtro = new VendaFilter();
		filtro.setDataInicial(formato.parse("07/02/2018"));
		filtro.setDataFinal(formato.parse("08/02/2018"));
		
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> vendas = vendaDAO.buscar(filtro);
		
		for (Venda venda : vendas) {
			System.out.println(venda);
		}
	}

}
