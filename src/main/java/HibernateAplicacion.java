import dao.EmpleadoDao;
import domain.Empleado;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.EmpleadoService;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class HibernateAplicacion {

        private final EmpleadoDao empleadoDao;
        private final BufferedReader userInputReader;
        private final EmpleadoService empleadoService;

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
            this.empleadoService = new EmpleadoService(userInputReader, empleadoDao);
        }

        private void run() throws IOException {
            while (true) {
                System.out.println("Introsuce tu opcion: "
                        + "1) Insertar nuevo empleado. "
                        + "2) Encuentra un empleado. "
                        + "3) Editar empleado. "
                        + "4) Borrar empleado. \n"
                        + "5) Listar empleados. "
                        + "6) Encuentra empleado por atributo. "
                        + "7) Salir");
                int option = Integer.parseInt(userInputReader.readLine());

                switch (option) {
                    case 1:
                        empleadoService.persistirEmpleado();
                        break;
                    case 2:
                        empleadoService.buscarEmpleadoPorId();
                        break;
                    case 3:
                        empleadoService.actualizarEmpleado();
                        break;
                    case 4:
                        empleadoService.borrarEmpleado();
                        break;
                    case 5:
                        empleadoService.listarEmpleados();
                        break;
                    case 7:
                        return;
                    case 6:
                        empleadoService.buscarEmpleadoPorAtributo();
                        break;


                }
            }
        }




    }


