/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.TipoPublicacao;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author willi
 */
@Stateless
public class TodosTiposPublicacao extends AbstractRepository<TipoPublicacao> {

    public TodosTiposPublicacao() {
        super(TipoPublicacao.class);
    }

    public List<TipoPublicacao> listarTipoPublicacao() {
        List<TipoPublicacao> lista = new ArrayList<TipoPublicacao>();
        try {
            String query = "SELECT p FROM TipoPublicacao p Order by p.descricao ";
            Query q = getEntityManager().createQuery(query);
            lista = q.getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return lista;
    }
}
