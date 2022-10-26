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
            Message msg = (Message) session.get(Message.class, 2L);
            // msg.setText("Hello Automatic dirty checking");
            session.delete(msg);
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
        
        // session.beginTransaction();
        
        // Message message = new Message("Hello world with Hibernate");
        
        // session.save(message);
        
        // session.getTransaction().commit();
        // session.close();
        System.exit(0);
    }
}
