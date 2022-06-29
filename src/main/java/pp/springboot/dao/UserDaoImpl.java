package pp.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

import pp.springboot.model.User;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {

        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {

        return entityManager.createQuery(" FROM User").getResultList();

    }

    @Override
    public User getUser(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void editUser(Long id, User user) {

        User edit = entityManager.find(User.class, id);
        edit.setName(user.getName());
        edit.setSecond_name(user.getSecond_name());
        edit.setAge(user.getAge());
        edit.setEmail(user.getEmail());
    }

    @Override
       public void deleteUser(Long id) {

        entityManager.remove(entityManager.find(User.class, id));
    }
}

