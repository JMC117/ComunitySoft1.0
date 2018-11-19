/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juan Montañez
 */
@Embeddable
public class DonacionesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int usuariofk;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int actividadfk;

    public DonacionesPK() {
    }

    public DonacionesPK(int usuariofk, int actividadfk) {
        this.usuariofk = usuariofk;
        this.actividadfk = actividadfk;
    }

    public int getUsuariofk() {
        return usuariofk;
    }

    public void setUsuariofk(int usuariofk) {
        this.usuariofk = usuariofk;
    }

    public int getActividadfk() {
        return actividadfk;
    }

    public void setActividadfk(int actividadfk) {
        this.actividadfk = actividadfk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuariofk;
        hash += (int) actividadfk;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DonacionesPK)) {
            return false;
        }
        DonacionesPK other = (DonacionesPK) object;
        if (this.usuariofk != other.usuariofk) {
            return false;
        }
        if (this.actividadfk != other.actividadfk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.DonacionesPK[ usuariofk=" + usuariofk + ", actividadfk=" + actividadfk + " ]";
    }
    
}
