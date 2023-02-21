import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Registro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        String contrasena;
        String unido;
        byte[] nombreContrasena;
        byte[] nombreContrasenaEnciptado;
        String resumenHexadecimal;

        System.out.println("Introduce el nombre de usuario:");
        nombre=sc.nextLine();
        System.out.println("Introduce la contraseña: ");
        contrasena= sc.nextLine();

        unido = nombre + contrasena;
        nombreContrasena = unido.getBytes();

        nombreContrasenaEnciptado = calculoBash.getDigest(nombreContrasena, "SHA-256");
        resumenHexadecimal = String.format("%064x", new BigInteger(1, nombreContrasenaEnciptado));
        insertarEnFichero(resumenHexadecimal);

    }

    public static void insertarEnFichero(String mensaje){

        System.out.println(mensaje);
        try {
            String filePath = "C:\\Users\\rlindes\\IdeaProjects\\ejercicioCredenciales\\credenciales.cre";
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mensaje);
            bw.newLine();
            bw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
