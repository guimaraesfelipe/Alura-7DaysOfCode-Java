package br.com.daysofcode.helper;

import br.com.daysofcode.util.HashUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public enum Key {
    IMDB_KEY {
        @Override
        public String get() {
            try {
                return readFileWithKeys("src/main/resources/imdb_key").get(0);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    },
    MARVEL_KEY {
        @Override
        public String get() {
            String publicKey;
            String privateKey;
            String pathKey = "src/main/resources/marvel_key";

            try {
                publicKey = readFileWithKeys(pathKey).get(0);
                privateKey = readFileWithKeys(pathKey).get(1);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            String timestamp = String.valueOf(System.currentTimeMillis());
            String hash = HashUtils.getHashMd5(timestamp + privateKey + publicKey);
            return String.format("ts=%s&hash=%s&apikey=%s", timestamp, hash, publicKey);
        }
    };

    public abstract String get();

    public static List<String> readFileWithKeys(String path) throws FileNotFoundException {
        LineNumberReader lineCounter = new LineNumberReader(new InputStreamReader(new FileInputStream(path)));
        String nextLine;
        List<String> keys = new ArrayList<>();

        try {
            while ((nextLine = lineCounter.readLine()) != null) {
                keys.add(nextLine);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Arquivo com as chaves de acesso não localizado", ex);
        }

        return keys;
    }
}
