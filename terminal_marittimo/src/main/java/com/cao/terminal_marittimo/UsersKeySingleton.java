package com.cao.terminal_marittimo;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class UsersKeySingleton {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static UsersKeySingleton instance = null;
    private ArrayList<String> clienteKeys;
    private ArrayList<String> fornitoreKeys;
    private ArrayList<String> autistaKeys;
    private ArrayList<String> adminKeys;


    private UsersKeySingleton() {
        clienteKeys = new ArrayList<String>();
        fornitoreKeys = new ArrayList<String>();
        autistaKeys = new ArrayList<String>();
        adminKeys = new ArrayList<String>();
    }
    public static String makeKey(String nome){
        return Jwts.builder()
                .setSubject(nome) // Imposta il nome dell'utente come soggetto
                .setIssuedAt(new Date()) // Data di emissione
                 // Scadenza (24 ore)
                 .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SECRET_KEY) // Firma il token con la chiave segreta
                .compact();
    }
    public static boolean isKeyExpired(String key) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(key);
            return false; // Il token è valido
        } catch (Exception e) {
            return true; // Il token è scaduto o non valido
        }
    }
    public static synchronized UsersKeySingleton getInstance() {
        if (instance == null) {
            instance = new UsersKeySingleton();
        }
        return instance;
    }

    public synchronized void addClienteKey(String key) {
        clienteKeys.add(key);
    }

    public synchronized void addFornitoreKey(String key) {
        fornitoreKeys.add(key);
    }

    public synchronized void addAutistaKey(String key) {
        autistaKeys.add(key);
    }

    public synchronized void addAdminKey(String key) {
        adminKeys.add(key);
    }
    public synchronized String checkAuthorization(String key) {
        if(isKeyExpired(key)) {
            return null;
        }
        if (clienteKeys.contains(key)) {
            return "cliente";
        } else if (fornitoreKeys.contains(key)) {
            return "fornitore";
        } else if (autistaKeys.contains(key)) {
            return "autista";
        } else if (adminKeys.contains(key)) {
            return "admin";
        }
        return null;
    }

}