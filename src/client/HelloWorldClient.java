package client;

import org.hibernate.Transaction;
import org.hibernate.Session;
import util.HibernateUtil;
import entity.Message;

public class HelloWorldClient {
    public static void main(String[] args) {
        Session session = HibernateUtil.SessionFactory().openSession();
        Transaction txn = session.getTransaction();
        try {
            txn.begin();
            Message msg = (Message) session.get(Message.class, 3L);
            // msg.setText("Hello Automatic dirty checking");
            //session.delete(msg);
            txn.commit();
        } catch (Exception e) {
            if(txn != null) {
                txn.rollback();
                e.printStackTrace();
            } 
        } finally {
            if(session!=null) {
                session.close();
                
            }
        }
        Session session2 = HibernateUtil.SessionFactory().openSession();
        session2.beginTransaction();

        Message msg2 = (Message) session2.get(Message.class, 3L);

        session2.getTransaction().commit();
        session2.close();

        msg2.setText("Hi");

        Session session3 = HibernateUtil.SessionFactory().openSession();
        session3.beginTransaction();

        session3.update(msg2);

        session3.getTransaction().commit();
        session3.close();
        
        // session.beginTransaction();
        
        // Message message = new Message("Hello world with Hibernate");
        
        // session.save(message);
        
        // session.getTransaction().commit();
        // session.close();
        System.exit(0);
    }
}
