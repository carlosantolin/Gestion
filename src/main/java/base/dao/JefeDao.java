package base.dao;

import base.domain.Jefe;
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

public class JefeDao {

    @Autowired
    private SessionFactory sessionFactory;
    private TareaDao tareaDao;



    public void persistir (String nombre){

        sessionFactory.getCurrentSession().save(new Jefe(nombre));

    }

    public Jefe buscar(long id){
        return sessionFactory.getCurrentSession().get(Jefe.class, id);
    }

    public List<Jefe> listar(){
        Query q = sessionFactory.getCurrentSession().createQuery(" from Jefe");

        return q.list();
    }

    public List<Jefe> buscarPorAtributo(Object campo, Object valor) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Jefe where " + campo + " = :atributo");
        q.setParameter("atributo", valor);


        return q.list();
    }


    public void actualizar(long id, String nombre) {


        Jefe jefe = sessionFactory.getCurrentSession().get(Jefe.class, id);
        jefe.setNombre(nombre);

        sessionFactory.getCurrentSession().saveOrUpdate(jefe);
    }

    public boolean borrar(long id){

        Jefe jefe = sessionFactory.getCurrentSession().get(Jefe.class, id);

        if(!tareaDao.buscarPorAtributo("jefe_id", jefe.getId()).isEmpty()){ //No asignado a tarea
            return false;
        }

        sessionFactory.getCurrentSession().delete(jefe);

        return true;
    }


}
