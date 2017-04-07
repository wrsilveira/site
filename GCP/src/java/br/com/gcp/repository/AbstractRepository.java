/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.coderp.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author lfpaz
 * @param <T>
 *
 *
 */
public abstract class AbstractRepository<T> implements Serializable {

    @PersistenceContext(unitName = "GCPPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    private Class<T> entityClass = null;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        Integer id = (Integer) JsfUtil.getValorSession("idcliente");
        if (id != null) {
            em.setProperty("eclipselink.tenant-id", id);
        } else {
            em.setProperty("eclipselink.tenant-id", 0);
        }
        return em;
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public boolean existe(Object id) {
        Object o = find(id);
        if (o != null) {
            return true;
        }
        return false;
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findExcept(List<T> listEntity) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List<T> list = getEntityManager().createQuery(cq).getResultList();
        list.removeAll(listEntity);
        return list;
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
