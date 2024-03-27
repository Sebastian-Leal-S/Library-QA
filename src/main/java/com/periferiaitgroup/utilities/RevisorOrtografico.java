package com.periferiaitgroup.utilities;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;
import org.languagetool.rules.RuleMatch;

import java.util.List;

public class RevisorOrtografico {

    private RevisorOrtografico() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Realiza una verificación ortográfica del texto proporcionado utilizando la biblioteca JLanguageTool para el idioma español.
     * Devuelve una lista de coincidencias de reglas ortográficas.
     *
     * @param text El texto que se va a verificar ortográficamente.
     * @return Una lista de objetos RuleMatch que representan las coincidencias de reglas ortográficas encontradas.
     * Devuelve null si ocurre algún error durante el proceso de verificación.
     */
    public static List<RuleMatch> spellCheck(String text) {
        try {
            JLanguageTool langTool = new JLanguageTool(new Spanish());
            List<RuleMatch> matches = langTool.check(text);
            return matches;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
