
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import hospital.Ingreso;

public class MenuIngreso {
	public static void menuIngresos( Scanner s ) throws Exception {
		
		int opcion = 0;
		Ingreso ingreso = new Ingreso();
		int id = 0;
		int idPaciente = 0;
		int idMedico = 0;
		Date fechaIngreso;
		int habitacion;
		int cama;
		Date fechaAlta = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		do {
			System.out.println( "----MENU DE INGRESOS---- " );
			System.out.println( "1. Insertar nuevo ingreso. " );
			System.out.println( "2. Consultar ingreso. " );
			System.out.println( "3. Actualizar datos de ingreso. " );
			System.out.println( "4. Borrar ingreso. " );
			System.out.println( "5. Salir al menú principal. " );
			opcion = s.nextInt(); s.nextLine();
			
			switch( opcion ) {
			case 1:
				System.out.println( "Introduzca id de paciente: " );
				idPaciente = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca id de médico: " );
				idMedico = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca número de habitacion: " );
				habitacion = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca número de cama: " );
				cama = s.nextInt(); s.nextLine();	
				ingreso.inserta( idPaciente, idMedico, habitacion, cama  );
				System.out.println( "Ingreso introducido. " );
				break;
			case 2:
				int entrada = -1;
				do {
					System.out.println( "Introduzca id de ingreso o introduzca 0 para ver todos los ingresos: " );
					entrada = s.nextInt(); s.nextLine();
				} while( ( entrada < 0 ) || ( entrada > 9999 ) );

				ResultSet ingres = ingreso.lee( entrada );
				while( ingres.next() ) {
					System.out.println( "id: " + ingres.getInt( "id" ) );
					System.out.println( "idPaciente: " + ingres.getInt( "idPaciente" ) );
					System.out.println( "idMedico: " + ingres.getInt( "idMedico" ) );
					System.out.println( "fecha de ingreso: " + ingres.getDate( "fechaIngreso" ) );
					System.out.println( "habitación: " + ingres.getInt( "habitacion" ) );
					System.out.println( "cama: " + ingres.getInt( "cama" ) );
					System.out.println( "fecha de alta: " + ingres.getDate( "fechaAlta" ) + "\n" );
				}
				break;
			case 3:
				System.out.println( "Introduzca id de ingreso (obligatorio): " );
				id = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca idPaciente: " );
				idPaciente = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca idMedico: " );
				idMedico = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca fecha de ingreso (dd/MM/aaaa): " );
				fechaIngreso = new java.sql.Date( formato.parse( s.nextLine() ).getTime() );
				System.out.println( "Introduzca número de habitacion: " );
				habitacion = s.nextInt(); s.nextLine();			
				System.out.println( "Introduzca número de cama: " );
				cama = s.nextInt(); s.nextLine();
				System.out.println( "Introduzca fecha de alta (dd/MM/aaaa): " );
				fechaAlta =  new java.sql.Date( formato.parse( s.nextLine() ).getTime() );
				ingreso.actualiza( id, idPaciente, idMedico, fechaIngreso, habitacion, cama, fechaAlta );
				System.out.println( "Ingreso actualizado. " );
				break;
			case 4:
				System.out.println( "Introduzca código de ingreso: " );
				int idIngreso = s.nextInt(); s.nextLine();
				ingreso.borra( idIngreso );
				System.out.println( "Ingreso borrado. " );
				break;
			case 5:
				break;
			default:
				System.out.println( "Seleccione una opción válida. " );
			}
		} while( opcion != 5 );
	}
}