/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.Concurso;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author wrssilva
 */
@Stateless
public class TodosConcursos extends AbstractRepository<Concurso> {

    public TodosConcursos() {
        super(Concurso.class);
    }

    public List<Concurso> buscaComFiltro(String titulo, Integer codigo) {
        List<Concurso> lista = new ArrayList<Concurso>();
        try {
            String query = "SELECT u FROM Concurso u WHERE 1=1 ";

            if (titulo != null && !titulo.trim().equals("")) {
                query += " AND ( UPPER(u.titulo) LIKE ?1 or UPPER(u.titulo) LIKE ?1 )";
            }

            if (codigo != null) {
                query += " AND u.id = ?2 ";
            }

            query += " ORDER BY u.id DESC ";
            Query q = getEntityManager().createQuery(query);

            if (titulo != null && titulo.length() > 0) {
                q.setParameter(1, "%" + titulo.toUpperCase() + "%");
            }

            if (codigo != null) {
                q.setParameter(2, codigo);
            }

            q.setMaxResults(100);
            lista = q.getResultList();
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return lista;
    }
}