import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Login {

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
        leerFichero(resumenHexadecimal);
    }

    public static String leerFichero(String mensajeEncriptado) {
        BufferedReader br = null;
        String[] contenido;
        String mensaje = "";
        byte[] arrayMensaje1 = mensajeEncriptado.getBytes();
        byte[] arrayMensaje2 = null;
        boolean existe = false;

        try {
            br = new BufferedReader(new FileReader("C:\\Users\\rlindes\\IdeaProjects\\ejercicioCredenciales\\credenciales.cre"));
            Scanner sc = new Scanner(br);

            while (sc.hasNext()) {
                mensaje = sc.nextLine();
                arrayMensaje2 = mensaje.getBytes();
                if (calculoBash.compararResumenes(arrayMensaje1, arrayMensaje2)){
                    existe = true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (existe){
            System.out.println("Se ha iniciado sesion correctamente");
        }else {
            System.out.println("Ese usuario no existe");
        }

        return mensaje;
    }
}
