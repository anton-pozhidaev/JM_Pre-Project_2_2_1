package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      TypedQuery<Car> query1 = sessionFactory.getCurrentSession().createQuery("from Car where model=:m AND series=:s");
      query1.setParameter("m", model);
      query1.setParameter("s", series);
      long carId = query1.getSingleResult().getId();

      TypedQuery<User> query2 = sessionFactory.getCurrentSession().createQuery("from User where car.id=:carId");
      query2.setParameter("carId", carId);
      return query2.getSingleResult();
   }

   @Override
   public void cleanUsersTable() {
      sessionFactory.getCurrentSession().createQuery("delete User").executeUpdate();
   }

   @Override
   public void cleanCarsTable() {
      sessionFactory.getCurrentSession().createQuery("delete Car").executeUpdate();
   }

}
