package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.ProductDAO;
import com.tampro.entity.Product;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductDAOImpl extends BaseDAOImpl<Product>implements ProductDAO<Product>{

}
