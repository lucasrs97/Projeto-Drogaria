package br.com.drogaria.projeto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.projeto.dao.FuncionarioDAO;
import br.com.drogaria.projeto.domain.Funcionario;
import br.com.drogaria.projeto.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String logar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioLogado = funcionarioDAO.autenticar(funcionarioLogado.getCpf(), 
					DigestUtils.md5Hex(funcionarioLogado.getSenha()));
			if (funcionarioLogado == null) {
				FacesUtil.adicionarMsgErro("CPF e/ou senha inválidos.");
				return null;
				// Return null significa que eu quero permanecer na página onde estou.
			} else {
				FacesUtil.adicionarMsgInfo("Funcionário autenticado com sucesso.");
				return "/pages/principal.xhtml?faces-redirect=true";
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar autenticar o funcionário: " + e.getMessage());
			return null;
		}
	}

	public String sair() {
		funcionarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}
}
