package base.dao;

import base.domain.Empleado;
import base.domain.Jefe;
import base.domain.Tarea;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TareaDao {

    @Autowired
    private SessionFactory sessionFactory;
    private HorarioDao horarioDao;


    public void persistir (String descripcion, Jefe jefe, List<Empleado> empleados){

        sessionFactory.getCurrentSession().save(new Tarea(empleados, jefe, descripcion, false));

    }

    public Tarea buscar(long id){
        return sessionFactory.getCurrentSession().get(Tarea.class, id);
    }

    public List<Tarea> listar(){
        Query q = sessionFactory.getCurrentSession().createQuery(" from Tarea");

        return q.list();
    }

    public List<Tarea> buscarPorAtributo(Object campo, Object valor) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Tarea where " + campo + " = :atributo");
        q.setParameter("atributo", valor);


        return q.list();
    }


    public void actualizar(long id, String descripcion, boolean completada, Jefe jefe,
                           List<Empleado> empleados) {


        Tarea tarea = sessionFactory.getCurrentSession().get(Tarea.class, id);
        tarea.setDescripcion(descripcion);
        tarea.setCompletada(completada);
        tarea.setJefe(jefe);
        for(Empleado e: empleados){
            tarea.pushEmpleado(e);
        }

        sessionFactory.getCurrentSession().saveOrUpdate(tarea);
    }

    public boolean borrar(long id){

        Tarea tarea = sessionFactory.getCurrentSession().get(Tarea.class, id);

        if(!horarioDao.buscarPorAtributo("tarea_id", tarea.getId()).isEmpty()){ //No asignada a horario
            return false;
        }

        sessionFactory.getCurrentSession().delete(tarea);
        return  true;
    }
}
