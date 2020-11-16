package com.tampro.dao;

import com.tampro.entity.ProductStock;

public interface ProductStockDAO<E> extends BaseDAO<E> {
	ProductStock findStockByProductIdAndInventoryId(int productId, int inventoryId);
}
