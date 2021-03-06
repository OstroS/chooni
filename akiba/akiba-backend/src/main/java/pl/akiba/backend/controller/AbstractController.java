package pl.akiba.backend.controller;

/**
 * 
 * @author sobczakt
 */
public class AbstractController {

    protected static final char[] FORBIDDEN_CHARS = { '<', '>', '"', '\'', '%', ';', '&', '*', '?' };

    protected static final String AUTH_CODE_HEADER = "x-akiba-auth-code";
}
