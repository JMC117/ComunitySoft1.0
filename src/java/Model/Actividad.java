/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Montañez
 */
@Entity
@Table(catalog = "IngWeb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a")
    , @NamedQuery(name = "Actividad.findByIdActividad", query = "SELECT a FROM Actividad a WHERE a.idActividad = :idActividad")
    , @NamedQuery(name = "Actividad.findByLugar", query = "SELECT a FROM Actividad a WHERE a.lugar = :lugar")})
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer idActividad;
    @Size(max = 70)
    @Column(length = 70)
    private String lugar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividad")
    private Collection<Donaciones> donacionesCollection;
    @JoinColumn(name = "proyectofk", referencedColumnName = "idProyecto")
    @ManyToOne
    private Proyecto proyectofk;

    public Actividad() {
    }

    public Actividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @XmlTransient
    public Collection<Donaciones> getDonacionesCollection() {
        return donacionesCollection;
    }

    public void setDonacionesCollection(Collection<Donaciones> donacionesCollection) {
        this.donacionesCollection = donacionesCollection;
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
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Actividad[ idActividad=" + idActividad + " ]";
    }
    
}
