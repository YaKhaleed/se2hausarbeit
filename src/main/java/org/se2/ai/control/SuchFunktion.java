package org.se2.ai.control;

/**
 * @author qthi2s
 */

import org.se2.ai.model.DTO.AutoanzeigeDTO;

import java.util.List;


public interface SuchFunktion {

    List<AutoanzeigeDTO> getAutoanzeigeListe(String titel);


}
