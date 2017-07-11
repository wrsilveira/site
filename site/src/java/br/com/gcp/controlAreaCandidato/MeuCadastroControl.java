/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.controlAreaCandidato;

import br.com.coderp.util.JavaUtil;
import br.com.coderp.util.JsfUtil;
import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.Candidato;
import br.com.gcp.model.Deficiencia;
import br.com.gcp.model.Escolaridade;
import br.com.gcp.repository.TodasDeficiencias;
import br.com.gcp.repository.TodasEscolaridades;
import br.com.gcp.repository.TodosCandidatos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author willi
 */
@ManagedBean
@ViewScoped
public class MeuCadastroControl implements Serializable {

    @EJB
    private TodasEscolaridades todasEscolaridades;
    @EJB
    private TodosCandidatos todosCandidatos;
    @EJB
    private TodasDeficiencias todasDeficiencias;

    private Candidato candidato = new Candidato();
    private String status;
    private List<Escolaridade> listaEscolaridade = new ArrayList<Escolaridade>();
    private List<Deficiencia> listaDeficiencia = new ArrayList<Deficiencia>();

    @PostConstruct
    public void init() {
        listaEscolaridade = todasEscolaridades.listarEscolaridades();
        listaDeficiencia = todasDeficiencias.listarDeficiencias();
        prepareCreate();
    }

    public boolean validar() {
        if (JavaUtil.stringNullVazia(candidato.getNome())) {
            JsfUtil.addErrorMsg("Informe a descrição do cargo!");
            return false;
        }
        return true;
    }

    public String prepareCreate() {
        candidato = new Candidato();
        candidato.setDeficiencia(null);
        status = "create";
        return null;
    }

    public String create() {
        if (validar()) {
            try {
                candidato.setSenha("123456");
                candidato.setDtAtualizacaoCandidato(new Date());
                candidato.setIndicadorFuncaoJurado(false);
                candidato.setIndicadorAfro(false);
                candidato.setDtUltAcesso(new Date());
                todosCandidatos.create(candidato);
                status = "sucesso";
                JsfUtil.addInfoMsg("Cadastro realizado com sucesso!");
            } catch (Exception e) {
                JsfUtil.addInfoMsg("Erro! Cadastro não realizado!");
                LoggerUtil.severe(this.getClass(), e, MeuCadastroControl.class);
            }
        }
        return null;
    }

    public String prepareUpdate() {
        return null;
    }

    public String update() {
        if (validar()) {
            todosCandidatos.edit(candidato);
        }
        return null;
    }

    public String limparDados() {
        candidato = new Candidato();
        return "meuCadastro";
    }

    public ArrayList<SelectItem> getComboUF() {
        ArrayList lista = new ArrayList();
        lista.add(new SelectItem("AC", "AC"));
        lista.add(new SelectItem("AL", "AL"));
        lista.add(new SelectItem("AM", "AM"));
        lista.add(new SelectItem("TO", "TO"));
        return lista;
    }

    public TodasEscolaridades getTodasEscolaridades() {
        return todasEscolaridades;
    }

    public void setTodasEscolaridades(TodasEscolaridades todasEscolaridades) {
        this.todasEscolaridades = todasEscolaridades;
    }

    public List<Escolaridade> getListaEscolaridade() {
        return listaEscolaridade;
    }

    public void setListaEscolaridade(List<Escolaridade> listaEscolaridade) {
        this.listaEscolaridade = listaEscolaridade;
    }

    public TodasDeficiencias getTodasDeficiencias() {
        return todasDeficiencias;
    }

    public void setTodasDeficiencias(TodasDeficiencias todasDeficiencias) {
        this.todasDeficiencias = todasDeficiencias;
    }

    public TodosCandidatos getTodosCandidatos() {
        return todosCandidatos;
    }

    public void setTodosCandidatos(TodosCandidatos todosCandidatos) {
        this.todosCandidatos = todosCandidatos;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Deficiencia> getListaDeficiencia() {
        return listaDeficiencia;
    }

    public void setListaDeficiencia(List<Deficiencia> listaDeficiencia) {
        this.listaDeficiencia = listaDeficiencia;
    }
}
