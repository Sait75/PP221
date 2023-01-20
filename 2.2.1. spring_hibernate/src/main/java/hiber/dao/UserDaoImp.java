package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).getResultList();
   }

   @Override
   public User findUserByCar(String model, int serial){
      return sessionFactory.getCurrentSession().createQuery("FROM Car WHERE model = :mod AND series = :ser", Car.class)
              .setParameter("mod", model)
              .setParameter("ser", serial)
              .getSingleResult().getUser();
   }
}
