package br.com.drogaria.projeto.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.projeto.domain.Funcionario;

public class FuncionarioDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Funcionario f1 = new Funcionario();
		f1.setNome("Lucas Ramalho Soares");
		f1.setCpf("168.933.517-39");
		f1.setSenha("Motherlode");
		f1.setFuncao("Desenvolvedor");

		Funcionario f2 = new Funcionario();
		f2.setNome("Celso dos Reis Soares");
		f2.setCpf("869.210.377-20");
		f2.setSenha("Flamengo");
		f2.setFuncao("Gerente");

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

		funcionarioDAO.salvar(f1);
		funcionarioDAO.salvar(f2);
	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> funcionarios = funcionarioDAO.listar();

		for (Funcionario funcionario : funcionarios) {
			System.out.println("Funcionários encontrados: " + funcionario);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

		Funcionario f1 = funcionarioDAO.buscarPorCodigo(1L);

		System.out.println("Resultado da busca por código");
		System.out.println(f1);

	}

	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		Funcionario f1 = funcionarioDAO.buscarPorCodigo(2L);
		
		if (f1 != null) {
			funcionarioDAO.excluir(f1);
		} else {
			System.out.println("O código procurado não está cadastrado.");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		Funcionario f1 = funcionarioDAO.buscarPorCodigo(1L);
		
		f1.setNome("Lucas Ramalho Soares");
		f1.setCpf("168.933.517-39");
		f1.setSenha("Motherlode.");
		f1.setFuncao("Desenvolvedor Pleno");
		
		
		funcionarioDAO.editar(f1);
	}
	
	@Test
	public void autenticar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.autenticar("168.933.517-39", "Motherlode.");
		
		Assert.assertNotNull(funcionario);
		// System.out.println("Funcionário autenticado: " + funcionario);
	}

}
