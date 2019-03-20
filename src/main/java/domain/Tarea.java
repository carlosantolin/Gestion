package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tarea", schema = "tiempo")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tarea_empleado", schema = "tiempo",
                joinColumns = {@JoinColumn(name="tarea_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name="empleado_id", referencedColumnName = "id")})
    private List<Empleado> empleadosAsignados = new ArrayList<Empleado>();

    @OneToOne
    @JoinColumn(name = "jefe_id", referencedColumnName = "id")
    private Jefe jefe;

    private String descripcion;
    private boolean completada;

    public Tarea() {}

    public Tarea(List<Empleado> empleadosAsignados, Jefe jefe,
                 String descripcion, boolean completada) {
        this.empleadosAsignados = empleadosAsignados;
        this.jefe = jefe;
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Empleado> getEmpleadosAsignados() {
        return empleadosAsignados;
    }

    public void setEmpleadosAsignados(List<Empleado> empleadosAsignados) {
        this.empleadosAsignados = empleadosAsignados;
    }

    public void pushEmpleado(Empleado empleado){
        this.empleadosAsignados.add(empleado);
    }

    public Jefe getJefe() {
        return jefe;
    }

    public void setJefe(Jefe jefe) {
        this.jefe = jefe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tarea tarea = (Tarea) o;

        if (id != tarea.id) return false;
        if (completada != tarea.completada) return false;
        if (empleadosAsignados != null ? !empleadosAsignados.equals(tarea.empleadosAsignados) : tarea.empleadosAsignados != null)
            return false;
        if (jefe != null ? !jefe.equals(tarea.jefe) : tarea.jefe != null) return false;
        return descripcion != null ? descripcion.equals(tarea.descripcion) : tarea.descripcion == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (empleadosAsignados != null ? empleadosAsignados.hashCode() : 0);
        result = 31 * result + (jefe != null ? jefe.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (completada ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", empleadosAsignados=" + empleadosAsignados +
                ", jefe=" + jefe +
                ", descripcion='" + descripcion + '\'' +
                ", completada=" + completada +
                '}';
    }
}
