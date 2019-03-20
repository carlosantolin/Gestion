package base.service;

import base.dao.JefeDao;
import base.dao.TareaDao;
import base.domain.Empleado;
import base.domain.Jefe;
import base.domain.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("tareaService")
@Transactional
public class TareaService {

    @Autowired
    private TareaDao tareaDao;
    private JefeDao jefeDao;

    public Tarea tareaMasOcupada(){
        List<Tarea> tareas = tareaDao.listar();

        if(tareas.isEmpty()){
            return null;
        }

        Tarea resultado = tareas.get(0);
        for(Tarea t : tareas) {
            if (t.getEmpleadosAsignados().size() > resultado.getEmpleadosAsignados().size()) {
                resultado = t;
            }
        }
        return resultado;
    }

    public List<Tarea> tareasDeEmpleado(Empleado empleado){
        List<Tarea> tareas = tareaDao.listar();
        List<Tarea> resultado = new ArrayList<Tarea>();

        for(Tarea t : tareas){
            if(t.getEmpleadosAsignados().contains(empleado)){
                resultado.add(t);
            }
        }
        return resultado;
    }

    public List<Tarea> tareasDeJefe(Jefe jefe){
        return tareaDao.buscarPorAtributo("jefe_id", jefe.getId());

    }
}
