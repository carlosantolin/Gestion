package base.domain;

import javax.persistence.*;

@Entity
@Table(name="jefe", schema = "tiempo")
public class Jefe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;

    public Jefe() {}

    public Jefe(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jefe jefe = (Jefe) o;

        if (id != jefe.id) return false;
        return nombre != null ? nombre.equals(jefe.nombre) : jefe.nombre == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Jefe{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
