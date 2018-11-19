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
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "Comunidad.findAll", query = "SELECT c FROM Comunidad c")
    , @NamedQuery(name = "Comunidad.findByIdComunidad", query = "SELECT c FROM Comunidad c WHERE c.idComunidad = :idComunidad")
    , @NamedQuery(name = "Comunidad.findByNombre", query = "SELECT c FROM Comunidad c WHERE c.nombre = :nombre")})
public class Comunidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer idComunidad;
    @Size(max = 45)
    @Column(length = 45)
    private String nombre;
    @JoinColumn(name = "localidad", referencedColumnName = "idLocalidad")
    @ManyToOne
    private Localidad localidad;
    @OneToMany(mappedBy = "comunidad")
    private Collection<Usuario> usuarioCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "comunidad")
    private ComunidadProyecto comunidadProyecto;

    public Comunidad() {
    }

    public Comunidad(Integer idComunidad) {
        this.idComunidad = idComunidad;
    }

    public Integer getIdComunidad() {
        return idComunidad;
    }

    public void setIdComunidad(Integer idComunidad) {
        this.idComunidad = idComunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public ComunidadProyecto getComunidadProyecto() {
        return comunidadProyecto;
    }

    public void setComunidadProyecto(ComunidadProyecto comunidadProyecto) {
        this.comunidadProyecto = comunidadProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComunidad != null ? idComunidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comunidad)) {
            return false;
        }
        Comunidad other = (Comunidad) object;
        if ((this.idComunidad == null && other.idComunidad != null) || (this.idComunidad != null && !this.idComunidad.equals(other.idComunidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Comunidad[ idComunidad=" + idComunidad + " ]";
    }
    
}
