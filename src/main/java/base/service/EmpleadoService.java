package base.service;

import base.dao.EmpleadoDao;
import base.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service("empleadoService")
public class EmpleadoService {


    @Autowired
    private  EmpleadoDao empleadoDao;


    @Transactional
    public void persistirEmpleado(String nombre, String departamento)  {

        empleadoDao.persistir(nombre, departamento);
    }

    @Transactional
    public void listarEmpleados() {
        List<Empleado> empleados = empleadoDao.listar();
        for(Empleado e : empleados) {
            System.out.println("Esto es lo que hay " + e.toString());
        }
    }

    @Transactional
    public void buscarEmpleadoPorAtributo() throws IOException {
        Object campo = requestStringInput("En que columna quieres buscar? ");
        Object valor  = requestStringInput("Que buscas? ");
        List<Empleado> empleados = empleadoDao.buscarPorAtributo(campo, valor);

        if(empleados.isEmpty() == false) {
            for (Empleado e : empleados) {
                System.out.println("Esto es lo que hay " + e.toString());
            }
        }
        else {
            System.out.println("Parece que no hay nada por aqui");
        }

    }

    @Transactional
    public void buscarEmpleadoPorId() throws IOException {
        int id = requestIntegerInput("Id del empleado");
        Empleado empleado = empleadoDao.buscar(id);
        if (empleado != null) {
            System.out.print(empleado.toString());
        }
        else{
            System.out.println("Parece que no hay nada por aqui");
        }
    }

    @Transactional
    public void actualizarEmpleado() throws IOException {
        int id = requestIntegerInput("id del empleado");
        String nombre = requestStringInput("nombre del empleado");
        String departamento = requestStringInput("departamento de empleado");
        empleadoDao.actualizar(id, nombre, departamento);
    }

    @Transactional
    public void borrarEmpleado() throws IOException {
        int id = requestIntegerInput("Id del empleado");
        empleadoDao.borrar(id);
    }

    private String requestStringInput(String request) throws IOException {
        System.out.printf("Introduce %s: ", request);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    private int requestIntegerInput(String request) throws IOException {
        System.out.printf("Introduce %s: ", request);
        return Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
    }

}
