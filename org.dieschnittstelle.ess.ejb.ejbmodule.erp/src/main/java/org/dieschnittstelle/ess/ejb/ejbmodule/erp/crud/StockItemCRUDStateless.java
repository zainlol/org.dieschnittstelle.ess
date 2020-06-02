package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class StockItemCRUDStateless implements StockItemCRUDLocal {

    @PersistenceContext(unitName = "erp_PU")
    private EntityManager em;

    @Override
    public StockItem createStockItem(StockItem item) {
        em.persist(item);
        return item;
    }

    //To-Do: Criteria Query
    @Override
    public StockItem readStockItem(IndividualisedProductItem prod, PointOfSale pos) {
        TypedQuery<StockItem> query =
                em.createQuery("SELECT c FROM StockItem c WHERE c.product = " + prod.getId() +  " AND c.pos = " + pos.getId() , StockItem.class);
       return query.getResultList().isEmpty() ? null : query.getSingleResult();
    }

    @Override
    public StockItem updateStockItem(StockItem item) {
        return em.merge(item);
    }

    @Override
    public List<StockItem> readAllStockItems() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StockItem> cq = cb.createQuery(StockItem.class);
        Root<StockItem> rootEntry = cq.from(StockItem.class);
        CriteriaQuery<StockItem> all = cq.select(rootEntry);
        TypedQuery<StockItem> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<StockItem> readStockItemsForProduct(IndividualisedProductItem prod) {
        return null;
    }

    @Override
    public List<StockItem> readStockItemsForPointOfSale(PointOfSale pos) {
        return null;
    }
}
