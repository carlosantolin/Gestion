import dao.EmpleadoDao;
import dao.JefeDao;
import dao.TareaDao;
import domain.Empleado;
import domain.Jefe;
import domain.Tarea;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.EmpleadoService;
import service.JefeService;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class HibernateAplicacion {

        private final EmpleadoDao empleadoDao;
        private final JefeDao jefeDao;
        private final TareaDao tareaDao;
        private final BufferedReader userInputReader;
        private final EmpleadoService empleadoService;
        private final JefeService jefeService;

        public static void main(String[] args) throws Exception {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            EmpleadoDao empleadoDao = new EmpleadoDao(sessionFactory);
            JefeDao jefeDao = new JefeDao(sessionFactory);
            TareaDao tareaDao = new TareaDao(sessionFactory);
            BufferedReader userInputReader =
                    new BufferedReader(new InputStreamReader(System.in));

            new HibernateAplicacion(empleadoDao, jefeDao, tareaDao,  userInputReader).run(session);
        }

        public HibernateAplicacion(EmpleadoDao empleadoDao, JefeDao jefeDao,
                                   TareaDao tareaDao,BufferedReader userInputReader) {
            this.empleadoDao = empleadoDao;
            this.jefeDao = jefeDao;
            this.tareaDao = tareaDao;
            this.userInputReader = userInputReader;
            this.jefeService = new JefeService(userInputReader, jefeDao);
            this.empleadoService = new EmpleadoService(userInputReader, empleadoDao);
        }

        private void run(Session session) throws IOException {
            while (true) {
                System.out.println("\nIntroudce tu opción: \n"
                                + "1) Empleados. \n"
                                + "2) Jefes. \n"
                                + "3) Salir");

                int opcion = Integer.parseInt(userInputReader.readLine());

                switch (opcion){
                    case 1:
                        menuEmpleados();
                        break;
                    case 2:
                        menuJefes();
                        break;
                    case 3:
                        return;
                    case 4:
                        pruebaMagica(session);
                        break;

                }
            }
        }

        private void pruebaMagica(Session session){
            Empleado carlos = new Empleado("Carlos", "Desarrollo");
            Empleado laura = new Empleado("Laura", "Marketing");

            List<Empleado> empleados = new ArrayList<Empleado>();
            empleados.add(carlos);
            empleados.add(laura);

            Jefe jefe = new Jefe("Jefazo");

            Tarea tarea = new Tarea(empleados, jefe, "probando", false);

            session.beginTransaction();
            session.save(carlos);
            session.save(laura);
            session.save(jefe);
            session.save(tarea);
            session.getTransaction().commit();
            System.out.println("Finalizada prueba");

        }

        private void menuEmpleados() throws IOException {
            while (true) {
                System.out.println("\nIntroduce tu opcion: \n"
                        + "1) Insertar nuevo empleado. \n"
                        + "2) Encuentra un empleado. \n"
                        + "3) Editar empleado. \n"
                        + "4) Borrar empleado. \n"
                        + "5) Listar empleados. \n"
                        + "6) Encuentra empleado por atributo. \n"
                        + "7) Salir\n");
                int opcion = Integer.parseInt(userInputReader.readLine());

                switch (opcion) {
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

            private void menuJefes() throws IOException {
                while (true) {
                    System.out.println("\nIntroduce tu opcion: \n"
                            + "1) Insertar nuevo jefe. \n"
                            + "2) Encuentra un jefe. \n"
                            + "3) Editar jefe. \n"
                            + "4) Borrar jefe. \n"
                            + "5) Listar jefes. \n"
                            + "6) Encuentra jefe por atributo. \n"
                            + "7) Salir\n");
                    int opcion = Integer.parseInt(userInputReader.readLine());

                    switch (opcion) {
                        case 1:
                            jefeService.persistirJefe();
                            break;
                        case 2:
                            jefeService.buscarJefePorId();
                            break;
                        case 3:
                            jefeService.actualizarJefe();
                            break;
                        case 4:
                            jefeService.borrarJefe();
                            break;
                        case 5:
                            jefeService.listarJefes();
                            break;
                        case 7:
                            return;
                        case 6:
                            jefeService.buscarJefePorAtributo();
                            break;


                    }
                }
        }




    }


