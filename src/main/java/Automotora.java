import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Vehiculo {
    String marca;
    String modelo;
    String color;
    int anio;
    int kilometraje;

    public Vehiculo(String marca, String modelo, String color, int anio, int kilometraje) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.kilometraje = kilometraje;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Color: " + color + ", Año: " + anio + ", Kilometraje: " + kilometraje;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Vehiculo> catalogoVehiculos = new ArrayList<>();
        catalogoVehiculos.add(new Vehiculo("Toyota", "Corolla", "Rojo", 2015, 60000));
        catalogoVehiculos.add(new Vehiculo("Nissan", "Versa", "Azul", 2018, 30000));
        catalogoVehiculos.add(new Vehiculo("Honda", "Civic", "Negro", 2020, 10000));

        Map<String, String> usuariosRegistrados = new HashMap<>();

        boolean salir = false;
        while (!salir) {
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese su RUT: ");
                    String rutInicioSesion = scanner.nextLine();
                    System.out.print("Ingrese su clave: ");
                    String claveInicioSesion = scanner.nextLine();

                    // Verificar si el usuario está registrado y la clave es correcta
                    if (usuariosRegistrados.containsKey(rutInicioSesion) &&
                            usuariosRegistrados.get(rutInicioSesion).equals(claveInicioSesion)) {
                        System.out.println("Catálogo de vehículos:");
                        for (Vehiculo vehiculo : catalogoVehiculos) {
                            System.out.println(vehiculo);
                        }
                    } else {
                        System.out.println("Error: RUT o clave incorrectos. No tiene acceso al catálogo.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese su RUT: ");
                    String rutRegistro = scanner.nextLine();

                    if (usuariosRegistrados.containsKey(rutRegistro)) {
                        System.out.println("Error: El usuario ya está registrado.");
                    } else if (!validarRut(rutRegistro)) {
                        System.out.println("Error: Formato de RUT incorrecto.");
                    } else {
                        System.out.print("Ingrese su nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese su apellido paterno: ");
                        String apellidoPaterno = scanner.nextLine();
                        System.out.print("Ingrese su apellido materno: ");
                        String apellidoMaterno = scanner.nextLine();
                        int edad;
                        do {
                            System.out.print("Ingrese su edad (entre 18 y 75 años): ");
                            edad = scanner.nextInt();
                            scanner.nextLine();
                            if (edad < 18) {
                                System.out.println("Error: la edad debe ser mayor o igual a 18 años");
                            }
                        } while (edad < 18 || edad > 75);

                        String claveRegistro;
                        do {
                            System.out.print("Ingrese la clave que desea (8-10 dígitos): ");
                            claveRegistro = scanner.nextLine();
                            if (claveRegistro.length() < 8 || claveRegistro.length() > 10) {
                                System.out.println("Error: la clave debe tener entre 8 y 10 dígitos.");
                            }
                        } while (claveRegistro.length() < 8 || claveRegistro.length() > 10);

                        usuariosRegistrados.put(rutRegistro, claveRegistro);

                        System.out.println("Registro exitoso. Catálogo de vehículos:");
                        for (Vehiculo vehiculo : catalogoVehiculos) {
                            System.out.println(vehiculo);
                        }
                    }
                    break;

                case 3:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static boolean validarRut(String rut) {
        // Expresión regular para validar el formato del RUT (sin puntos ni guion)
        String rutPattern = "\\d{7,8}-[kK\\d]";

        // Verificar si el RUT coincide con el patrón
        return rut.matches(rutPattern);
    }
}
