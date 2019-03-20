package base;

import base.service.EmpleadoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringAplicacion {

    public static void main(String[] args){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        EmpleadoService servicio = (EmpleadoService) context.getBean("empleadoService");
        servicio.persistirEmpleado("carlo", "menta");
    }
}
