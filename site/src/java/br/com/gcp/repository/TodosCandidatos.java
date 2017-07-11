/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.Candidato;
import br.com.gcp.model.Candidato;
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
public class TodosCandidatos extends AbstractRepository<Candidato> {

    public TodosCandidatos() {
        super(Candidato.class);
    }

    public List<Candidato> buscarCandidato(String cpf) {
        List<Candidato> lista = new ArrayList<Candidato>();
        try {
            String query = "SELECT c FROM Candidato c WHERE c.cpf=?1 Order by c.nome";
            Query q = getEntityManager().createQuery(query);
            q.setParameter(1, cpf);
            lista = q.getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            LoggerUtil.severe(this.getClass(), e);
        }
        return lista;
    }
}
