package service;

import dao.EmpleadoDao;
import domain.Empleado;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class EmpleadoService {


    private final BufferedReader userInputReader;
    private final EmpleadoDao empleadoDao;

    public EmpleadoService(BufferedReader userInputReader, EmpleadoDao empleadoDao) {
        this.userInputReader = userInputReader;
        this.empleadoDao = empleadoDao;
    }

    public void persistirEmpleado() throws IOException {
        String nombre = requestStringInput("nombre del empleado");
        String departamento = requestStringInput("departamento del empleado");
        empleadoDao.persistir(nombre, departamento);
    }

    public void listarEmpleados() {
        List<Empleado> empleados = empleadoDao.listar();
        for(Empleado e : empleados) {
            System.out.println("Esto es lo que hay " + e.toString());
        }
    }

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

    public void actualizarEmpleado() throws IOException {
        int id = requestIntegerInput("id del empleado");
        String nombre = requestStringInput("nombre del empleado");
        String departamento = requestStringInput("departamento de empleado");
        empleadoDao.actualizar(id, nombre, departamento);
    }

    public void borrarEmpleado() throws IOException {
        int id = requestIntegerInput("Id del empleado");
        empleadoDao.borrar(id);
    }

    private String requestStringInput(String request) throws IOException {
        System.out.printf("Introduce %s: ", request);
        return userInputReader.readLine();
    }

    private int requestIntegerInput(String request) throws IOException {
        System.out.printf("Introduce %s: ", request);
        return Integer.parseInt(userInputReader.readLine());
    }

}
