package com.fabrefrederic.technique.spring;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Voir page 71 Les classes utilisées comme des types de propriétés n'ont pas forcément toutes vocation à être gérées
 * sous forme de Beans par le conteneur léger. Il peut être plus intéressant de les traiter comme des valeurs simples,
 * initialisées via une chaîne de caractères. Inconnus de Spring, ces types doivent être accompagnés d'un transcodeur, à
 * même de faire la conversion entre la chaîne de caractères et les attributs du type.
 * 
 * @author frederic.fabre
 * 
 */
public class DatePropertyEditor extends PropertyEditorSupport {
    private String pattern = "yyyy-MM-dd";

    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        try {
            final Date date = new SimpleDateFormat(pattern).parse(text);
            setValue(date);
        } catch (final ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 
     * @param pattern
     */
    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }
}
