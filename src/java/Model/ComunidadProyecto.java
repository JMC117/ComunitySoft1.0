/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Montañez
 */
@Entity
@Table(name = "Comunidad_Proyecto", catalog = "IngWeb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComunidadProyecto.findAll", query = "SELECT c FROM ComunidadProyecto c")
    , @NamedQuery(name = "ComunidadProyecto.findByComunidadfk", query = "SELECT c FROM ComunidadProyecto c WHERE c.comunidadfk = :comunidadfk")})
public class ComunidadProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer comunidadfk;
    @JoinColumn(name = "comunidadfk", referencedColumnName = "idComunidad", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Comunidad comunidad;
    @JoinColumn(name = "proyectofk", referencedColumnName = "idProyecto")
    @ManyToOne
    private Proyecto proyectofk;

    public ComunidadProyecto() {
    }

    public ComunidadProyecto(Integer comunidadfk) {
        this.comunidadfk = comunidadfk;
    }

    public Integer getComunidadfk() {
        return comunidadfk;
    }

    public void setComunidadfk(Integer comunidadfk) {
        this.comunidadfk = comunidadfk;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public Proyecto getProyectofk() {
        return proyectofk;
    }

    public void setProyectofk(Proyecto proyectofk) {
        this.proyectofk = proyectofk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comunidadfk != null ? comunidadfk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComunidadProyecto)) {
            return false;
        }
        ComunidadProyecto other = (ComunidadProyecto) object;
        if ((this.comunidadfk == null && other.comunidadfk != null) || (this.comunidadfk != null && !this.comunidadfk.equals(other.comunidadfk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.ComunidadProyecto[ comunidadfk=" + comunidadfk + " ]";
    }
    
}
