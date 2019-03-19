import domain.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

public class Runner {

    private long id;
    @Test
    public void crud(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        create(session);
        read(session);

        update(session);
        read(session);

        delete(session);
        read(session);

        session.close();
    }

    public void delete(Session session){
         System.out.println("Borrando a Carlos");

        Empleado carlos = (Empleado) session.get(Empleado.class, this.id);

        session.beginTransaction();
        session.delete(carlos);
        session.getTransaction().commit();

    }

    public void update(Session session){
        System.out.println("Cambiando el departamento de Carlos");
        Empleado carlos = (Empleado) session.get(Empleado.class, this.id);
        carlos.setDepartamento("Publicidad");

        session.beginTransaction();
        session.saveOrUpdate(carlos);
        session.getTransaction().commit();
    }

    public void create(Session session) {
        Empleado nuevo = new Empleado();
        nuevo.setDepartamento("Marketing");
        nuevo.setNombre("Carlos");

        Empleado nuevo2 = new Empleado();
        nuevo2.setDepartamento("Pirateria");
        nuevo2.setNombre("Laura");

        System.out.println("Creando nuevo Empleado " + nuevo.toString());

        session.beginTransaction();
        session.save(nuevo);
        session.save(nuevo2);
        session.getTransaction().commit();

        this.id=nuevo.getId();
    }

    public void read(Session session){
        Query q = session.createQuery("select _nombre from Empleado _nombre");

        List<Empleado> empleados = q.list();

        for(Empleado e : empleados){
            System.out.println("Esto es lo que hay " + e.toString());

        }
    }
}
