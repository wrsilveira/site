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
import br.com.gcp.repository.TodosUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author wrssilva
 */

@ManagedBean
@ViewScoped
public class UsuarioControl implements Serializable {

    @ManagedProperty(value = "#{sessionControl}")
    private SessionControl sessao;
    private String status = "list";

    @EJB
    private TodosUsuarios todosUsuarios;
    private List<Usuario> listaUsuario = new ArrayList<>();
    private Usuario usuario = new Usuario();

    private String filtroNomeUsuario = "";
    private String filtroNomeAcesso = "";
    private boolean filtroAtivo = true;

    public String prepareCreate() {
        usuario = new Usuario();
        status = "create";
        return null;
    }

    public String prepareList() {
        this.filtro();
        status = "list";
        return null;
    }

    public String prepareUpdate(Usuario u) {
        usuario = u;
        usuario.setIndicadorForcarSenha(Boolean.FALSE);
        usuario.setIndicadorAlterarSenha(Boolean.FALSE);
        status = "update";
        return null;
    }

    public String prepareDelete(Usuario u) {
        usuario = u;
        status = "delete";
        return null;
    }

    public String prepareView(Usuario u) {
        usuario = u;
        status = "view";
        return null;
    }

    public String create() {
        if (validar()) {

            try {
                //coloca a dt_inclusao de agora
                usuario.setDtInclusao(new Date());
                usuario.setDtAtualizacao(new Date());
                usuario.setIndicadorAlterarSenha(true);
                usuario.setSenha(JavaUtil.md5Password(usuario.getSenha()));
                usuario.setQtdTentativasFracassadas(0);
                usuario.setUsuarioAtualizacao((Usuario) sessao.getUsuario());
                usuario.setUsuarioInclusao((Usuario) sessao.getUsuario());

                todosUsuarios.create(usuario);

                JsfUtil.addInfoMsg("Usuário " + usuario.getNome() + " cadastrado com sucesso");
                usuario = new Usuario();
                status = "list";
                this.filtro();
            } catch (Exception e) {
                JsfUtil.addErrorMsg("Usuário não cadastrado. Verifique os dados ou se ele já não existe");
            }
        }
        return null;
    }

    public String update() {
        if (validar()) {
            
            if (usuario.getIndicadorAlterarSenha()) {
                usuario.setSenha(JavaUtil.md5Password(usuario.getNomeAcesso()));
                usuario.setIndicadorAlterarSenha(true);
                usuario.setQtdTentativasFracassadas(0);
                JsfUtil.addInfoMsg("A senha do usuário foi forçada!");
            }

            try {
                usuario.setDtAtualizacao(new Date());
                todosUsuarios.edit(usuario);
                JsfUtil.addInfoMsg("Usuário " + usuario.getNome() + " atualizado com sucesso");
                this.filtro();
                usuario = new Usuario();
                status = "list";
            } catch (Exception e) {
                JsfUtil.addErrorMsg("Usuário não cadastrado. Verifique os dados ou se ele já não existe.");
            }
        }
        return null;
    }

    public String delete() {
        try {
            todosUsuarios.remove(usuario);
            JsfUtil.addInfoMsg("Usuário " + usuario.getNome() + " deletado com sucesso");
            this.filtro();
            usuario = new Usuario();
            status = "list";
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMsg("Não foi possivel deletar o usuário!");
            return null;
        }
    }

    public String filtro() {
        listaUsuario = todosUsuarios.buscaComFiltro(filtroNomeUsuario, filtroAtivo);
        return null;
    }

    public boolean validar() {
        boolean retorno = true;
        String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
        Matcher matcher = null;

        if (usuario.getNome() != null && usuario.getNome().trim().length() < 5) {
            JsfUtil.addWarnMsg("O Nome deve ter pelo menos 5 caracteres");
            retorno = false;
        }

//        if (usuario.getNomePopular() != null && usuario.getNomePopular().trim().length() < 3) {
//            JsfUtil.addWarnMsg("O Nome popular deve ter pelo menos 3 caracteres");
//            retorno = false;
//        }

//        if (JavaUtil.stringNullVazia(usuario.getLocalAtual())) {
//            JsfUtil.addWarnMsg("O Local Atual deve ser preenchido");
//            retorno = false;
//        }

        if (JavaUtil.stringNullVazia(usuario.getEmail())) {
            JsfUtil.addWarnMsg("O E-mail deve ser preenchido");
            retorno = false;
        } else {
            matcher = pattern.matcher(usuario.getEmail());
            if (!matcher.matches()) {
                JsfUtil.addWarnMsg("E-mail inválido");
                retorno = false;
            }
            
            if(todosUsuarios.conferirEmail(usuario)){
                JsfUtil.addWarnMsg("Este e-mail já foi cadastrado no sistema");
                retorno = false; 
            }
        }

        if (usuario.getNomeAcesso() != null && usuario.getNomeAcesso().trim().length() < 5) {
            JsfUtil.addWarnMsg("O Login deve ter pelo menos 5 caracteres");
            retorno = false;
        }

        if (usuario.getSenha() != null && usuario.getSenha().length() < 5) {
            JsfUtil.addWarnMsg("A senha deve ter pelo menos 5 caracteres");
            retorno = false;
        }

        //verifica se existe algum usuario no banco com o mesmo login
        if (todosUsuarios.existeLogin(usuario)) {
            JsfUtil.addWarnMsg("Já existe: " + usuario.getNomeAcesso() + ". Tente outro por favor");
            retorno = false;
        }
        return retorno;
    }

    public String stringAtivo(boolean ativo) {
        if (ativo) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    public String getFiltroNomeUsuario() {
        return filtroNomeUsuario;
    }

    public void setFiltroNomeUsuario(String filtroNomeUsuario) {
        this.filtroNomeUsuario = filtroNomeUsuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFiltroNomeAcesso() {
        return filtroNomeAcesso;
    }

    public void setFiltroNomeAcesso(String filtroNomeAcesso) {
        this.filtroNomeAcesso = filtroNomeAcesso;
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

    public boolean isFiltroAtivo() {
        return filtroAtivo;
    }

    public void setFiltroAtivo(boolean filtroAtivo) {
        this.filtroAtivo = filtroAtivo;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
