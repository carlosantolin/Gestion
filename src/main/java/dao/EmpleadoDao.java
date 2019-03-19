package dao;

import domain.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class EmpleadoDao {
    private SessionFactory sessionFactory;
    private Session session;

    public EmpleadoDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        this.session = this.sessionFactory.openSession();
    }

    public void persistir (String nombre, String departamento){

        this.session.beginTransaction();
        this.session.save(new Empleado(nombre, departamento));
        this.session.getTransaction().commit();

    }

    public Empleado buscar(long id){
        return session.get(Empleado.class, id);
    }

    public List<Empleado> listar(){
        Query q = session.createQuery(" from Empleado");

        return q.list();
    }

    public void actualizar(long id, String nombre, String departamento) {


        Empleado empleado = this.session.get(Empleado.class, id);
        empleado.setDepartamento(departamento);
        empleado.setNombre(nombre);

        this.session.beginTransaction();
        this.session.saveOrUpdate(new Empleado(nombre, departamento));
        this.session.getTransaction().commit();
    }

    public void borrar(long id){

        Empleado empleado = session.get(Empleado.class, id);
        this.session.beginTransaction();
        session.delete(empleado);
        this.session.getTransaction().commit();
    }


}
