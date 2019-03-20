package base.dao;

import base.domain.Empleado;
import base.domain.Horario;
import base.domain.Tarea;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)

public class HorarioDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void persistir (Empleado empleado, Tarea tarea, int horas){

        sessionFactory.getCurrentSession().save(new Horario(empleado, tarea, horas));

    }

    public Horario buscar(long id){
        return sessionFactory.getCurrentSession().get(Horario.class, id);
    }

    public List<Horario> listar(){
        Query q = sessionFactory.getCurrentSession().createQuery(" from Horario ");

        return q.list();
    }

    public List<Horario> buscarPorAtributo(Object campo, Object valor) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Horario where " + campo + " = :atributo");
        q.setParameter("atributo", valor);


        return q.list();
    }


    public void actualizar(long id, Empleado empleado, Tarea tarea, int horas) {


        Horario horario = sessionFactory.getCurrentSession().get(Horario.class, id);
        horario.setHoras(horas);
        horario.setAsignado(empleado);
        horario.setTarea(tarea);

        sessionFactory.getCurrentSession().saveOrUpdate(tarea);
    }

    public boolean borrar(long id){

        Horario horario = sessionFactory.getCurrentSession().get(Horario.class, id);


        sessionFactory.getCurrentSession().delete(horario);
        return true;
    }

}
