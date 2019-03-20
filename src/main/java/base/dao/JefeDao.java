package base.dao;

import base.domain.Jefe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class JefeDao {

    private SessionFactory sessionFactory;
    private Session session;

    public JefeDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        this.session = this.sessionFactory.openSession();
    }

    public void persistir (String nombre){

        this.session.beginTransaction();
        this.session.save(new Jefe(nombre));
        this.session.getTransaction().commit();

    }

    public Jefe buscar(long id){
        return session.get(Jefe.class, id);
    }

    public List<Jefe> listar(){
        Query q = session.createQuery(" from Jefe");

        return q.list();
    }

    public List<Jefe> buscarPorAtributo(Object campo, Object valor) {
        Query q = this.session.createQuery("from Jefe where " + campo + " = :atributo");
        q.setParameter("atributo", valor);


        return q.list();
    }


    public void actualizar(long id, String nombre) {


        Jefe jefe = this.session.get(Jefe.class, id);
        jefe.setNombre(nombre);

        this.session.beginTransaction();
        this.session.saveOrUpdate(jefe);
        this.session.getTransaction().commit();
    }

    public void borrar(long id){

        Jefe jefe = session.get(Jefe.class, id);
        this.session.beginTransaction();
        session.delete(jefe);
        this.session.getTransaction().commit();
    }


}
