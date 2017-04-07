/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.control;

import br.com.coderp.security.SessionControl;
import br.com.coderp.util.JavaUtil;
import br.com.coderp.util.JsfUtil;
import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.Usuario;
import br.com.gcp.repository.TodosUsuarios;
import java.util.Date;
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
public class TrocaSenhaControl {
    
 @EJB
    private TodosUsuarios todosUsuarios;
    @ManagedProperty(value = "#{sessionControl}")
    private SessionControl sessao;
    private String senha = "";
    private String novaSenha1 = "";
    private String novaSenha2 = "";
    private String trocaSenha = "";

    public TrocaSenhaControl() {
    }

    public String update() {
        Usuario usuario = (Usuario) sessao.getUsuario();
        if (!JavaUtil.md5Password(senha).equals(usuario.getSenha())) {
            JsfUtil.addWarnMsg("A senha antiga não confere!");
            return null;
        }

        if (novaSenha1.isEmpty() || novaSenha1.length() < 5) {
            JsfUtil.addWarnMsg("Sua senha deve ter no minimo 5 caracteres!");
            return null;
        }

        if (!novaSenha1.equals(novaSenha2)) {
            JsfUtil.addWarnMsg("As novas senhas não conferem!");
            return null;
        }

        try {
            usuario.setDtAlteracaoSenha(new Date());
            usuario.setSenha(JavaUtil.md5Password(novaSenha1));
            usuario.setIndicadorForcarSenha(false);
            usuario.setIndicadorAlterarSenha(false);
            todosUsuarios.edit(usuario);
            JsfUtil.addInfoMsg("Sua senha foi atualizada com sucesso");
            senha = "";
            novaSenha1 = "";
            novaSenha2 = "";
            return "/sis/trocarSenhaSucesso?faces-redirect=true";
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
            JsfUtil.addErrorMsg("Erro ao atualizar a senha!");
        }

        return null;
    }

    public boolean isTemQueTrocarSenha() {
        if (trocaSenha != null && trocaSenha.equalsIgnoreCase("true")) {
            return true;
        }

        if (((Usuario)sessao.getUsuario()).getIndicadorAlterarSenha()) {
            return true;
        }
        return false;
    }

    public boolean isPodeCancelarTrocaSenha() {
        if (trocaSenha != null && trocaSenha.equalsIgnoreCase("true")) {
            return true;
        }
        return false;
    }

    public SessionControl getSessao() {
        return sessao;
    }

    public void setSessao(SessionControl sessao) {
        this.sessao = sessao;
    }

    public String getNovaSenha1() {
        return novaSenha1;
    }

    public void setNovaSenha1(String novaSenha1) {
        this.novaSenha1 = novaSenha1;
    }

    public String getNovaSenha2() {
        return novaSenha2;
    }

    public void setNovaSenha2(String novaSenha2) {
        this.novaSenha2 = novaSenha2;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTrocaSenha() {
        return trocaSenha;
    }

    public void setTrocaSenha(String trocaSenha) {
        this.trocaSenha = trocaSenha;
    }    

    public TodosUsuarios getTodosUsuarios() {
        return todosUsuarios;
    }

    public void setTodosUsuarios(TodosUsuarios todosUsuarios) {
        this.todosUsuarios = todosUsuarios;
    }
}
