package hello.dao;

import java.util.List;

import hello.model.Stock;

public interface StockDao {

	public void save(Stock s);
	public List<Stock> list();

}
