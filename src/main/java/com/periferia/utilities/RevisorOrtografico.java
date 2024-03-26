package com.periferia.utilities;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;
import org.languagetool.rules.RuleMatch;

import java.util.List;

public class RevisorOrtografico {

    private RevisorOrtografico() {
        throw new IllegalStateException("Utility class");
    }

    public static void checking(String text) {
        try {
            JLanguageTool langTool = new JLanguageTool(new Spanish());
            List<RuleMatch> matches = langTool.check(text);

            for (RuleMatch match : matches) {
                int inicio = match.getFromPos();
                int fin = match.getToPos();
                String error = text.substring(inicio, fin);
                System.out.println("Palabra con error: " + error);
                System.out.println("Sugerencias de correciones: " + match.getSuggestedReplacements());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
