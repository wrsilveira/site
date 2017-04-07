/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.control;

import br.com.coderp.security.SessionControl;
import br.com.coderp.util.JsfUtil;
import br.com.gcp.model.Usuario;
import br.com.gcp.model.Concurso;
import br.com.gcp.repository.TodosConcursos;
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
public class ConcursoControl {

    @ManagedProperty(value = "#{sessionControl}")
    private SessionControl sessao;
    private String status = "list";
    public String filtroTitulo = "";
    public Integer filtroCodigo;
    private Usuario usuario;
    private Concurso concurso;
    List<Concurso> listaConcurso = new ArrayList<Concurso>();

    @EJB
    private TodosConcursos todosConcursos;

    public String prepareCreate() {
        concurso = new Concurso();
        status = "create";
        return null;
    }

    public String prepareList() {
        this.filtro();
        status = "list";
        return null;
    }

    public String prepareUpdate(Concurso c) {
        concurso = c;
        status = "update";
        return null;
    }

    public String prepareDelete(Concurso c) {
        concurso = c;
        status = "delete";
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

                concurso.setDtAtualizacao(new Date());
                concurso.setUsuarioAtualizacao((Usuario) sessao.getUsuario());
                todosConcursos.create(concurso);

                JsfUtil.addInfoMsg("Concurso " + concurso.getTitulo() + " cadastrado com sucesso");
                concurso = new Concurso();
                status = "list";
                this.filtro();
            } catch (Exception e) {
                JsfUtil.addErrorMsg("Concurso não cadastrado. Verifique os dados ou se ele já não existe");
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

    public String delete() {
        try {
            todosConcursos.remove(concurso);
            JsfUtil.addInfoMsg("Concurso " + concurso.getTitulo() + " deletado com sucesso");
            this.filtro();
            concurso = new Concurso();
            status = "list";
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMsg("Não foi possivel deletar o usuário!");
            return null;
        }
    }

    public String filtro() {
        listaConcurso = todosConcursos.buscaComFiltro(filtroTitulo, filtroCodigo);
        return null;
    }

    public boolean validar() {
        boolean retorno = true;

        return retorno;
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
}
