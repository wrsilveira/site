/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.control;

import br.com.coderp.security.SessionControl;
import br.com.coderp.util.JavaUtil;
import br.com.coderp.util.JsfUtil;
import br.com.gcp.model.Usuario;
import br.com.gcp.repository.TodosPerfisAcesso;
import br.com.gcp.repository.TodosUsuarios;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wrssilva
 */
@ManagedBean
@RequestScoped
public class LoginControl implements Serializable {

    @ManagedProperty(value = "#{sessionControl}")
    private SessionControl sessao;
    @EJB
    private TodosUsuarios todosUsuarios;
    @EJB
    private TodosPerfisAcesso todosPerfisAcesso;
    
    private Usuario usuario = new Usuario();

    private void autorizaPaginas() {
        
        sessao.adicionaPaginaAutorizada("/sis/principal.xhtml");
        sessao.adicionaPaginaAutorizada("/sis/trocarSenhaSucesso.xhtml");
        sessao.adicionaPaginaAutorizada("/sis/principal.xhtml");
        sessao.adicionaPaginaAutorizada("/sis/usuario.xhtml");
        sessao.adicionaPaginaAutorizada("/sis/perfilUsuario.xhtml");
    }
   
    public String executarLogin() {
        Usuario usu = todosUsuarios.buscaPorAcessoAtivo(usuario.getNomeAcesso());
        if (usu != null) {

            if (usu.getQtdTentativasFracassadas() > 5) {
                JsfUtil.addErrorMsg("Seu acesso foi bloqueado devido a excesso de tentativas fracassadas!");
                return null;
            }

            if (usu.getSenha().equals(JavaUtil.md5Password(usuario.getSenha())) && usu.getIndicadorAtivo() == true) {
                sessao.setUsuario(usu);
                for (String perfil : todosPerfisAcesso.listaPerfilAcesso(usu)) {
                    sessao.adicionaPerfil(perfil);
                }
                autorizaPaginas();
                usu.setDtUltimaAutenticacao(new Date());
                usu.setQtdTentativasFracassadas(0);
                usu.setIndicadorForcarSenha(false);
                todosUsuarios.edit(usu);
                return "sis/principal?faces-redirect=true";
            } else {
                usu.setQtdTentativasFracassadas(usu.getQtdTentativasFracassadas() + 1);
                todosUsuarios.edit(usu);
            }
        }
        JsfUtil.addErrorMsg("Erro ao tentar efetuar login! Tente novamente.");
        return null;
    }    
    
    public String limpaSessao() {
        HttpSession s = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        s.invalidate();
        return "/index";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public SessionControl getSessao() {
        return sessao;
    }

    public void setSessao(SessionControl sessao) {
        this.sessao = sessao;
    }

    public TodosUsuarios getTodosUsuarios() {
        return todosUsuarios;
    }

    public void setTodosUsuarios(TodosUsuarios todosUsuarios) {
        this.todosUsuarios = todosUsuarios;
    }

    public TodosPerfisAcesso getTodosPerfisAcesso() {
        return todosPerfisAcesso;
    }

    public void setTodosPerfisAcesso(TodosPerfisAcesso todosPerfisAcesso) {
        this.todosPerfisAcesso = todosPerfisAcesso;
    }
}
