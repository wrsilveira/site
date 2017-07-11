/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.Deficiencia;
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
public class TodasDeficiencias extends AbstractRepository<Deficiencia> {

    public TodasDeficiencias() {
        super(Deficiencia.class);
    }

    public List<Deficiencia> listarDeficiencias() {
        List<Deficiencia> lista = new ArrayList<Deficiencia>();
        try {
            String query = "SELECT d FROM Deficiencia d ORDER BY d.id ";
            Query q = getEntityManager().createQuery(query);
            q.setMaxResults(12);
            lista = q.getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return lista;
    }
}