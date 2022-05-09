
import java.sql.ResultSet;
import java.util.Scanner;

import hospital.Medico;

public class MenuMedico {
	public static void menuMedicos(Scanner s) throws Exception {
		
		int opcion = 0;
		Medico medico = new Medico();
		int id = 0;
		String nombre;
		String apellidos;
		int telefono;
		String especialidad;
		
		do {
			System.out.println( "----MENU DE MEDICOS---- " );
			System.out.println( "1. Insertar nuevo medico. " );
			System.out.println( "2. Consultar datos de medico. " );
			System.out.println( "3. Actualizar datos de medico. " );
			System.out.println( "4. Borrar medico. " );
			System.out.println( "5. Salir al menú principal. " );
			opcion = s.nextInt(); s.nextLine();
			
			switch( opcion ) {
			case 1:
				System.out.println( "Introduzca nombre: " );
				nombre = s.nextLine();
				System.out.println( "Introduzca apellidos: " );
				apellidos = s.nextLine();
				System.out.println( "Introduzca telefono: " );
				telefono = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca especialidad: " );
				especialidad = s.nextLine();
				medico.inserta( nombre, apellidos, telefono, especialidad );
				System.out.println( "Médico insertado. " );
				break;
			case 2:
				int entrada = -1;
				do {
					System.out.println( "Introduzca id de médico o introduzca 0 para ver todos los médicos: " );
					entrada = s.nextInt(); s.nextLine();
				} while( ( entrada < 0 ) || ( entrada > 9999 ) );

				ResultSet medic = medico.lee( entrada );
				while( medic.next() ) {
					System.out.println( "id: " + medic.getInt( "id" ) );
					System.out.println( "nombre: " + medic.getString( "nombre" ) );
					System.out.println( "apellidos: " + medic.getString( "apellidos" ) );
					System.out.println( "telefono: " + medic.getInt( "telefono" ) );
					System.out.println( "especialidad: " + medic.getString( "especialidad" ) + "\n" );
				}
				break;
			case 3:
				System.out.println( "Introduzca id del médico (obligatorio): " );
				id = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca nombre: " );
				nombre = s.nextLine();
				System.out.println( "Introduzca apellidos: " );
				apellidos = s.nextLine();
				System.out.println( "Introduzca número de teléfono: " );
				telefono = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca especialidad: " );
				especialidad = s.nextLine();
				medico.actualiza( id, nombre, apellidos, telefono, especialidad );
				System.out.println( "Médico actualizado. " );
				break;
			case 4:
				System.out.println( " Introduzca código de médico: " );
				int idIngreso = s.nextInt(); s.nextLine();
				medico.borra( idIngreso );
				System.out.println( "Médico borrado. " );
				break;
			case 5:
				break;
			default:
				System.out.println( "Seleccione una opción válida. " );
			}
		} while( opcion != 5 );		
	}
}