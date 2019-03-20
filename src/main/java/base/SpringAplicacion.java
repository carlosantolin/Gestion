package base;

import base.service.EmpleadoService;
import base.service.JefeService;
import base.service.TareaService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringAplicacion {

    public static void main(String[] args){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        EmpleadoService empleadoService = (EmpleadoService) context.getBean("empleadoService");
        JefeService servicioJefe = (JefeService) context.getBean("jefeService");
        TareaService tareaService = (TareaService) context.getBean("tareaService");


    }
}
