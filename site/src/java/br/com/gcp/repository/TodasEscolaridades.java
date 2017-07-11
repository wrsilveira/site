/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.Concurso;
import br.com.gcp.model.Escolaridade;
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
public class TodasEscolaridades extends AbstractRepository<Escolaridade> {

    public TodasEscolaridades() {
        super(Escolaridade.class);
    }

    public List<Escolaridade> listarEscolaridades() {
        List<Escolaridade> listaEscolaridade = new ArrayList<Escolaridade>();
        try {
            String query = "SELECT e FROM Escolaridade e ORDER BY e.id ";
            Query q = getEntityManager().createQuery(query);
            q.setMaxResults(12);
            listaEscolaridade = q.getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return listaEscolaridade;
    }
}
