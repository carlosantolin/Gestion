import dao.EmpleadoDao;
import domain.Empleado;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class HibernateAplicacion {

        private final EmpleadoDao empleadoDao;
        private final BufferedReader userInputReader;

        public static void main(String[] args) throws Exception {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //Session session = sessionFactory.openSession();

            EmpleadoDao empleadoDao = new EmpleadoDao(sessionFactory);
            BufferedReader userInputReader =
                    new BufferedReader(new InputStreamReader(System.in));
            new HibernateAplicacion(empleadoDao, userInputReader).run();
        }

        public HibernateAplicacion(EmpleadoDao empleadoDao, BufferedReader userInputReader) {
            this.empleadoDao = empleadoDao;
            this.userInputReader = userInputReader;
        }

        private void run() throws IOException {
            while (true) {
                System.out.println("Introsuce tu opcion: "
                        + "1) Insertar nuevo empleado. "
                        + "2) Encuentra un empleado. "
                        + "3) Editar empleado. "
                        + "4) Borrar empleado. "
                        + "5) Listar empleados. "
                        + "6) Salir");
                int option = Integer.parseInt(userInputReader.readLine());

                switch (option) {
                    case 1:
                        persistirEmpleado();
                        break;
                    case 2:
                        buscarEmpleadoPorId();
                        break;
                    case 3:
                        actualizarEmpleado();
                        break;
                    case 4:
                        borrarEmpleado();
                        break;
                    case 5:
                        listarEmpleados();
                        break;
                    case 6:
                        return;
                }
            }
        }

        private void persistirEmpleado() throws IOException {
            String nombre = requestStringInput("nombre del empleado");
            String departamento = requestStringInput("departamento del empleado");
            empleadoDao.persistir(nombre, departamento);
        }

        private void listarEmpleados() {
            List<Empleado> empleados = empleadoDao.listar();
            for(Empleado e : empleados) {
                System.out.println("Esto es lo que hay " + e.toString());
            }
        }

        private void buscarEmpleadoPorId() throws IOException {
            int id = requestIntegerInput("Id del empleado");
            Empleado empleado = empleadoDao.buscar(id);
            System.out.print(empleado.toString());
        }

        private void actualizarEmpleado() throws IOException {
            int id = requestIntegerInput("id del empleado");
            String nombre = requestStringInput("nombre del empleado");
            String departamento = requestStringInput("departamento de empleado");
            empleadoDao.actualizar(id, nombre, departamento);
        }

        private void borrarEmpleado() throws IOException {
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


