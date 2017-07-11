/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.control;

import br.com.coderp.security.SessionControl;
import br.com.coderp.util.JavaUtil;
import br.com.coderp.util.JsfUtil;
import br.com.gcp.model.Cargo;
import br.com.gcp.model.Usuario;
import br.com.gcp.model.Concurso;
import br.com.gcp.model.Publicacao;
import br.com.gcp.model.TipoPublicacao;
import br.com.gcp.repository.TodosCargos;
import br.com.gcp.repository.TodosConcursos;
import br.com.gcp.repository.TodasPublicacoes;
import br.com.gcp.repository.TodosClientes;
import br.com.gcp.repository.TodosTiposPublicacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author wrssilva
 */
@ViewScoped
@ManagedBean
public class ConcursoControl implements Serializable {

    @ManagedProperty(value = "#{sessionControl}")
    private SessionControl sessao;
    private String status = "list";
    public String filtroTitulo = "";
    public Integer filtroCodigo;
    private Usuario usuario;
    private Concurso concurso;
    private Cargo cargo;
    private Publicacao publicacao;
    List<Concurso> listaConcurso = new ArrayList<Concurso>();
    List<Cargo> listaCargo = new ArrayList<Cargo>();
    List<Publicacao> listaPublicacao = new ArrayList<Publicacao>();
    List<TipoPublicacao> listaTipoPublicacao = new ArrayList<TipoPublicacao>();

    @EJB
    private TodosConcursos todosConcursos;
    @EJB
    private TodosCargos todosCargos;
    @EJB
    private TodasPublicacoes todasPublicacoes;
    @EJB
    private TodosTiposPublicacao todosTiposPublicacao;
    @EJB
    private TodosClientes todosClientes;

    public String prepareCreate() {
        concurso = new Concurso();
        status = "create";
        return null;
    }

    public String prepareCreateCargo() {
        cargo = new Cargo();
        status = "createCargo";
        return null;
    }

    public String prepareCreatePublicacao() {
        publicacao = new Publicacao();
        status = "createPublicacao";
        filtroTipoPublicacao();
        return null;
    }

    public String prepareCreateCargo(Concurso c) {
        concurso = c;
        cargo = new Cargo();
        filtroCargo();
        status = "listCargo";
        return null;
    }

    public String prepareCreatePublicacao(Concurso c) {
        concurso = c;
        publicacao = new Publicacao();
        filtroTipoPublicacao();
        filtroPublicacao();
        status = "listPublicacao";
        return null;
    }

    public String prepareList() {
        this.filtro();
        status = "list";
        return null;
    }

    public String prepareListCargo() {
        this.filtroCargo();
        status = "listCargo";
        return null;
    }

    public String prepareListPublicacao() {
        this.filtroPublicacao();
        status = "listPublicacao";
        return null;
    }

    public String prepareUpdate(Concurso c) {
        concurso = c;
        status = "update";
        return null;
    }

    public String prepareUpdateCargo(Cargo c) {
        cargo = c;
        status = "updateCargo";
        return null;
    }

    public String prepareUpdatePublicacao(Publicacao c) {
        publicacao = c;
        status = "updatePublicacao";
        return null;
    }

    public String prepareDelete(Concurso c) {
        concurso = c;
        status = "delete";
        return null;
    }

    public String prepareDeleteCargo(Cargo c) {
        cargo = c;
        status = "deleteCargo";
        return null;
    }

    public String prepareDeletePublicacao(Publicacao c) {
        publicacao = c;
        status = "deletePublicacao";
        return null;
    }

    public String prepareView(Concurso c) {
        concurso = c;
        status = "view";
        return null;
    }

    public String create() {
        if (validar()) {
            try {
                concurso.setCliente(todosClientes.find(1));
                concurso.setDtAtualizacao(new Date());
                concurso.setUsuarioAtualizacao((Usuario) sessao.getUsuario());
                todosConcursos.create(concurso);

                JsfUtil.addInfoMsg("Concurso " + concurso.getTitulo() + " cadastrado com sucesso");
                concurso = new Concurso();
                status = "list";
                this.filtro();
            } catch (Exception e) {
                JsfUtil.addErrorMsg("Concurso não cadastrado. Erro: " + e.toString());
            }
        }
        return null;
    }

    public String createCargo() {
        if (validarCargo()) {
            try {
                cargo.setConcurso(concurso);
                cargo.setDtAtualizacao(new Date());
                cargo.setUsuarioAtualizacao((Usuario) sessao.getUsuario());
                todosCargos.create(cargo);

                JsfUtil.addInfoMsg("Cargo " + cargo.getDescricao() + " cadastrado com sucesso");
                cargo = new Cargo();
                status = "listCargo";
                this.filtroCargo();
            } catch (Exception e) {
                JsfUtil.addErrorMsg("Cargo não cadastrado. Verifique os dados ou se ele já não existe");
            }
        }
        return null;
    }

    public String createPublicacao() {
        if (validarPublicacao()) {
            try {
                publicacao.setLink("xxx");
                publicacao.setIndicadorArquivo(true);
                publicacao.setConcurso(concurso);
                publicacao.setDtAtualizacao(new Date());
                publicacao.setUsuarioAtualizacao((Usuario) sessao.getUsuario());
                todasPublicacoes.create(publicacao);

                JsfUtil.addInfoMsg("Publicacao " + publicacao.getTitulo() + " cadastrada com sucesso");
                publicacao = new Publicacao();
                status = "listPublicacao";
                this.filtroPublicacao();
            } catch (Exception e) {
                JsfUtil.addErrorMsg("Publicacao não cadastrada. Verifique os dados ou se ele já não existe");
            }
        }
        return null;
    }

    public String update() {
        if (validar()) {

            concurso.setDtAtualizacao(new Date());
            concurso.setUsuarioAtualizacao((Usuario) sessao.getUsuario());
            todosConcursos.edit(concurso);

            JsfUtil.addInfoMsg("Concurso " + concurso.getTitulo() + " cadastrado com sucesso");
            concurso = new Concurso();
            status = "list";
            this.filtro();
        }
        return null;
    }

    public String updateCargo() {
        if (validarCargo()) {

            cargo.setDtAtualizacao(new Date());
            cargo.setUsuarioAtualizacao((Usuario) sessao.getUsuario());
            todosCargos.edit(cargo);

            JsfUtil.addInfoMsg("Cargo " + cargo.getDescricao() + " atualizado com sucesso");
            cargo = new Cargo();
            status = "listCargo";
            this.filtroCargo();
        }
        return null;
    }

    public String updatePublicacao() {
        if (validarPublicacao()) {

            publicacao.setDtAtualizacao(new Date());
            publicacao.setUsuarioAtualizacao((Usuario) sessao.getUsuario());
            todasPublicacoes.edit(publicacao);

            JsfUtil.addInfoMsg("Publicação " + concurso.getTitulo() + " atualizada com sucesso");
            status = "listPublicacao";
            this.filtroPublicacao();
        }
        return null;
    }

    public String delete() {
        try {
            todosConcursos.remove(concurso);
            JsfUtil.addInfoMsg("Concurso " + concurso.getTitulo() + " deletado com sucesso");
            this.filtro();
            concurso = new Concurso();
            status = "list";
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMsg("Não foi possivel deletar o concurso!");
            return null;
        }
    }

    public String deleteCargo() {
        try {
            todosCargos.remove(cargo);
            JsfUtil.addInfoMsg("Cargo " + cargo.getDescricao() + " deletado com sucesso");
            this.filtroCargo();
            cargo = new Cargo();
            status = "listCargo";
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMsg("Não foi possivel deletar o cargo!");
            return null;
        }
    }

    public String deletePublicacao() {
        try {
            todasPublicacoes.remove(publicacao);
            JsfUtil.addInfoMsg("Publicação " + publicacao.getTitulo() + " deletado com sucesso");
            this.filtroPublicacao();
            publicacao = new Publicacao();
            status = "listPublicacao";
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMsg("Não foi possivel deletar a publicação!");
            return null;
        }
    }

    public String filtro() {
        listaConcurso = todosConcursos.buscaComFiltro(filtroTitulo, filtroCodigo);
        return null;
    }

    public String filtroCargo() {
        listaCargo = todosCargos.listarCargosPorConcurso(concurso);
        return null;
    }

    public String filtroPublicacao() {
        listaPublicacao = todasPublicacoes.listarPublicacoesPorConcurso(concurso);
        return null;
    }

    public String filtroTipoPublicacao() {
        listaTipoPublicacao = todosTiposPublicacao.listarTipoPublicacao();
        return null;
    }

    public boolean validar() {
        boolean retorno = true;

        return retorno;
    }

    public boolean validarCargo() {
        if (JavaUtil.stringNullVazia(cargo.getDescricao())) {
            JsfUtil.addErrorMsg("Informe a descrição do cargo!");
            return false;
        }

        if (cargo.getTaxaInscricao() == null) {
            JsfUtil.addErrorMsg("Informe a taxa de inscrição!");
            return false;
        }
        return true;
    }

    public boolean validarPublicacao() {
        if (publicacao.getTipoPublicacao() == null) {
            JsfUtil.addErrorMsg("Informe o tipo!");
            return false;
        }
        return true;
    }

    public SessionControl getSessao() {
        return sessao;
    }

    public void setSessao(SessionControl sessao) {
        this.sessao = sessao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public TodosConcursos getTodosConcursos() {
        return todosConcursos;
    }

    public void setTodosConcursos(TodosConcursos todosConcursos) {
        this.todosConcursos = todosConcursos;
    }

    public String getFiltroTitulo() {
        return filtroTitulo;
    }

    public void setFiltroTitulo(String filtroTitulo) {
        this.filtroTitulo = filtroTitulo;
    }

    public Integer getFiltroCodigo() {
        return filtroCodigo;
    }

    public void setFiltroCodigo(Integer filtroCodigo) {
        this.filtroCodigo = filtroCodigo;
    }

    public List<Concurso> getListaConcurso() {
        return listaConcurso;
    }

    public void setListaConcurso(List<Concurso> listaConcurso) {
        this.listaConcurso = listaConcurso;
    }

    public List<Cargo> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<Cargo> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public TodosCargos getTodosCargos() {
        return todosCargos;
    }

    public void setTodosCargos(TodosCargos todosCargos) {
        this.todosCargos = todosCargos;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public List<Publicacao> getListaPublicacao() {
        return listaPublicacao;
    }

    public void setListaPublicacao(List<Publicacao> listaPublicacao) {
        this.listaPublicacao = listaPublicacao;
    }

    public TodasPublicacoes getTodasPublicacoes() {
        return todasPublicacoes;
    }

    public void setTodasPublicacoes(TodasPublicacoes todasPublicacoes) {
        this.todasPublicacoes = todasPublicacoes;
    }

    public List<TipoPublicacao> getListaTipoPublicacao() {
        return listaTipoPublicacao;
    }

    public void setListaTipoPublicacao(List<TipoPublicacao> listaTipoPublicacao) {
        this.listaTipoPublicacao = listaTipoPublicacao;
    }
}
