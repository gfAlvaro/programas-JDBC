import java.util.Scanner;

public class Menu {
	
	public static void main( String[] args ) {
		
		int opcion = 0;		
		Scanner s = new Scanner( System.in );
		
		do {
			System.out.println( "----MENU HOSPITAL---- " );
			System.out.println( "Elija dobre qué datos quiere operar. " );
			System.out.println( "1. Ingresos. " );
			System.out.println( "2. Medicos. " );
			System.out.println( "3. Pacientes. " );
			System.out.println( "4. Salir. " );
			opcion = s.nextInt(); s.nextLine();
			
			switch( opcion ) {
			case 1:	
				try { MenuIngreso.menuIngresos( s ); } 
				catch( Exception e ) { e.printStackTrace(); }
				break;
			case 2:
				try { MenuMedico.menuMedicos( s ); }
				catch( Exception e ) { e.printStackTrace(); }
				break;
			case 3:
				try { MenuPaciente.menuPacientes( s ); }
				catch( Exception e ) { e.printStackTrace(); }
				break;
			case 4:
				s.close();
				System.out.println( "Hasta la vista. " );
				break;
			default:
				System.out.println( "Seleccione una opción válida. " );
			}						
		} while( opcion != 4 );		
	}
}