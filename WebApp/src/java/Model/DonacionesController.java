package Model;

import Model.util.JsfUtil;
import Model.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("donacionesController")
@SessionScoped
public class DonacionesController implements Serializable {

    @EJB
    private Model.DonacionesFacade ejbFacade;
    private List<Donaciones> items = null;
    private Donaciones selected;

    public DonacionesController() {
    }

    public Donaciones getSelected() {
        return selected;
    }

    public void setSelected(Donaciones selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setDonacionesPK(new Model.DonacionesPK());
    }

    private DonacionesFacade getFacade() {
        return ejbFacade;
    }

    public Donaciones prepareCreate() {
        selected = new Donaciones();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DonacionesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DonacionesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DonacionesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Donaciones> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Donaciones getDonaciones(Model.DonacionesPK id) {
        return getFacade().find(id);
    }

    public List<Donaciones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Donaciones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Donaciones.class)
    public static class DonacionesControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DonacionesController controller = (DonacionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "donacionesController");
            return controller.getDonaciones(getKey(value));
        }

        Model.DonacionesPK getKey(String value) {
            Model.DonacionesPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Model.DonacionesPK();
            key.setUsuariofk(Integer.parseInt(values[0]));
            key.setActividadfk(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(Model.DonacionesPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUsuariofk());
            sb.append(SEPARATOR);
            sb.append(value.getActividadfk());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Donaciones) {
                Donaciones o = (Donaciones) object;
                return getStringKey(o.getDonacionesPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Donaciones.class.getName()});
                return null;
            }
        }

    }

}
