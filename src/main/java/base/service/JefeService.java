package base.service;

import base.dao.JefeDao;
import base.domain.Jefe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service("jefeService")
@Transactional
public class JefeService {


    @Autowired
    private  JefeDao jefeDao;



    public void persistirJefe() throws IOException {
        String nombre = requestStringInput("nombre del jefe");
        jefeDao.persistir(nombre);
    }

    public void listarJefes() {
        List<Jefe> jefes = jefeDao.listar();
        for(Jefe e : jefes) {
            System.out.println("Esto es lo que hay " + e.toString());
        }
    }

    public void buscarJefePorAtributo() throws IOException {
        Object campo = requestStringInput("En que columna quieres buscar? ");
        Object valor  = requestStringInput("Que buscas? ");
        List<Jefe> jefes = jefeDao.buscarPorAtributo(campo, valor);

        if(jefes.isEmpty() == false) {
            for (Jefe e : jefes) {
                System.out.println("Esto es lo que hay " + e.toString());
            }
        }
        else {
            System.out.println("Parece que no hay nada por aqui");
        }

    }

    public void buscarJefePorId() throws IOException {
        int id = requestIntegerInput("Id del jefe");
        Jefe jefe = jefeDao.buscar(id);
        if (jefe != null) {
            System.out.print(jefe.toString());
        }
        else{
            System.out.println("Parece que no hay nada por aqui");
        }
    }

    public void actualizarJefe() throws IOException {
        int id = requestIntegerInput("id del empleado");
        String nombre = requestStringInput("nombre del jefe");
        jefeDao.actualizar(id, nombre);
    }

    public void borrarJefe() throws IOException {
        int id = requestIntegerInput("Id del jefe");
        jefeDao.borrar(id);
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
