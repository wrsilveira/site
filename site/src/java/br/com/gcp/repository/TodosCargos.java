/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.Cargo;
import br.com.gcp.model.Concurso;
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
public class TodosCargos extends AbstractRepository<Cargo> {

    public TodosCargos() {
        super(Cargo.class);
    }

    public List<Cargo> listarCargosPorConcurso(Concurso c) {
        List<Cargo> listaCargo = new ArrayList<Cargo>();
        try {
            String query = "SELECT c FROM Cargo c WHERE c.concurso=?1 Order by c.descricao";
            Query q = getEntityManager().createQuery(query);
            q.setParameter(1, c);
            listaCargo = q.getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return listaCargo;
    }
}