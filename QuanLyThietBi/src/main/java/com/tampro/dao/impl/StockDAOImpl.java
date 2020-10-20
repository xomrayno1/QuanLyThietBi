package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.StockDAO;
import com.tampro.entity.Stock;

@Repository
@Transactional(rollbackFor = Exception.class)
public class StockDAOImpl extends BaseDAOImpl<Stock>implements StockDAO<Stock>{

}
