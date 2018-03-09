package pagecode;

import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.UIColumn;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlMessages;

/**
 * <p>TODO <<Interface|Clase>> pagecode.SigeItrCambioSecciones.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>SigeItrCambioSecciones.java</code>.</p>
 * <p>Fecha creación: 26/09/2012</p>
 * <p>Última actualización: 26/09/2012</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class SigeItrCambioSecciones extends PageCodeBase {

    protected HtmlOutputText text6;
    protected HtmlScriptCollector scriptCollector1;
    protected HtmlForm form1;
    protected HtmlOutputText text1;
    protected HtmlMessage message12;
    protected HtmlOutputText lblSeccionD;
    protected HtmlSelectOneMenu cboSeccionD;
    protected HtmlMessage message11;
    protected HtmlSelectOneMenu cboCircuitoD;
    protected HtmlOutputText lblCircuitoD;
    protected HtmlMessage message10;
    protected HtmlSelectOneMenu cboSubestacionD;
    protected HtmlOutputText lblSubestacionD;
    protected HtmlSelectOneMenu cboSubregionD;
    protected HtmlOutputText lblSubregionD;
    protected HtmlMessage message9;
    protected HtmlMessage message8;
    protected HtmlSelectOneMenu cboRegionD;
    protected HtmlOutputText lblRegionD;
    protected HtmlMessage message7;
    protected HtmlSelectOneMenu cboOficinaD;
    protected HtmlOutputText text2;
    protected HtmlOutputText txtOrigen;
    protected HtmlOutputText txtDestino;
    protected HtmlSelectOneMenu cboOficina;
    protected HtmlOutputText lblRegion;
    protected HtmlSelectOneMenu cboRegion;
    protected HtmlOutputText lblSubregion;
    protected HtmlSelectOneMenu cboSubregion;
    protected HtmlOutputText lblSubestacion;
    protected HtmlSelectOneMenu cboSubestacion;
    protected HtmlOutputText lblCircuito;
    protected HtmlSelectOneMenu cboCircuito;
    protected HtmlOutputText lblSeccion;
    protected HtmlSelectOneMenu cboSeccion;
    protected HtmlMessage message1;
    protected HtmlMessage message2;
    protected HtmlMessage message3;
    protected HtmlMessage message4;
    protected HtmlMessage message5;
    protected HtmlMessage message6;
    protected HtmlSelectOneRadio chkTipo;
    protected HtmlDataTable table1;
    protected HtmlOutputText lblNombre;
    protected HtmlOutputText lblSubregio;
    protected HtmlOutputText lblOficina;
    protected HtmlOutputText txtOficina;
    protected UIColumn col2;
    protected HtmlOutputText txtRegion;
    protected UIColumn col3;
    protected HtmlOutputText txtSubregion;
    protected UIColumn col4;
    protected HtmlOutputText txtSubestacion;
    protected UIColumn col5;
    protected HtmlOutputText txtCircuito;
    protected UIColumn col6;
    protected HtmlOutputText txtSeccion;
    protected UIColumn col7;
    protected HtmlOutputText lblKm;
    protected HtmlOutputText txtKm;
    protected UIColumn col8;
    protected HtmlOutputText lblClientes;
    protected HtmlOutputText txtClientes;
    protected UIColumn col9;
    protected HtmlOutputText lblBorrar;
    protected UIColumn col11;
    protected UIColumn col22;
    protected UIColumn col33;
    protected UIColumn col44;
    protected UIColumn col55;
    protected UIColumn col66;
    protected UIColumn col77;
    protected UIColumn col88;
    protected UIColumn col99;
    protected HtmlOutputText lblBorrar1;
    protected HtmlOutputText lblClientes1;
    protected HtmlOutputText txtClientes1;
    protected HtmlOutputText txtKm1;
    protected HtmlOutputText lblKm1;
    protected HtmlOutputText txtSeccion1;
    protected HtmlOutputText lblSeccion1;
    protected HtmlOutputText txtCircuito1;
    protected HtmlOutputText lblCircuito1;
    protected HtmlOutputText txtSubestacion1;
    protected HtmlOutputText lblSubestacion1;
    protected HtmlOutputText txtSubregion1;
    protected HtmlOutputText lblSubregion1;
    protected HtmlOutputText txtRegion1;
    protected HtmlOutputText lblRegion1;
    protected HtmlOutputText txtOficina1;
    protected HtmlOutputText lblOficina1;
    protected HtmlDataTable table2;
    protected HtmlCommandExButton btnAgregarSeccO;
    protected HtmlCommandExButton btnAgregarSeccD;
    protected HtmlInputText txtKmD;
    protected HtmlOutputText lblKmD;
    protected HtmlOutputText lblClientesD;
    protected HtmlInputText txtClientesD;
    protected HtmlOutputText lblKmO;
    protected HtmlInputText txtKmO;
    protected HtmlOutputText lblClientesO;
    protected HtmlInputText txtClientesO;
    protected HtmlCommandExButton btnGrabar;
    protected HtmlMessage messageBtnO;
    protected HtmlMessage messageBtnD;
    protected HtmlMessages messagesG;
    protected UIColumn col1;
    protected HtmlOutputText getText6() {
        if (text6 == null) {
            text6 = (HtmlOutputText) findComponentInRoot("text6");
        }
        return text6;
    }

    protected HtmlScriptCollector getScriptCollector1() {
        if (scriptCollector1 == null) {
            scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
        }
        return scriptCollector1;
    }

    protected HtmlForm getForm1() {
        if (form1 == null) {
            form1 = (HtmlForm) findComponentInRoot("form1");
        }
        return form1;
    }

    protected HtmlOutputText getText1() {
        if (text1 == null) {
            text1 = (HtmlOutputText) findComponentInRoot("text1");
        }
        return text1;
    }

    protected HtmlMessage getMessage12() {
        if (message12 == null) {
            message12 = (HtmlMessage) findComponentInRoot("message12");
        }
        return message12;
    }

    protected HtmlOutputText getLblSeccionD() {
        if (lblSeccionD == null) {
            lblSeccionD = (HtmlOutputText) findComponentInRoot("lblSeccionD");
        }
        return lblSeccionD;
    }

    protected HtmlSelectOneMenu getCboSeccionD() {
        if (cboSeccionD == null) {
            cboSeccionD = (HtmlSelectOneMenu) findComponentInRoot("cboSeccionD");
        }
        return cboSeccionD;
    }

    protected HtmlMessage getMessage11() {
        if (message11 == null) {
            message11 = (HtmlMessage) findComponentInRoot("message11");
        }
        return message11;
    }

    protected HtmlSelectOneMenu getCboCircuitoD() {
        if (cboCircuitoD == null) {
            cboCircuitoD = (HtmlSelectOneMenu) findComponentInRoot("cboCircuitoD");
        }
        return cboCircuitoD;
    }

    protected HtmlOutputText getLblCircuitoD() {
        if (lblCircuitoD == null) {
            lblCircuitoD = (HtmlOutputText) findComponentInRoot("lblCircuitoD");
        }
        return lblCircuitoD;
    }

    protected HtmlMessage getMessage10() {
        if (message10 == null) {
            message10 = (HtmlMessage) findComponentInRoot("message10");
        }
        return message10;
    }

    protected HtmlSelectOneMenu getCboSubestacionD() {
        if (cboSubestacionD == null) {
            cboSubestacionD = (HtmlSelectOneMenu) findComponentInRoot("cboSubestacionD");
        }
        return cboSubestacionD;
    }

    protected HtmlOutputText getLblSubestacionD() {
        if (lblSubestacionD == null) {
            lblSubestacionD = (HtmlOutputText) findComponentInRoot("lblSubestacionD");
        }
        return lblSubestacionD;
    }

    protected HtmlSelectOneMenu getCboSubregionD() {
        if (cboSubregionD == null) {
            cboSubregionD = (HtmlSelectOneMenu) findComponentInRoot("cboSubregionD");
        }
        return cboSubregionD;
    }

    protected HtmlOutputText getLblSubregionD() {
        if (lblSubregionD == null) {
            lblSubregionD = (HtmlOutputText) findComponentInRoot("lblSubregionD");
        }
        return lblSubregionD;
    }

    protected HtmlMessage getMessage9() {
        if (message9 == null) {
            message9 = (HtmlMessage) findComponentInRoot("message9");
        }
        return message9;
    }

    protected HtmlMessage getMessage8() {
        if (message8 == null) {
            message8 = (HtmlMessage) findComponentInRoot("message8");
        }
        return message8;
    }

    protected HtmlSelectOneMenu getCboRegionD() {
        if (cboRegionD == null) {
            cboRegionD = (HtmlSelectOneMenu) findComponentInRoot("cboRegionD");
        }
        return cboRegionD;
    }

    protected HtmlOutputText getLblRegionD() {
        if (lblRegionD == null) {
            lblRegionD = (HtmlOutputText) findComponentInRoot("lblRegionD");
        }
        return lblRegionD;
    }

    protected HtmlMessage getMessage7() {
        if (message7 == null) {
            message7 = (HtmlMessage) findComponentInRoot("message7");
        }
        return message7;
    }

    protected HtmlSelectOneMenu getCboOficinaD() {
        if (cboOficinaD == null) {
            cboOficinaD = (HtmlSelectOneMenu) findComponentInRoot("cboOficinaD");
        }
        return cboOficinaD;
    }

    protected HtmlOutputText getText2() {
        if (text2 == null) {
            text2 = (HtmlOutputText) findComponentInRoot("text2");
        }
        return text2;
    }

    protected HtmlOutputText getTxtOrigen() {
        if (txtOrigen == null) {
            txtOrigen = (HtmlOutputText) findComponentInRoot("txtOrigen");
        }
        return txtOrigen;
    }

    protected HtmlOutputText getTxtDestino() {
        if (txtDestino == null) {
            txtDestino = (HtmlOutputText) findComponentInRoot("txtDestino");
        }
        return txtDestino;
    }

    protected HtmlSelectOneMenu getCboOficina() {
        if (cboOficina == null) {
            cboOficina = (HtmlSelectOneMenu) findComponentInRoot("cboOficina");
        }
        return cboOficina;
    }

    protected HtmlOutputText getLblRegion() {
        if (lblRegion == null) {
            lblRegion = (HtmlOutputText) findComponentInRoot("lblRegion");
        }
        return lblRegion;
    }

    protected HtmlSelectOneMenu getCboRegion() {
        if (cboRegion == null) {
            cboRegion = (HtmlSelectOneMenu) findComponentInRoot("cboRegion");
        }
        return cboRegion;
    }

    protected HtmlOutputText getLblSubregion() {
        if (lblSubregion == null) {
            lblSubregion = (HtmlOutputText) findComponentInRoot("lblSubregion");
        }
        return lblSubregion;
    }

    protected HtmlSelectOneMenu getCboSubregion() {
        if (cboSubregion == null) {
            cboSubregion = (HtmlSelectOneMenu) findComponentInRoot("cboSubregion");
        }
        return cboSubregion;
    }

    protected HtmlOutputText getLblSubestacion() {
        if (lblSubestacion == null) {
            lblSubestacion = (HtmlOutputText) findComponentInRoot("lblSubestacion");
        }
        return lblSubestacion;
    }

    protected HtmlSelectOneMenu getCboSubestacion() {
        if (cboSubestacion == null) {
            cboSubestacion = (HtmlSelectOneMenu) findComponentInRoot("cboSubestacion");
        }
        return cboSubestacion;
    }

    protected HtmlOutputText getLblCircuito() {
        if (lblCircuito == null) {
            lblCircuito = (HtmlOutputText) findComponentInRoot("lblCircuito");
        }
        return lblCircuito;
    }

    protected HtmlSelectOneMenu getCboCircuito() {
        if (cboCircuito == null) {
            cboCircuito = (HtmlSelectOneMenu) findComponentInRoot("cboCircuito");
        }
        return cboCircuito;
    }

    protected HtmlOutputText getLblSeccion() {
        if (lblSeccion == null) {
            lblSeccion = (HtmlOutputText) findComponentInRoot("lblSeccion");
        }
        return lblSeccion;
    }

    protected HtmlSelectOneMenu getCboSeccion() {
        if (cboSeccion == null) {
            cboSeccion = (HtmlSelectOneMenu) findComponentInRoot("cboSeccion");
        }
        return cboSeccion;
    }

    protected HtmlMessage getMessage1() {
        if (message1 == null) {
            message1 = (HtmlMessage) findComponentInRoot("message1");
        }
        return message1;
    }

    protected HtmlMessage getMessage2() {
        if (message2 == null) {
            message2 = (HtmlMessage) findComponentInRoot("message2");
        }
        return message2;
    }

    protected HtmlMessage getMessage3() {
        if (message3 == null) {
            message3 = (HtmlMessage) findComponentInRoot("message3");
        }
        return message3;
    }

    protected HtmlMessage getMessage4() {
        if (message4 == null) {
            message4 = (HtmlMessage) findComponentInRoot("message4");
        }
        return message4;
    }

    protected HtmlMessage getMessage5() {
        if (message5 == null) {
            message5 = (HtmlMessage) findComponentInRoot("message5");
        }
        return message5;
    }

    protected HtmlMessage getMessage6() {
        if (message6 == null) {
            message6 = (HtmlMessage) findComponentInRoot("message6");
        }
        return message6;
    }

    protected HtmlSelectOneRadio getChkTipo() {
        if (chkTipo == null) {
            chkTipo = (HtmlSelectOneRadio) findComponentInRoot("chkTipo");
        }
        return chkTipo;
    }

    protected HtmlDataTable getTable1() {
        if (table1 == null) {
            table1 = (HtmlDataTable) findComponentInRoot("table1");
        }
        return table1;
    }

    protected HtmlOutputText getLblNombre() {
        if (lblNombre == null) {
            lblNombre = (HtmlOutputText) findComponentInRoot("lblNombre");
        }
        return lblNombre;
    }

    protected HtmlOutputText getLblSubregio() {
        if (lblSubregio == null) {
            lblSubregio = (HtmlOutputText) findComponentInRoot("lblSubregio");
        }
        return lblSubregio;
    }

    protected HtmlOutputText getLblOficina() {
        if (lblOficina == null) {
            lblOficina = (HtmlOutputText) findComponentInRoot("lblOficina");
        }
        return lblOficina;
    }

    protected HtmlOutputText getTxtOficina() {
        if (txtOficina == null) {
            txtOficina = (HtmlOutputText) findComponentInRoot("txtOficina");
        }
        return txtOficina;
    }

    protected UIColumn getCol2() {
        if (col2 == null) {
            col2 = (UIColumn) findComponentInRoot("col2");
        }
        return col2;
    }

    protected HtmlOutputText getTxtRegion() {
        if (txtRegion == null) {
            txtRegion = (HtmlOutputText) findComponentInRoot("txtRegion");
        }
        return txtRegion;
    }

    protected UIColumn getCol3() {
        if (col3 == null) {
            col3 = (UIColumn) findComponentInRoot("col3");
        }
        return col3;
    }

    protected HtmlOutputText getTxtSubregion() {
        if (txtSubregion == null) {
            txtSubregion = (HtmlOutputText) findComponentInRoot("txtSubregion");
        }
        return txtSubregion;
    }

    protected UIColumn getCol4() {
        if (col4 == null) {
            col4 = (UIColumn) findComponentInRoot("col4");
        }
        return col4;
    }

    protected HtmlOutputText getTxtSubestacion() {
        if (txtSubestacion == null) {
            txtSubestacion = (HtmlOutputText) findComponentInRoot("txtSubestacion");
        }
        return txtSubestacion;
    }

    protected UIColumn getCol5() {
        if (col5 == null) {
            col5 = (UIColumn) findComponentInRoot("col5");
        }
        return col5;
    }

    protected HtmlOutputText getTxtCircuito() {
        if (txtCircuito == null) {
            txtCircuito = (HtmlOutputText) findComponentInRoot("txtCircuito");
        }
        return txtCircuito;
    }

    protected UIColumn getCol6() {
        if (col6 == null) {
            col6 = (UIColumn) findComponentInRoot("col6");
        }
        return col6;
    }

    protected HtmlOutputText getTxtSeccion() {
        if (txtSeccion == null) {
            txtSeccion = (HtmlOutputText) findComponentInRoot("txtSeccion");
        }
        return txtSeccion;
    }

    protected UIColumn getCol7() {
        if (col7 == null) {
            col7 = (UIColumn) findComponentInRoot("col7");
        }
        return col7;
    }

    protected HtmlOutputText getLblKm() {
        if (lblKm == null) {
            lblKm = (HtmlOutputText) findComponentInRoot("lblKm");
        }
        return lblKm;
    }

    protected HtmlOutputText getTxtKm() {
        if (txtKm == null) {
            txtKm = (HtmlOutputText) findComponentInRoot("txtKm");
        }
        return txtKm;
    }

    protected UIColumn getCol8() {
        if (col8 == null) {
            col8 = (UIColumn) findComponentInRoot("col8");
        }
        return col8;
    }

    protected HtmlOutputText getLblClientes() {
        if (lblClientes == null) {
            lblClientes = (HtmlOutputText) findComponentInRoot("lblClientes");
        }
        return lblClientes;
    }

    protected HtmlOutputText getTxtClientes() {
        if (txtClientes == null) {
            txtClientes = (HtmlOutputText) findComponentInRoot("txtClientes");
        }
        return txtClientes;
    }

    protected UIColumn getCol9() {
        if (col9 == null) {
            col9 = (UIColumn) findComponentInRoot("col9");
        }
        return col9;
    }

    protected HtmlOutputText getLblBorrar() {
        if (lblBorrar == null) {
            lblBorrar = (HtmlOutputText) findComponentInRoot("lblBorrar");
        }
        return lblBorrar;
    }

    protected UIColumn getCol11() {
        if (col11 == null) {
            col11 = (UIColumn) findComponentInRoot("col11");
        }
        return col11;
    }

    protected UIColumn getCol22() {
        if (col22 == null) {
            col22 = (UIColumn) findComponentInRoot("col22");
        }
        return col22;
    }

    protected UIColumn getCol33() {
        if (col33 == null) {
            col33 = (UIColumn) findComponentInRoot("col33");
        }
        return col33;
    }

    protected UIColumn getCol44() {
        if (col44 == null) {
            col44 = (UIColumn) findComponentInRoot("col44");
        }
        return col44;
    }

    protected UIColumn getCol55() {
        if (col55 == null) {
            col55 = (UIColumn) findComponentInRoot("col55");
        }
        return col55;
    }

    protected UIColumn getCol66() {
        if (col66 == null) {
            col66 = (UIColumn) findComponentInRoot("col66");
        }
        return col66;
    }

    protected UIColumn getCol77() {
        if (col77 == null) {
            col77 = (UIColumn) findComponentInRoot("col77");
        }
        return col77;
    }

    protected UIColumn getCol88() {
        if (col88 == null) {
            col88 = (UIColumn) findComponentInRoot("col88");
        }
        return col88;
    }

    protected UIColumn getCol99() {
        if (col99 == null) {
            col99 = (UIColumn) findComponentInRoot("col99");
        }
        return col99;
    }

    protected HtmlOutputText getLblBorrar1() {
        if (lblBorrar1 == null) {
            lblBorrar1 = (HtmlOutputText) findComponentInRoot("lblBorrar1");
        }
        return lblBorrar1;
    }

    protected HtmlOutputText getLblClientes1() {
        if (lblClientes1 == null) {
            lblClientes1 = (HtmlOutputText) findComponentInRoot("lblClientes1");
        }
        return lblClientes1;
    }

    protected HtmlOutputText getTxtClientes1() {
        if (txtClientes1 == null) {
            txtClientes1 = (HtmlOutputText) findComponentInRoot("txtClientes1");
        }
        return txtClientes1;
    }

    protected HtmlOutputText getTxtKm1() {
        if (txtKm1 == null) {
            txtKm1 = (HtmlOutputText) findComponentInRoot("txtKm1");
        }
        return txtKm1;
    }

    protected HtmlOutputText getLblKm1() {
        if (lblKm1 == null) {
            lblKm1 = (HtmlOutputText) findComponentInRoot("lblKm1");
        }
        return lblKm1;
    }

    protected HtmlOutputText getTxtSeccion1() {
        if (txtSeccion1 == null) {
            txtSeccion1 = (HtmlOutputText) findComponentInRoot("txtSeccion1");
        }
        return txtSeccion1;
    }

    protected HtmlOutputText getLblSeccion1() {
        if (lblSeccion1 == null) {
            lblSeccion1 = (HtmlOutputText) findComponentInRoot("lblSeccion1");
        }
        return lblSeccion1;
    }

    protected HtmlOutputText getTxtCircuito1() {
        if (txtCircuito1 == null) {
            txtCircuito1 = (HtmlOutputText) findComponentInRoot("txtCircuito1");
        }
        return txtCircuito1;
    }

    protected HtmlOutputText getLblCircuito1() {
        if (lblCircuito1 == null) {
            lblCircuito1 = (HtmlOutputText) findComponentInRoot("lblCircuito1");
        }
        return lblCircuito1;
    }

    protected HtmlOutputText getTxtSubestacion1() {
        if (txtSubestacion1 == null) {
            txtSubestacion1 = (HtmlOutputText) findComponentInRoot("txtSubestacion1");
        }
        return txtSubestacion1;
    }

    protected HtmlOutputText getLblSubestacion1() {
        if (lblSubestacion1 == null) {
            lblSubestacion1 = (HtmlOutputText) findComponentInRoot("lblSubestacion1");
        }
        return lblSubestacion1;
    }

    protected HtmlOutputText getTxtSubregion1() {
        if (txtSubregion1 == null) {
            txtSubregion1 = (HtmlOutputText) findComponentInRoot("txtSubregion1");
        }
        return txtSubregion1;
    }

    protected HtmlOutputText getLblSubregion1() {
        if (lblSubregion1 == null) {
            lblSubregion1 = (HtmlOutputText) findComponentInRoot("lblSubregion1");
        }
        return lblSubregion1;
    }

    protected HtmlOutputText getTxtRegion1() {
        if (txtRegion1 == null) {
            txtRegion1 = (HtmlOutputText) findComponentInRoot("txtRegion1");
        }
        return txtRegion1;
    }

    protected HtmlOutputText getLblRegion1() {
        if (lblRegion1 == null) {
            lblRegion1 = (HtmlOutputText) findComponentInRoot("lblRegion1");
        }
        return lblRegion1;
    }

    protected HtmlOutputText getTxtOficina1() {
        if (txtOficina1 == null) {
            txtOficina1 = (HtmlOutputText) findComponentInRoot("txtOficina1");
        }
        return txtOficina1;
    }

    protected HtmlOutputText getLblOficina1() {
        if (lblOficina1 == null) {
            lblOficina1 = (HtmlOutputText) findComponentInRoot("lblOficina1");
        }
        return lblOficina1;
    }

    protected HtmlDataTable getTable2() {
        if (table2 == null) {
            table2 = (HtmlDataTable) findComponentInRoot("table2");
        }
        return table2;
    }

    protected HtmlCommandExButton getBtnAgregarSeccO() {
        if (btnAgregarSeccO == null) {
            btnAgregarSeccO = (HtmlCommandExButton) findComponentInRoot("btnAgregarSeccO");
        }
        return btnAgregarSeccO;
    }

    protected HtmlCommandExButton getBtnAgregarSeccD() {
        if (btnAgregarSeccD == null) {
            btnAgregarSeccD = (HtmlCommandExButton) findComponentInRoot("btnAgregarSeccD");
        }
        return btnAgregarSeccD;
    }

    protected HtmlInputText getTxtKmD() {
        if (txtKmD == null) {
            txtKmD = (HtmlInputText) findComponentInRoot("txtKmD");
        }
        return txtKmD;
    }

    protected HtmlOutputText getLblKmD() {
        if (lblKmD == null) {
            lblKmD = (HtmlOutputText) findComponentInRoot("lblKmD");
        }
        return lblKmD;
    }

    protected HtmlOutputText getLblClientesD() {
        if (lblClientesD == null) {
            lblClientesD = (HtmlOutputText) findComponentInRoot("lblClientesD");
        }
        return lblClientesD;
    }

    protected HtmlInputText getTxtClientesD() {
        if (txtClientesD == null) {
            txtClientesD = (HtmlInputText) findComponentInRoot("txtClientesD");
        }
        return txtClientesD;
    }

    protected HtmlOutputText getLblKmO() {
        if (lblKmO == null) {
            lblKmO = (HtmlOutputText) findComponentInRoot("lblKmO");
        }
        return lblKmO;
    }

    protected HtmlInputText getTxtKmO() {
        if (txtKmO == null) {
            txtKmO = (HtmlInputText) findComponentInRoot("txtKmO");
        }
        return txtKmO;
    }

    protected HtmlOutputText getLblClientesO() {
        if (lblClientesO == null) {
            lblClientesO = (HtmlOutputText) findComponentInRoot("lblClientesO");
        }
        return lblClientesO;
    }

    protected HtmlInputText getTxtClientesO() {
        if (txtClientesO == null) {
            txtClientesO = (HtmlInputText) findComponentInRoot("txtClientesO");
        }
        return txtClientesO;
    }

    protected HtmlCommandExButton getBtnGrabar() {
        if (btnGrabar == null) {
            btnGrabar = (HtmlCommandExButton) findComponentInRoot("btnGrabar");
        }
        return btnGrabar;
    }

    protected HtmlMessage getMessageBtnO() {
        if (messageBtnO == null) {
            messageBtnO = (HtmlMessage) findComponentInRoot("messageBtnO");
        }
        return messageBtnO;
    }

    protected HtmlMessage getMessageBtnD() {
        if (messageBtnD == null) {
            messageBtnD = (HtmlMessage) findComponentInRoot("messageBtnD");
        }
        return messageBtnD;
    }

    protected HtmlMessages getMessagesG() {
        if (messagesG == null) {
            messagesG = (HtmlMessages) findComponentInRoot("messagesG");
        }
        return messagesG;
    }

    protected UIColumn getCol1() {
        if (col1 == null) {
            col1 = (UIColumn) findComponentInRoot("col1");
        }
        return col1;
    }

}