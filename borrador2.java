import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class borrador2 {

    static HashMap<String, String> nombre_laboral = new HashMap<>();
    static HashMap<String, String> roles = new HashMap<>();
    static HashMap<String, String> tiposcc = new HashMap<>();
    static HashMap<String, String> cedulas = new HashMap<>();
    static HashMap<String, String> horarios = new HashMap<>();
    static HashMap<String, String> mensajes = new HashMap<>();
    static HashMap<String, String> remitentes = new HashMap<>();

    static String nombre;
    static String codigo;
    static String rol;
    static String tipocc;
    static String cedula;
    static String mensaje1;
    static String mensaje2;
    static String mensaje3;
    static String remitente;
    static String horario = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSistema de Registro de Horarios V 0.5");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse en el Sistema");
            System.out.println("3. Salir");

            System.out.print("Ingrese el numero de la opcion deseada : ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    IniciarSesion(scanner);
                    break;

                case 2:
                    RegistrarUsuario(scanner);
                    break;

                case 3:
                    System.out.print("¿Está seguro de que desea salir? (si/no): ");
                    String respuesta = scanner.nextLine().toLowerCase();

                    if (respuesta.equals("si")) {
                        System.out.println("Hasta Pronto!!");
                        return;
                    } else if (respuesta.equals("no")) {
                        break;
                    } else {
                        System.out.println("Respuesta invalida. Ingrese 'si' o 'no'");
                    }
                    break;

                default:
                    System.out.println("Opcion invalida. Seleccione nuevamente");
                    break;
            }
        }
    }

    public static void IniciarSesion(Scanner scanner) {
        System.out.print("Ingrese su código de usuario: ");
        codigo = scanner.next();
        if (nombre_laboral.containsKey(codigo)) {
            nombre = nombre_laboral.get(codigo);
            tipocc = tiposcc.get(codigo);
            cedula = cedulas.get(codigo);
            rol = roles.get(codigo);
            System.out.println("...");
            System.out.println("\nBienvenido al Sistema");
            System.out.println("Nombre: " + nombre);
            System.out.println("Tipo de Documento: " + tipocc);
            System.out.println("Numero de Documento: " + cedula);
            System.out.println("Rol : " + rol);

            administrarHorarios(rol); //

        } else {
            System.out.println("...");
            System.out.println("NO existe ningun usuario con el código " + codigo);
        }
    }

    public static void RegistrarUsuario(Scanner scanner) {
        System.out.println("\nDiligencie los siguientes datos:");
        System.out.print("\nCodigo de empleado: ");
        codigo = scanner.nextLine();

        if (!nombre_laboral.containsKey(codigo)) {
            System.out.print("Nombre Completo: ");
            nombre = scanner.nextLine();
            int tipoccInt;
            do {
                System.out.println("Seleccione su tipo de Documento: ");
                System.out.print("1. Cedula de Ciudadania(CC), 2. Tarjeta de Identidad (TI), 3. Pasaporte, 4. Cédula de Extranjería: ");
                tipoccInt = Integer.parseInt(scanner.nextLine());

                if (tipoccInt >= 1 && tipoccInt <= 4) {
                    break;
                } else {
                    System.out.println("Opcion Invalida. Seleccione 1, 2, 3 o 4");
                }
            } while (true);

            switch (tipoccInt) {
                case 1:
                    tipocc = "Cedula de Ciudadania(CC)";
                    break;
                case 2:
                    tipocc = "Tarjeta de Identidad (TI)";
                    break;
                case 3:
                    tipocc = "Pasaporte";
                    break;
                case 4:
                    tipocc = "Cédula de Extranjería";
                    break;
                default:
                    tipocc = "desconocido";
                    break;
            }

            System.out.print("Numero de Identificacion: ");
            cedula = scanner.nextLine();
            int rolInt;
            do {
                System.out.println("Seleccione su Rol: ");
                System.out.print("1. Administrador, 2. para Coordinador, 3. para Empleado: ");
                rolInt = Integer.parseInt(scanner.nextLine());

                if (rolInt >= 1 && rolInt <= 3) {
                    break;
                } else {
                    System.out.println("Opcion Invalida. Seleccione 1, 2 o 3");
                }
            } while (true);

            String rol;
            switch (rolInt) {
                case 1:
                    rol = "Administrador";
                    break;
                case 2:
                    rol = "Coordinador";
                    break;
                case 3:
                    rol = "Empleado";
                    break;
                default:
                    rol = "desconocido";
                    break;
            }

            nombre_laboral.put(codigo, nombre);
            tiposcc.put(codigo, tipocc);
            cedulas.put(codigo, cedula);
            roles.put(codigo, rol);
            System.out.println("...");
            System.out.println("Usuario creado exitosamente.");
        } else {
            System.out.println("EL codigo " + codigo +" ya esta en uso. Por favor ingrese un codigo valido.");
        }
    }

    public static void administrarHorarios(String rol) {

        switch (rol) {
            case "Administrador":
                administradorOpciones();
                break;
            case "Coordinador":
                coordinadorOpciones();
                break;
            case "Empleado":
                empleadoOpciones();
                break;

        }
    }

    public static void administradorOpciones() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nOpciones para Administradores:");
            System.out.println("1. Consultar datos empleado");
            System.out.println("2. Consultar todos los empleados");
            System.out.println("3. Modificar datos de empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Revisar mensajes");
            System.out.println("6. Enviar mensaje a coordinador");
            System.out.println("7. Salir");

            System.out.print("Ingrese el numero de la opcion deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el codigo del empleado: ");
                    codigo = scanner.next();
                    if (nombre_laboral.containsKey(codigo)) {
                        nombre = nombre_laboral.get(codigo);
                        tipocc = tiposcc.get(codigo);
                        cedula = cedulas.get(codigo);
                        rol = roles.get(codigo);
                        System.out.println("\nNombre: " + nombre);
                        System.out.println("Tipo de Documento: " + tipocc);
                        System.out.println("Numero de Documento: " + cedula);
                        System.out.println("Rol : " + rol);
                    }
                    break;
                case 2:
                    Set<String> codigos = nombre_laboral.keySet();
                    System.out.println();
                    System.out.println("Numero de empleados registrados: " + codigos.size());

                    for (String codigo : codigos) {
                        nombre = nombre_laboral.get(codigo);
                        rol = roles.get(codigo);
                        System.out.println();
                        System.out.println("Nombre: " + nombre);
                        System.out.println("Rol : " + rol);
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el codigo del empleado: ");
                    codigo = scanner.next();
                    if (nombre_laboral.containsKey(codigo)) {
                        scanner.nextLine();

                        System.out.println("Seleccione qué aspecto desea modificar:");
                        System.out.println("1. Nombre");
                        System.out.println("2. Tipo de Documento");
                        System.out.println("3. Número de Documento");
                        System.out.println("4. Rol");

                        int opcionModificar = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionModificar) {
                            case 1:
                                System.out.print("Ingrese el nuevo nombre del empleado: ");
                                nombre = scanner.nextLine();
                                nombre_laboral.put(codigo, nombre);
                                System.out.println("Nombre modificado con éxito.");
                                break;
                            case 2:
                                int tipoccInt;
                                do {
                                    System.out.println("Seleccione el nuevo tipo de documento: ");
                                    System.out.print("1. Cedula de Ciudadania(CC), 2. Tarjeta de Identidad (TI), 3. Pasaporte, 4. Cédula de Extranjería: ");
                                    tipoccInt = Integer.parseInt(scanner.nextLine());

                                    if (tipoccInt >= 1 && tipoccInt <= 4) {
                                        break;
                                    } else {
                                        System.out.println("Opcion Invalida. Seleccione 1, 2, 3 o 4");
                                    }
                                } while (true);

                                switch (tipoccInt) {
                                    case 1:
                                        tipocc = "Cedula de Ciudadania(CC)";
                                        break;
                                    case 2:
                                        tipocc = "Tarjeta de Identidad (TI)";
                                        break;
                                    case 3:
                                        tipocc = "Pasaporte";
                                        break;
                                    case 4:
                                        tipocc = "Cédula de Extranjería";
                                        break;
                                    default:
                                        tipocc = "desconocido";
                                        break;
                                }
                                tiposcc.put(codigo, tipocc);
                                System.out.println("Tipo de documento modificado con éxito.");
                                break;
                            case 3:
                                System.out.print("Ingrese el nuevo numero de documento: ");
                                cedula = scanner.nextLine();
                                cedulas.put(codigo, cedula);
                                System.out.println("Numero de documento modificado con éxito..");
                                break;
                            case 4:
                                int rolInt;
                                do {
                                    System.out.println("Seleccione el nuevo Rol: ");
                                    System.out.print("1. Administrador, 2. para Coordinador, 3. para Empleado: ");
                                    rolInt = Integer.parseInt(scanner.nextLine());

                                    if (rolInt >= 1 && rolInt <= 3) {
                                        break;
                                    } else {
                                        System.out.println("Opcion Invalida. Seleccione 1, 2 o 3");
                                    }
                                } while (true);

                                switch (rolInt) {
                                    case 1:
                                        rol = "Administrador";
                                        break;
                                    case 2:
                                        rol = "Coordinador";
                                        break;
                                    case 3:
                                        rol = "Empleado";
                                        break;
                                    default:
                                        rol = "desconocido";
                                        break;
                                }
                                roles.put(codigo, rol);
                                System.out.println("Rol modificado con éxito.");
                            default:
                                System.out.println("Opción inválida.");
                                break;
                        }
                    } else {
                        System.out.println("No existe un empleado con el código " + codigo);
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el codigo del empleado: ");
                    codigo = scanner.next();
                    nombre_laboral.remove(codigo);
                    tiposcc.remove(codigo);
                    cedulas.remove(codigo);
                    roles.remove(codigo);
                    System.out.println("...");
                    System.out.println("Se ha eliminado el empleado exitosamente");
                    break;
                case 5:
                    if (mensaje1 != null && !mensaje1.isEmpty()) {
                        mensaje1 = mensajes.get(codigo);
                        remitente = remitentes.get(codigo);
                        System.out.println("Tiene el siguiente mensaje : " + mensaje1);
                    } else {
                        System.out.println("No tienes mensajes nuevos.");
                    }
                    break;
                case 6:
                    System.out.print("Ingrese el codigo del Coordinador : ");
                    codigo = scanner.next();
                    nombre = nombre_laboral.get(codigo);

                    if (nombre_laboral.containsKey(codigo)) {
                        scanner.nextLine();

                        System.out.print("Ingrese el mensaje a enviar: ");
                        mensaje2 = scanner.nextLine();
                        mensajes.put(codigo, mensaje2);
                        remitentes.put(codigo, remitente);
                        System.out.println("...");
                        System.out.println("Se envio el mensaje exitosamente al Coordinador: "+nombre +" con codigo" +codigo);
                    } else {
                        System.out.println("No existe un empleado con el código " + codigo);
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione una opción valida.");
                    break;
            }
        }
    }

    public static void coordinadorOpciones() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpciones para Coordinadores:");
            System.out.println("1. Ver Horario de Empleado");
            System.out.println("2. Registrar Horario");
            System.out.println("3. Modificar Horario");
            System.out.println("4. Eliminar  Horario");
            System.out.println("5. Revisar mensajes");
            System.out.println("6. Enviar mensaje a Administrador");
            System.out.println("7. Enviar mensaje a Empleado");
            System.out.println("8. Salir");

            System.out.print("Ingrese el numero de la opcion deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el codigo del Empleado : ");
                    codigo = scanner.next();
                    nombre = nombre_laboral.get(codigo);
                    horario = horarios.get(codigo);
                    if (nombre_laboral.containsKey(codigo)) {
                        scanner.nextLine();
                        if (horario != null) {
                            System.out.println("\nNombre: " + nombre);
                            System.out.println("Horario: " + horario);
                        } else {
                            System.out.println("\nEl empleado no tiene parametrizado ningun horario");
                        }

                    } else {
                        System.out.println("No existe un empleado con el código " + codigo);
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el codigo del Empleado : ");
                    codigo = scanner.next();
                    if (nombre_laboral.containsKey(codigo)) {
                        scanner.nextLine();
                        System.out.print("Ingrese el horario a registrar: ");
                        horario = scanner.nextLine();
                        horarios.put(codigo, horario);
                        System.out.println("Se ha registrado el horario exitosamente");
                    } else {
                        System.out.println("No existe un empleado con el código " + codigo);
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el codigo del Empleado : ");
                    codigo = scanner.next();
                    nombre = nombre_laboral.get(codigo);
                    if (nombre_laboral.containsKey(codigo)) {
                        if (horario != null) {
                            horario = horarios.get(codigo);
                            System.out.println("El horario actual del empleado "+nombre+ "es : " + horario);
                            scanner.nextLine();
                            System.out.print("Ingrese el nuevo horario: ");
                            horario = scanner.nextLine();
                            horarios.put(codigo, horario);
                            System.out.println("Se ha modificado el horario exitosamente");
                        } else {
                            System.out.println("\nEl empleado no tiene parametrizado ningun horario");
                        }

                    } else {
                        System.out.println("No existe un empleado con el código " + codigo);
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el codigo del empleado: ");
                    codigo = scanner.next();
                    horarios.remove(codigo);
                    System.out.println("...");
                    System.out.println("Se ha eliminado el horario exitosamente");
                    break;
                case 5:
                    if (mensaje2 != null && !mensaje2.isEmpty()) {
                        mensaje2 = mensajes.get(codigo);
                        remitente = remitentes.get(codigo);
                        System.out.println("Tiene el siguiente mensaje : " + mensaje2);
                    } else {
                        System.out.println("No tienes mensajes nuevos.");
                    }
                    break;
                case 6:
                    System.out.print("Ingrese el codigo del Administrador : ");
                    codigo = scanner.next();
                    nombre = nombre_laboral.get(codigo);

                    if (nombre_laboral.containsKey(codigo)) {
                        scanner.nextLine();

                        System.out.print("Ingrese el mensaje a enviar: ");
                        mensaje1 = scanner.nextLine();
                        mensajes.put(codigo, mensaje1);
                        remitentes.put(codigo, remitente);
                        System.out.println("...");
                        System.out.println("Se envio el mensaje exitosamente al administrador "+ nombre+" con el codigo: " + codigo);
                    } else {
                        System.out.println("No existe un empleado con el código " + codigo);
                    }
                    break;
                case 7:
                    System.out.print("Ingrese el codigo del Empleado : ");
                    codigo = scanner.next();
                    nombre = nombre_laboral.get(codigo);

                    if (nombre_laboral.containsKey(codigo)) {
                        scanner.nextLine();

                        System.out.print("Ingrese el mensaje a enviar: ");
                        mensaje3 = scanner.nextLine();
                        mensajes.put(codigo, mensaje3);
                        remitentes.put(codigo, remitente);
                        System.out.println("...");
                        System.out.println("Se envio el mensaje exitosamente al empleado"+nombre+"con el codigo: " + codigo);
                    } else {
                        System.out.println("No existe un empleado con el código " + codigo);
                    }
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione una opción valida.");
                    break;
            }
        }
    }
    public static void solicitarCambioHorario(Scanner scanner) {
        System.out.println("Ingrese el codigo del coordinador:");
        codigo = scanner.next();
        String codigoC = codigo;
        if (nombre_laboral.containsKey(codigo)) {
            System.out.print("Ingrese su código: ");
            codigo = scanner.next();
            String codigoE = codigo;


            if (nombre_laboral.containsKey(codigoE)) {
                System.out.print("Ingrese el código del otro empleado: ");
                String otroCodigo = scanner.next();


                if (nombre_laboral.containsKey(otroCodigo)) {
                    String miHorario = horarios.get(codigoE);
                    String otroHorario = horarios.get(otroCodigo);

                    // Intercambiar horarios
                    horarios.put(codigo, otroHorario);
                    horarios.put(otroCodigo, miHorario);

                    System.out.println("Se ha realizado el cambio de horario exitosamente.");
                } else {
                    System.out.println("No existe un empleado con el código " + otroCodigo);
                }
            } else {
                System.out.println("No existe un empleado con el código " + codigo);
            }
        }
    }

    public static void empleadoOpciones() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpciones para Empleados:");
            System.out.println("1. Ver horario");
            System.out.println("2. Solicitar cambio de horario");
            System.out.println("3. Revisar mensajes");
            System.out.println("4. Enviar mensaje a coordinador");
            System.out.println("5. Salir");

            System.out.print("Ingrese el numero de la opcion deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                    nombre = nombre_laboral.get(codigo);
                    horario = horarios.get(codigo);
                    if (nombre_laboral.containsKey(codigo)) {
                        scanner.nextLine();
                        if (horario != null) {
                            System.out.println("\nNombre: " + nombre);
                            System.out.println("Horario: " + horario);
                        } else {
                            System.out.println("\nUsted no tiene parametrizado ningun horario");
                        }

                    } else {
                        System.out.println("No existe un empleado con el código " + codigo);
                    }

                    break;
                case 2:
                    solicitarCambioHorario(scanner);
                    break;

                case 3:
                    if (mensaje3 != null && !mensaje3.isEmpty()) {
                        mensaje3 = mensajes.get(codigo);
                        remitente = remitentes.get(codigo);
                        System.out.println("Tiene el siguiente mensaje : " + mensaje3);
                    } else {
                        System.out.println("No tienes mensajes nuevos.");
                    }
                    break;


                case 4:
                    System.out.print("Ingrese el codigo del Coordinador : ");
                    codigo = scanner.next();
                    nombre = nombre_laboral.get(codigo);

                    if (nombre_laboral.containsKey(codigo)) {
                        scanner.nextLine();

                        System.out.print("Ingrese el mensaje: ");
                        mensaje2 = scanner.nextLine();
                        mensajes.put(codigo, mensaje2);
                        remitentes.put(codigo, remitente);
                        System.out.println("...");
                        System.out.println("Se envio el mensaje exitosamente al Coordinador: "+nombre +" con codigo"+ codigo);
                    } else {
                        System.out.println("No existe un empleado con el código " + codigo);
                    }
                    break;

                case 5:
                    return;
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione una opción valida.");
                    break;
            }
        }
    }
}

