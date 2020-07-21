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
   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public List<Car> listCars() {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String name, int series) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where name = :paramName and series= :paramSeries");
      query.setParameter("paramName", name);
      query.setParameter("paramSeries", series);
      Car car = query.getResultList().get(0);
      TypedQuery<User> query1 = sessionFactory.getCurrentSession().createQuery("from User where car_id = :сarId");
      query1.setParameter("сarId", (Long) car.getId());
      return query1.getResultList().get(0);
   }

}
