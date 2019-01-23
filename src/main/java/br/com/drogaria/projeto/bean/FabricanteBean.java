package br.com.drogaria.projeto.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.projeto.dao.FabricanteDAO;
import br.com.drogaria.projeto.domain.Fabricante;
import br.com.drogaria.projeto.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {

	private Fabricante fabricanteCadastro;
	private List<Fabricante> listaFabricantes;
	private List<Fabricante> listaFabricantesFiltrados;
	private String acao;
	private Long codigoFabricante;

	public Fabricante getFabricanteCadastro() {
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	public List<Fabricante> getListaFabricantesFiltrados() {
		return listaFabricantesFiltrados;
	}

	public void setListaFabricantesFiltrados(List<Fabricante> listaFabricantesFiltrados) {
		this.listaFabricantesFiltrados = listaFabricantesFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigoFabricante() {
		return codigoFabricante;
	}

	public void setCodigoFabricante(Long codigoFabricante) {
		this.codigoFabricante = codigoFabricante;
	}

	public void novo() {
		fabricanteCadastro = new Fabricante();
	}

	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);

			fabricanteCadastro = new Fabricante();

			FacesUtil.adicionarMsgInfo("Fabricante salvo com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar incluir um fabricante: " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricantes = fabricanteDAO.listar();			
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os fabricantes: " + e.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {			
			if(codigoFabricante != null) {	
				FabricanteDAO fabricanteDAO = new FabricanteDAO();
				fabricanteCadastro = fabricanteDAO.buscarPorCodigo(codigoFabricante);
			} else {
					fabricanteCadastro = new Fabricante();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar obter os dados do fabricante. " + e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricanteCadastro);

			FacesUtil.adicionarMsgInfo("Fabricante excluído com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluír um fabricante: " + e.getMessage());
		}
	}
	
	public void editar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.editar(fabricanteCadastro);

			FacesUtil.adicionarMsgInfo("Fabricante editado com sucesso.");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o fabricante: " + e.getMessage());
		}
	}
	
}
