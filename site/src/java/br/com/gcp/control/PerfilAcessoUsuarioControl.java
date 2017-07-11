/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.control;

import br.com.coderp.security.SessionControl;
import br.com.coderp.util.JsfUtil;
import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.PerfilAcesso;
import br.com.gcp.model.PerfilAcessoUsuario;
import br.com.gcp.model.Usuario;
import br.com.gcp.repository.TodosPerfisAcesso;
import br.com.gcp.repository.TodosPerfisAcessoUsuario;
import br.com.gcp.repository.TodosUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author wrssilva
 */

@ViewScoped
@ManagedBean
public class PerfilAcessoUsuarioControl implements Serializable {
    @EJB
    private TodosPerfisAcessoUsuario todosPerfisAcessoUsuario;
    @EJB
    private TodosPerfisAcesso todosPefisAcesso;
    @EJB
    private TodosUsuarios usuarioFacade;
    @ManagedProperty(value = "#{sessionControl}")
    private SessionControl sessao;
    private Usuario usuario;
    private List<Usuario> listaUsuario = new ArrayList<>();
    private final List<PerfilAcessoUsuario> listaPerfilAcessoUsuario = new ArrayList<>();
    private DualListModel<PerfilAcesso> dualListPerfil = new DualListModel<>();
    private List<PerfilAcesso> listaAlvo;
    private List<PerfilAcesso> listaOrigem;  

    @PostConstruct
    public void ini() {
        listaUsuario = usuarioFacade.buscaOrdenadaAtivos();
    }

    public String prepareUpdate() {
        if (usuario != null) {
            listaAlvo = todosPerfisAcessoUsuario.buscarPerfilPorUsuario(usuario);
            listaOrigem = todosPefisAcesso.findAll();

            for (PerfilAcesso p : listaAlvo) {
                listaOrigem.remove(p);
            }

        } else {
            listaAlvo = new ArrayList<>();
            listaOrigem = new ArrayList<>();
        }
        dualListPerfil.setTarget(listaAlvo);
        dualListPerfil.setSource(listaOrigem);
        return null;
    }

    public String update() {
        if (validar()) {
            try {
                PerfilAcessoUsuario perfilAcessoUsuario = new PerfilAcessoUsuario();
                perfilAcessoUsuario.setDtAtualizacao(new Date());
                perfilAcessoUsuario.setUsuario(usuario);
                perfilAcessoUsuario.setUsuarioAtualizacao((Usuario) sessao.getUsuario());
                todosPerfisAcessoUsuario.excluirPerfilPorUsuario(usuario);
                for (PerfilAcesso perfil : dualListPerfil.getTarget()) {
                    perfilAcessoUsuario.setPerfilAcesso(perfil);
                    perfilAcessoUsuario.setUsuario(usuario);
                    //perfilAcessoUsuario.setPerfilAcessoUsuarioPK(new PerfilAcessoUsuarioPK(usuario.getId(), perfil.getId()));
                    todosPerfisAcessoUsuario.create(perfilAcessoUsuario);
                }

                JsfUtil.addInfoMsg("Perfis de acesso para o usuário [" + perfilAcessoUsuario.getUsuario() + "] salvos com sucesso");
            } catch (Exception e) {
                JsfUtil.addErrorMsg("Perfil de Acesso não cadastrado ");
                LoggerUtil.severe(this.getClass(), e);
            }
        }
        return null;
    }

    private boolean temPerfil(Usuario usu, String perf) {
        boolean temPerfil = false;
        List<PerfilAcesso> list = todosPerfisAcessoUsuario.buscarPerfilPorUsuario(usu);
        for (PerfilAcesso p : list) {
            if (p.getNome().equalsIgnoreCase(perf)) {
                temPerfil = true;
            }
        }
        return temPerfil;
    }

    private boolean temPerfil(List<PerfilAcesso> list, String perf) {
        boolean temPerfil = false;
        for (PerfilAcesso p : list) {
            if (p.getNome().equalsIgnoreCase(perf)) {
                temPerfil = true;
            }
        }
        return temPerfil;
    }

    public boolean validar() {
        boolean flag = true;
        if (!sessao.temPerfil("Administrador")) {
            boolean usuarioSelecionadoConfigurador = temPerfil(usuario, "Configurador");
            boolean usuarioSelecionadoAdministrador = temPerfil(usuario, "Administrador");
            boolean temPerfilConfigurador = temPerfil(dualListPerfil.getTarget(), "Configurador");
            boolean temPerfilAdministrador = temPerfil(dualListPerfil.getTarget(), "Administrador");

            if (usuarioSelecionadoAdministrador && !temPerfilAdministrador) {
                JsfUtil.addWarnMsg("O perfil Administrador não pode ser removido!");
                flag = false;
            }

            if (usuarioSelecionadoConfigurador && !temPerfilConfigurador && !temPerfilAdministrador) {
                JsfUtil.addWarnMsg("O perfil Configurador não pode ser removido!");
                flag = false;
            }
            for (PerfilAcesso perfil : dualListPerfil.getTarget()) {

                if ("Administrador".equalsIgnoreCase(perfil.getNome()) && !usuarioSelecionadoAdministrador) {
                    JsfUtil.addWarnMsg("O perfil Administrador só pode ser delegado por outro Administrador!");
                    flag = false;
                }

                if ("Configurador".equalsIgnoreCase(perfil.getNome()) && !usuarioSelecionadoAdministrador && !usuarioSelecionadoConfigurador) {
                    JsfUtil.addWarnMsg("O perfil Configurador só pode ser delegado por um Administrador!");
                    flag = false;
                }
            }

            if (!flag) {
                JsfUtil.addErrorMsg("A lista de perfil não foi atualizada!");
            }
        }
        return flag;
    }    

    public TodosPerfisAcessoUsuario getTodosPerfisAcessoUsuario() {
        return todosPerfisAcessoUsuario;
    }

    public void setTodosPerfisAcessoUsuario(TodosPerfisAcessoUsuario todosPerfisAcessoUsuario) {
        this.todosPerfisAcessoUsuario = todosPerfisAcessoUsuario;
    }

    public TodosPerfisAcesso getTodosPefisAcesso() {
        return todosPefisAcesso;
    }

    public void setTodosPefisAcesso(TodosPerfisAcesso todosPefisAcesso) {
        this.todosPefisAcesso = todosPefisAcesso;
    }

    public TodosUsuarios getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(TodosUsuarios usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public SessionControl getSessao() {
        return sessao;
    }

    public void setSessao(SessionControl sessao) {
        this.sessao = sessao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public DualListModel<PerfilAcesso> getDualListPerfil() {
        return dualListPerfil;
    }

    public void setDualListPerfil(DualListModel<PerfilAcesso> dualListPerfil) {
        this.dualListPerfil = dualListPerfil;
    }

    public List<PerfilAcesso> getListaAlvo() {
        return listaAlvo;
    }

    public void setListaAlvo(List<PerfilAcesso> listaAlvo) {
        this.listaAlvo = listaAlvo;
    }

    public List<PerfilAcesso> getListaOrigem() {
        return listaOrigem;
    }

    public void setListaOrigem(List<PerfilAcesso> listaOrigem) {
        this.listaOrigem = listaOrigem;
    }
}
