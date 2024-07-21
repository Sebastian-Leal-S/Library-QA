package com.periferiaitgroup.utilities;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;
import org.languagetool.rules.RuleMatch;

import java.util.List;

/**
 * Clase utilitaria para realizar verificaciones ortográficas utilizando la biblioteca JLanguageTool para el idioma español.
 */
public class RevisorOrtografico {

	/**
     * Constructor privado para prevenir la instanciación de la clase utilitaria.
     * 
     * @throws IllegalStateException si se intenta instanciar la clase.
     */
    private RevisorOrtografico() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Verifica la ortografía de un texto en español y retorna una lista de coincidencias de reglas que identifican los errores.
     * 
     * @param text el texto a verificar ortográficamente.
     * @return una lista de coincidencias de reglas (RuleMatch) que contienen los errores ortográficos encontrados en el texto.
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