package mercadolivre.mercadolivre.entities;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
    public MD5() {
    }

    public String criptografar(String palavra){

        try {
            MessageDigest m = MessageDigest.getInstance("MD5"); //Obter uma instância do algoritmo a ser usado.
            m.update(palavra.getBytes(), 0, palavra.length()); //Passar a informação que se deseja criptografar para o algoritmo.
            return new BigInteger(1, m.digest()).toString(16);//Efetuar a criptografia.
        }catch (Exception e){
            System.out.println(e);
            return e.getMessage();
        }
    }
}