/**
 * 
 */
package pagecode;

import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlInputText;

/**
 * @author Administrador
 *
 */
public class SigeItrRepInterrupcionPorIndiceContinuidadServicio extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlMessages messages1;
	protected HtmlOutputText txtError;
	protected HtmlCommandExButton button1;
	protected HtmlCommandExButton btnCancelar;
	protected HtmlOutputText text0;
	protected HtmlOutputText text2;
	protected HtmlSelectOneMenu cboMesFinal;
	protected HtmlMessage message3;
	protected HtmlSelectOneMenu cboMesInicial;
	protected HtmlInputText txtAnoInicial;
	protected HtmlMessage message5;
	protected HtmlInputText txtAnoFinal;
	protected HtmlMessage message4;
	protected HtmlOutputText text6;

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

	protected HtmlMessages getMessages1() {
		if (messages1 == null) {
			messages1 = (HtmlMessages) findComponentInRoot("messages1");
		}
		return messages1;
	}

	protected HtmlOutputText getTxtError() {
		if (txtError == null) {
			txtError = (HtmlOutputText) findComponentInRoot("txtError");
		}
		return txtError;
	}

	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
	}

	protected HtmlCommandExButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = (HtmlCommandExButton) findComponentInRoot("btnCancelar");
		}
		return btnCancelar;
	}

	protected HtmlOutputText getText0() {
		if (text0 == null) {
			text0 = (HtmlOutputText) findComponentInRoot("text0");
		}
		return text0;
	}

	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}

	protected HtmlSelectOneMenu getCboMesFinal() {
		if (cboMesFinal == null) {
			cboMesFinal = (HtmlSelectOneMenu) findComponentInRoot("cboMesFinal");
		}
		return cboMesFinal;
	}

	protected HtmlMessage getMessage3() {
		if (message3 == null) {
			message3 = (HtmlMessage) findComponentInRoot("message3");
		}
		return message3;
	}

	protected HtmlSelectOneMenu getCboMesInicial() {
		if (cboMesInicial == null) {
			cboMesInicial = (HtmlSelectOneMenu) findComponentInRoot("cboMesInicial");
		}
		return cboMesInicial;
	}

	protected HtmlInputText getTxtAnoInicial() {
		if (txtAnoInicial == null) {
			txtAnoInicial = (HtmlInputText) findComponentInRoot("txtAnoInicial");
		}
		return txtAnoInicial;
	}

	protected HtmlMessage getMessage5() {
		if (message5 == null) {
			message5 = (HtmlMessage) findComponentInRoot("message5");
		}
		return message5;
	}

	protected HtmlInputText getTxtAnoFinal() {
		if (txtAnoFinal == null) {
			txtAnoFinal = (HtmlInputText) findComponentInRoot("txtAnoFinal");
		}
		return txtAnoFinal;
	}

	protected HtmlMessage getMessage4() {
		if (message4 == null) {
			message4 = (HtmlMessage) findComponentInRoot("message4");
		}
		return message4;
	}

	protected HtmlOutputText getText6() {
		if (text6 == null) {
			text6 = (HtmlOutputText) findComponentInRoot("text6");
		}
		return text6;
	}

}