package base;

import base.dao.EmpleadoDao;
import base.dao.HorarioDao;
import base.dao.JefeDao;
import base.dao.TareaDao;
import base.domain.Empleado;
import base.domain.Horario;
import base.domain.Jefe;
import base.domain.Tarea;
import base.service.EmpleadoService;
import base.service.JefeService;
import base.service.TareaService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Arrays;

public class SpringAplicacion {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        EmpleadoService empleadoService = (EmpleadoService) context.getBean("empleadoService");
        JefeService jefeService = (JefeService) context.getBean("jefeService");
        TareaService tareaService = (TareaService) context.getBean("tareaService");
        TareaDao tareaDao = (TareaDao) context.getBean("tareaDao");
        JefeDao jefeDao = (JefeDao) context.getBean("jefeDao");
        EmpleadoDao empleadoDao = (EmpleadoDao) context.getBean("empleadoDao");
        HorarioDao horarioDao = (HorarioDao) context.getBean("horarioDao");

        empleadoService.persistirEmpleado("carlos", "Desarrollo");
        empleadoService.persistirEmpleado("Laura", "Marketing");
        empleadoService.persistirEmpleado("Peyu", "Escapismo");
        empleadoService.persistirEmpleado("Shishi", "Desarrollo");

        jefeService.persistirJefe();
        jefeService.persistirJefe();

        Jefe jefe = jefeDao.buscarPorAtributo("nombre", "pepe").get(0);



        tareaDao.persistir("tarea de prueba", jefe,
                empleadoDao.buscarPorAtributo("departamento", "Desarrollo"));

        Empleado carlos = empleadoDao.buscarPorAtributo("nombre", "carlos").get(0);
        Tarea tarea = tareaDao.buscarPorAtributo("descripcion", "tarea de prueba").get(0);

        horarioDao.persistir(carlos, tarea, 20);

        System.out.println("Esta es la tarea mas ocupada" +tareaService.tareaMasOcupada().toString());
        System.out.println("Estas son las tareas de carlos "+ tareaService.tareasDeEmpleado(carlos).toString());
    }
}
