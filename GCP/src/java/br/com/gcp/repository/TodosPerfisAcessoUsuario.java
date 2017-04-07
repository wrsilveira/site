/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.LoggerUtil;
import br.com.gcp.model.PerfilAcesso;
import br.com.gcp.model.PerfilAcessoUsuario;
import br.com.gcp.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author wrssilva
 */

@Stateless
public class TodosPerfisAcessoUsuario extends AbstractRepository<PerfilAcessoUsuario>{

    public TodosPerfisAcessoUsuario() {
        super(PerfilAcessoUsuario.class);
    }
    
    public List<PerfilAcesso> buscarPerfilPorUsuario(Usuario u) {
        List<PerfilAcesso> listPerfis = new ArrayList<PerfilAcesso>();
        try {
            Query query = getEntityManager().createQuery("SELECT pa FROM PerfilAcessoUsuario pu, PerfilAcesso pa WHERE pu.usuario = ?1 AND pa.id = pu.perfilAcesso.id");
            query.setParameter(1, u);
            listPerfis = query.getResultList();
        } catch (Exception e) {
           LoggerUtil.severe(this.getClass(), e);            
        }

        return listPerfis;
    }
       
    public void excluirPerfilPorUsuario(Usuario u) {        
        try {
            Query query = getEntityManager().createQuery("DELETE FROM PerfilAcessoUsuario p WHERE p.usuario = ?1");
            query.setParameter(1, u);
            query.executeUpdate();
        } catch (Exception e) {
           LoggerUtil.severe(this.getClass(), e);            
        }
    }
}
