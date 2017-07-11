/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.Cargo;
import br.com.gcp.model.Concurso;
import br.com.gcp.model.Publicacao;
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
public class TodasPublicacoes extends AbstractRepository<Publicacao> {

    public TodasPublicacoes() {
        super(Publicacao.class);
    }

    public List<Publicacao> listarPublicacoesPorConcurso(Concurso c) {
        List<Publicacao> listaPublicacao = new ArrayList<Publicacao>();
        try {
            String query = "SELECT p FROM Publicacao p WHERE p.concurso=?1";
            Query q = getEntityManager().createQuery(query);
            q.setParameter(1, c);
            q.setMaxResults(100);
            listaPublicacao = q.getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return listaPublicacao;
    }

}
