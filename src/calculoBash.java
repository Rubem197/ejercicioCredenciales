import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class calculoBash {

    public static byte[] getDigest(byte[] nombreContrasena,String metodoEncriptacion){

        MessageDigest algoritmo = null;
        byte[] resumen = null;
        try {
            algoritmo = MessageDigest.getInstance(metodoEncriptacion);
            algoritmo.reset();
            algoritmo.update(nombreContrasena); // mensaje es un array de bytes
            resumen = algoritmo.digest();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return  resumen;
    }

    public static boolean compararResumenes(byte[] array1, byte[] array2){
        boolean iguales= false;
        if (MessageDigest.isEqual(array1, array2)){
            iguales = true;
        }
        return iguales;
    }
}
