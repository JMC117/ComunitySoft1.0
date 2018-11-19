/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Montañez
 */
@Entity
@Table(catalog = "IngWeb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Donaciones.findAll", query = "SELECT d FROM Donaciones d")
    , @NamedQuery(name = "Donaciones.findByUsuariofk", query = "SELECT d FROM Donaciones d WHERE d.donacionesPK.usuariofk = :usuariofk")
    , @NamedQuery(name = "Donaciones.findByActividadfk", query = "SELECT d FROM Donaciones d WHERE d.donacionesPK.actividadfk = :actividadfk")
    , @NamedQuery(name = "Donaciones.findByRecurso", query = "SELECT d FROM Donaciones d WHERE d.recurso = :recurso")
    , @NamedQuery(name = "Donaciones.findByTipoRecurso", query = "SELECT d FROM Donaciones d WHERE d.tipoRecurso = :tipoRecurso")
    , @NamedQuery(name = "Donaciones.findByDescripcion", query = "SELECT d FROM Donaciones d WHERE d.descripcion = :descripcion")})
public class Donaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DonacionesPK donacionesPK;
    @Size(max = 45)
    @Column(length = 45)
    private String recurso;
    @Size(max = 2)
    @Column(length = 2)
    private String tipoRecurso;
    @Size(max = 115)
    @Column(length = 115)
    private String descripcion;
    @JoinColumn(name = "actividadfk", referencedColumnName = "idActividad", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividad actividad;
    @JoinColumn(name = "usuariofk", referencedColumnName = "idUsuario", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Donaciones() {
    }

    public Donaciones(DonacionesPK donacionesPK) {
        this.donacionesPK = donacionesPK;
    }

    public Donaciones(int usuariofk, int actividadfk) {
        this.donacionesPK = new DonacionesPK(usuariofk, actividadfk);
    }

    public DonacionesPK getDonacionesPK() {
        return donacionesPK;
    }

    public void setDonacionesPK(DonacionesPK donacionesPK) {
        this.donacionesPK = donacionesPK;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (donacionesPK != null ? donacionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Donaciones)) {
            return false;
        }
        Donaciones other = (Donaciones) object;
        if ((this.donacionesPK == null && other.donacionesPK != null) || (this.donacionesPK != null && !this.donacionesPK.equals(other.donacionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Donaciones[ donacionesPK=" + donacionesPK + " ]";
    }
    
}
