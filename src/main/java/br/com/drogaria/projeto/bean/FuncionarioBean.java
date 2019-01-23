package br.com.drogaria.projeto.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.projeto.dao.FuncionarioDAO;
import br.com.drogaria.projeto.domain.Funcionario;
import br.com.drogaria.projeto.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionarioCadastro;
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFuncionariosFiltrados;
	private String acao;
	private Long codigoFuncionario;

	public Funcionario getFuncionarioCadastro() {
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}

	public void setListaFuncionariosFiltrados(List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(Long codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public void novo() {
		funcionarioCadastro = new Funcionario();
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			funcionarioDAO.salvar(funcionarioCadastro);

			funcionarioCadastro = new Funcionario();

			FacesUtil.adicionarMsgInfo("Funcionário salvo com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar incluir um funcionário: " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			listaFuncionarios = funcionarioDAO.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os funcionários: " + e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigoFuncionario != null) {
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarioCadastro = funcionarioDAO.buscarPorCodigo(codigoFuncionario);
			} else {
				funcionarioCadastro = new Funcionario();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar obter os dados do funcionário. " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionarioCadastro);

			FacesUtil.adicionarMsgInfo("Funcionário excluído com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluír um funcionário: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			funcionarioDAO.editar(funcionarioCadastro);

			FacesUtil.adicionarMsgInfo("Funcionário editado com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o funcionário: " + e.getMessage());
		}
	}

}