package org.se2.ai.control;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.DTO.KundeDTO;
import org.se2.ai.model.dao.BenutzerDAO;
import org.se2.ai.model.DTO.VertrieblerDTO;
import org.se2.ai.model.dao.KundeDAO;
import org.se2.ai.model.dao.VertrieblerDAO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.ui.MyUI;
import com.vaadin.ui.UI;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zmorin2s
 */

public class RegistrationControl {
    Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    public boolean checkUserExists(String email) throws DatabaseException {
        return BenutzerDAO.getInstance().checkUserExists(email);
    }

    /**
     * @param email    - String
     * @param password - String of the password entered in the form
     * @return boolean
     * @throws DatabaseException When murphy is around
     */
    public boolean registerUser(String email, String password, String role) throws DatabaseException {
        boolean erg = BenutzerDAO.getInstance().createBenutzer(email, password, role);
        sendConfirmationEmail(email);
        return erg;
    }

    public void registerVertriebler(VertrieblerDTO v, String anrede) {
        VertrieblerDAO.getInstance().insertVertriebler(v);
        BenutzerDAO.getInstance().updateStammdaten(v, anrede, user);

    }

    /**
     * register a student user
     *
     * @param kunde - A DTO of type Kunde
     */
    public void registerKunde(KundeDTO kunde) {

        try {
            KundeDAO.getInstance().newKunde(kunde);
            BenutzerDAO.getInstance().updateStammdaten(kunde, kunde.getAnrede(), user);
        } catch (Exception e) {
            Logger.getLogger(RegistrationControl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void sendConfirmationEmail(String email) {
        HtmlEmail mail = new HtmlEmail();

        mail.setHostName("smtp.mailtrap.io");
        mail.setSmtpPort(587);
        mail.setSSLOnConnect(false);
        mail.setAuthentication("fc96262875e64b", "556759cf419b54");

        try {
            mail.setFrom("moinsen@test.com");
            mail.addTo(email);
            mail.setSubject("Willkommen im CarLook Portal");
            mail.setHtmlMsg("Hallo! Sie haben Ihr Konto erfolgreich erstellt!<br>Ab jetzt steht Ihnen das Portal zur Verfügung.");
            mail.send();
        } catch (EmailException e) {
            Logger.getLogger(RegistrationControl.class.getName()).log(Level.SEVERE, "Failed to send an email", e);
        }
    }
}

