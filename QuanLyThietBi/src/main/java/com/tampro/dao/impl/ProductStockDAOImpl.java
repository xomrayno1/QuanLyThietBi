package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.StockDAO;
import com.tampro.entity.ProductStock;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductStockDAOImpl extends BaseDAOImpl<ProductStock>implements StockDAO<ProductStock> {

}
