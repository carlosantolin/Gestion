package domain;

import javax.persistence.*;

@Entity
@Table(name="Horario", schema = "tiempo")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Empleado asignado;

    @OneToOne
    @JoinColumn(name = "tarea_id", referencedColumnName = "id")
    private Tarea tarea;

    private int horas;

    public Horario() {}

    public Horario(Empleado asignado, Tarea tarea, int horas) {
        this.asignado = asignado;
        this.tarea = tarea;
        this.horas = horas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Empleado getAsignado() {
        return asignado;
    }

    public void setAsignado(Empleado asignado) {
        this.asignado = asignado;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;

        if (id != horario.id) return false;
        if (horas != horario.horas) return false;
        if (asignado != null ? !asignado.equals(horario.asignado) : horario.asignado != null) return false;
        return tarea != null ? tarea.equals(horario.tarea) : horario.tarea == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (asignado != null ? asignado.hashCode() : 0);
        result = 31 * result + (tarea != null ? tarea.hashCode() : 0);
        result = 31 * result + horas;
        return result;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "id=" + id +
                ", asignado=" + asignado +
                ", tarea=" + tarea +
                ", horas=" + horas +
                '}';
    }
}
