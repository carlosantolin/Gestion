package dao;

import domain.Empleado;
import domain.Jefe;
import domain.Tarea;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class TareaDao {

    private SessionFactory sessionFactory;
    private Session session;

    public TareaDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        this.session = this.sessionFactory.openSession();
    }

    public void persistir (String descripcion, Jefe jefe, List<Empleado> empleados){

        this.session.beginTransaction();
        this.session.save(new Tarea(empleados, jefe, descripcion, false));
        this.session.getTransaction().commit();

    }

    public Tarea buscar(long id){
        return session.get(Tarea.class, id);
    }

    public List<Tarea> listar(){
        Query q = session.createQuery(" from Tarea");

        return q.list();
    }

    public List<Tarea> buscarPorAtributo(Object campo, Object valor) {
        Query q = this.session.createQuery("from Tarea where " + campo + " = :atributo");
        q.setParameter("atributo", valor);


        return q.list();
    }


    public void actualizar(long id, String descripcion, boolean completada, Jefe jefe,
                           List<Empleado> empleados) {


        Tarea tarea = this.session.get(Tarea.class, id);
        tarea.setDescripcion(descripcion);
        tarea.setCompletada(completada);
        tarea.setJefe(jefe);
        for(Empleado e: empleados){
            tarea.pushEmpleado(e);
        }

        this.session.beginTransaction();
        this.session.saveOrUpdate(tarea);
        this.session.getTransaction().commit();
    }

    public void borrar(long id){

        Tarea tarea = session.get(Tarea.class, id);
        this.session.beginTransaction();
        session.delete(tarea);
        this.session.getTransaction().commit();
    }
}
