package domain;

import dao.Tarea;

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