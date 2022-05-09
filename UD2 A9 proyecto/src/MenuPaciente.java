
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import hospital.Paciente;

public class MenuPaciente {
	
	public static void menuPacientes( Scanner s ) throws Exception {
		
		int opcion = 0;
		Paciente paciente = new Paciente();
		int id = 0;
		String nombre;
		String apellidos;
		String direccion;
		String poblacion;
		String provincia;
		int codigoPostal;
		int telefono;
		Date fechaNacimiento;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		do {
			System.out.println( "----MENU DE PACIENTES---- " );
			System.out.println( "1. Insertar nuevo paciente. " );
			System.out.println( "2. Consultar paciente. " );
			System.out.println( "3. Actualizar datos de paciente. " );
			System.out.println( "4. Borrar paciente. " );
			System.out.println( "5. Salir al menú principal. " );
			opcion = s.nextInt(); s.nextLine();
			
			switch( opcion ) {
			case 1:
				System.out.println( "Introduzca nombre: " );
				nombre = s.nextLine();
				System.out.println( "Introduzca apellidos: " );
				apellidos = s.nextLine();
				System.out.println( "Introduzca direccion: " );
				direccion = s.nextLine();
				System.out.println( "Introduzca poblacion: " );
				poblacion = s.nextLine();
				System.out.println( "Introduzca provincia: " );
				provincia = s.nextLine();
				System.out.println( "Introduzca código postal: " );
				codigoPostal = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca teléfono: " );
				telefono = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca fecha de nacmimiento (dd/MM/aaaa): " );
				fechaNacimiento = new java.sql.Date( formato.parse( s.nextLine() ).getTime() );
				
				paciente.inserta( nombre, apellidos, direccion, poblacion,
								  provincia, codigoPostal, telefono, fechaNacimiento  );
				System.out.println( "Paciente insertado. " );
				break;
			case 2:
				int entrada;
				do {
					System.out.println( "Introduzca id de ingreso o introduzca 0 para ver todos los pacientes: " );
					entrada = s.nextInt(); s.nextLine();
				} while( ( entrada < 0 ) || ( entrada > 9999 ) );

				ResultSet pacient = paciente.lee( entrada );
				while( pacient.next() ){
					System.out.println( "id: " + pacient.getInt( "id" ) );
					System.out.println( "nombre: " + pacient.getString( "nombre" ) );
					System.out.println( "apellidos: " + pacient.getString( "apellidos" ) );
					System.out.println( "direccion: " + pacient.getString( "direccion" ) );
					System.out.println( "poblacion: " + pacient.getString( "poblacion" ) );
					System.out.println( "provincia: " + pacient.getString( "provincia" ) );
					System.out.println( "código postal: " + pacient.getInt( "codigoPostal" ) );
					System.out.println( "telefono: " + pacient.getInt( "telefono" ) );
					System.out.println( "fecha de nacimiento: " + pacient.getDate( "fechaNacimiento" ) + "\n" );
				}
				break;
			case 3:
				System.out.println( "Introduzca id: " );
				id = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca nombre: " );
				nombre = s.nextLine();
				System.out.println( "Introduzca apellidos: " );
				apellidos = s.nextLine();
				System.out.println( "Introduzca dirección: " );
				direccion = s.nextLine();
				System.out.println( "Introduzca poblacion: " );
				poblacion = s.nextLine();
				System.out.println( "Introduzca provincia: " );
				provincia = s.nextLine();
				System.out.println( "Introduzca código postal: " );
				codigoPostal = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca teléfono: " );
				telefono = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca fecha de nacimiento (dd/MM/aaaa): " );
				fechaNacimiento = new java.sql.Date( formato.parse( s.nextLine() ).getTime() );
				
				paciente.actualiza( id, nombre, apellidos, direccion, poblacion,
									provincia, codigoPostal, telefono, (java.sql.Date) fechaNacimiento );
				System.out.println( "Paciente actualizado. " );
				break;
			case 4:
				System.out.println( "Introduzca id de paciente: " );
				int idIngreso = s.nextInt(); s.nextLine();
				paciente.borra( idIngreso );
				System.out.println( "Paciente borrado. " );
				break;
			case 5:
				break;
			default:
				System.out.println( "Seleccione una opción válida." );
			}
		} while( opcion != 5 );		
	}
}