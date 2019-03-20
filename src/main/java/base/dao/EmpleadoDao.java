package base.dao;

import base.domain.Empleado;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpleadoDao {

    @Autowired
    private SessionFactory sessionFactory;



    public void persistir (String nombre, String departamento){

        sessionFactory.getCurrentSession().save(new Empleado(nombre, departamento));

    }

    public Empleado buscar(long id){
        return sessionFactory.getCurrentSession().get(Empleado.class, id);
    }

    public List<Empleado> listar(){
        Query q = sessionFactory.getCurrentSession().createQuery(" from Empleado");

        return q.list();
    }

    public List<Empleado> buscarPorAtributo(Object campo, Object valor) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Empleado where " + campo + " = :atributo");
        q.setParameter("atributo", valor);


        return q.list();
    }


    public void actualizar(long id, String nombre, String departamento) {


        Empleado empleado = sessionFactory.getCurrentSession().get(Empleado.class, id);
        empleado.setDepartamento(departamento);
        empleado.setNombre(nombre);


        sessionFactory.getCurrentSession().saveOrUpdate(empleado);
    }

    public void borrar(long id){

        Empleado empleado = sessionFactory.getCurrentSession().get(Empleado.class, id);
        sessionFactory.getCurrentSession().delete(empleado);
    }


}
