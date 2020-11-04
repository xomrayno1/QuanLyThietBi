package com.tampro.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.ProductStockDAO;
import com.tampro.entity.ProductStock;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductStockDAOImpl extends BaseDAOImpl<ProductStock>implements ProductStockDAO<ProductStock> {

	public ProductStock findStockByProductIdAndInventoryId(int productId, int inventoryId) {
		// TODO Auto-generated method stub
		Query query = factory.getCurrentSession()
				.createQuery(" FROM ProductStock as model where model.product.id = :productId and model.stock.id = :stockId ");
		query.setParameter("productId", productId);
		query.setParameter("stockId", inventoryId);
		if(query.getResultList().isEmpty()) {
			return null;
		}else {
			return (ProductStock) query.getResultList().get(0);
		}
	}

}
