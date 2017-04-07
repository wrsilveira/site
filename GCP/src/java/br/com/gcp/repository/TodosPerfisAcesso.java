/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.PerfilAcesso;
import br.com.gcp.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author wrssilva
 */

@Stateless
public class TodosPerfisAcesso extends AbstractRepository<PerfilAcesso> {

    public TodosPerfisAcesso() {
        super(PerfilAcesso.class);
    }

    public List<PerfilAcesso> buscaComFiltro(String nome) {
        List<PerfilAcesso> lista = new ArrayList<PerfilAcesso>();
        try {
            String query = "SELECT u FROM PerfilAcesso u WHERE UPPER(u.nome) LIKE ?1 ORDER BY u.nome";
            Query q = getEntityManager().createQuery(query);
            q.setParameter(1, "%" + nome.toUpperCase() + "%");
            lista = q.getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
           LoggerUtil.severe(this.getClass(), e);
        }
        return lista;
    }

    public List<String> listaPerfilAcesso(Usuario usuario) {
        List<String> lista = new ArrayList<String>();
        try {
            String query = "SELECT u.nome FROM PerfilAcesso u JOIN u.perfilAcessoUsuarioList pu WHERE pu.usuario=?1";
            Query q = getEntityManager().createQuery(query);
            q.setParameter(1, usuario);
            lista = q.getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
           LoggerUtil.severe(this.getClass(), e);
        }
        return lista;
    }
}