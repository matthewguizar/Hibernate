package client;

import org.hibernate.Session;
import util.HibernateUtil;
import domain.Message;

public class HelloWorldClient {
    public static void main(String[] args) {
        Session session = HibernateUtil.SessionFactory().openSession();
        session.beginTransaction();
        
        Message message = new Message("Hello world with Hibernate");
        
        session.save(message);

        session.getTransaction().commit();
        session.close();
    }
}
