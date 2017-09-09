package hello.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hello.model.Person;
import hello.model.Stock;

public class StockDaoImpl implements StockDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	public void save(Stock s) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(s);
		tx.commit();
		session.close();
	}

	public List<Stock> list() {
		Session session = this.sessionFactory.openSession();
		List<Stock> stockList = session.createQuery("from stock").list();
		session.close();
		return stockList;
	}

}
