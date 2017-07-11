/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author wrssilva
 */

@Stateless
public class TodosUsuarios extends AbstractRepository<Usuario> {
    
   public TodosUsuarios() {
        super(Usuario.class);
    }
    @EJB
    private TodosPerfisAcessoUsuario todosPerfisAcessoUsuario;
    @EJB
    private TodosPerfisAcesso todosPerfisAcesso;

    public List<Usuario> buscaComFiltro(String nome, boolean ativo) {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            String query = "SELECT u FROM Usuario u WHERE 1=1 ";

            if (nome != null && !nome.trim().equals("")) {
                query += " AND ( UPPER(u.nome) LIKE ?1 or UPPER(u.nomeAcesso) LIKE ?1 )";
            }
            
            query += " AND u.indicadorAtivo = ?2 ";

            query += " ORDER BY u.nome";
            Query q = getEntityManager().createQuery(query);

            if (nome != null && nome.length() > 0) {
                q.setParameter(1, "%" + nome.toUpperCase() + "%");
            }
            
            q.setParameter(2, ativo);

            lista = q.getResultList();
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return lista;
    }

    public Usuario buscaPorAcessoAtivo(String login) {
        Usuario usu = null;
        Query q = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.nomeAcesso = ?1 and u.indicadorAtivo=true");
        q.setParameter(1, login);
        try {
            usu = (Usuario) q.getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return usu;
    }

    public boolean existeLogin(Usuario usuario) {
        boolean retorno = false;
        if (usuario.getId() == null) {
            usuario.setId(0);
        }
        Query q = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u <> ?1 AND u.nomeAcesso = ?2");
        q.setParameter(1, usuario);
        q.setParameter(2, usuario.getNomeAcesso());
        try {
            if (q.getSingleResult() != null) {
                return true;
            }
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return retorno;
    }

    public List<Usuario> buscaOrdenadaAtivos() {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            String query = "SELECT u FROM Usuario u "
                    + "WHERE u.indicadorAtivo = true ORDER BY u.nomeAcesso";
            Query q = getEntityManager().createQuery(query);

            lista = q.getResultList();
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return lista;
    }

    public List<Usuario> buscaOrdenadaAtivosNaoAdministradores() {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            String query = "SELECT DISTINCT u FROM Usuario u "
                    + " LEFT JOIN u.perfilAcessoUsuarioList pau "
                    + "WHERE u.indicadorAtivo=true AND pau.perfilAcesso.nome LIKE ?1 ORDER BY u.nome";
            Query q = getEntityManager().createQuery(query);
            q.setParameter(1, "%Administrador%");

            lista = q.getResultList();
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return lista;
    }

    public boolean conferirEmail(Usuario usuario) {
        boolean retorno = false;
        if (usuario.getId() == null) {
            usuario.setId(0);
        }
        Query q = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u <> ?1 AND u.email = ?2");
        q.setParameter(1, usuario);
        q.setParameter(2, usuario.getEmail());
        try {
            if (q.getSingleResult() != null) {
                return true;
            }
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return retorno;
    }    
}
