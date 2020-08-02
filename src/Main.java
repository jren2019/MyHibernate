import entity.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            //加载核心配置文件
            Configuration configuration = new Configuration();
            configuration.configure();

            //创建SessionFactory对象, 并在数据库中把表创建出来

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        //创建Session
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();

        try {
            User user = new User();
            user.setUsername("小李");
            user.setPassword("123");
            user.setAddress("日本");
            session.save(user);
            session.beginTransaction().commit();
        } finally {
            session.close();
        }
    }
}