package ui;
import model.MSC;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Main{
	public static Scanner sc = new Scanner(System.in);
	private MSC mscManager;
	
	public static void main(String[] args){
		Main ppal = new Main();
		
		int option=0;
		
		do{
			option= ppal.showMenu();
			ppal.executeOperation(option);
			
		}while (option!=0);
	}
	
	/**
	 * Constructor that creates one object of the MSC class in order to 
	 * initialize the application 
	 */
	public Main(){
		mscManager = new MSC();
	}
	
	public int showMenu() {
		int option=0;

		System.out.println(
				"Menú principal, seleccione una opción\n" +
				"(1) Crear nuevo usuario \n" +
				"(2) Mostrar los usuarios registrados \n" +
				"(3) Subir una canción al pool de canciones \n"+
				"(4) Mostrar la lista de canciones en pool \n" +
				"(5) Crear una playlist\n"+
				"(6) Mostrar las playlist creadas \n" +
				"(7) Añadir canción a playlist \n" +  
				"(0) Para salir"
				);
		option= sc.nextInt();
		sc.nextLine();
		return option;
	}
	
	public void executeOperation(int operation) {
		
		switch(operation) {
		case 0:
			System.out.println("Bye!");
			break;
		case 1:
			createUser() ;
			break;
		case 2:
			System.out.println(mscManager.showUsers());
			break;
	
		case 3:
			createSong();
			break;

		case 4:
			System.out.println(mscManager.showSongs());
			break;
		case 5:
			playlistMenu();
			break;
		case 6:
			System.out.println(mscManager.showPlaylists());
			break;
		case 7:
			//Metodo añadir cancion a playlist;
			break;
		default:
			System.out.println("Error, opción no válida");
		
		}
	}
	private void playlistMenu() {
		int option2=0;
		System.out.println(
				"Menú de Playlist \n" +
				"(1) Para crear playlist público\n" +
				"(2) Para crear playlist restringido\n"+
				"(3) Para crear playlist privado \n"+
				"(0) Para volver"
				);
		option2= sc.nextInt();
		sc.nextLine();
		
		switch(option2) {
		case 1:
			createPublicPlaylist();
			break;
		case 2:
			
			break;
		case 3: 
			
			break;
		
		}
	}
	
	
	/**
	 * createUser is a method that asks for the necessary information to add a new user, 
	 * it also validates the information provided 
	 */
	public void createUser(){
		String nickname="";
		String password= "";
		int age=0;
		boolean inputAccepted1 = false;
		
		if(mscManager.spaceAvailableUser()){
			//Ask for inputs and validate
			while(!inputAccepted1) {
				try {
					JTextField Nickname = new JTextField();
					JTextField Password = new JTextField();
					JTextField Age = new JTextField();
					
					//Create several inputs
					Object [] fields = {
						"Nickname", Nickname,
						"Password", Password,
						"Age", Age
					};
					JOptionPane.showConfirmDialog(null,fields,"Crear un nuevo usuario",JOptionPane.OK_CANCEL_OPTION);
					nickname=Nickname.getText();
					password=Password.getText();
					age= Integer.parseInt(Age.getText());

					if (nickname.equals("")) {
					  JOptionPane.showMessageDialog(null, "No puede dejar el nickname vacío","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if (mscManager.findUser(nickname)){
						JOptionPane.showMessageDialog(null, "Nickname no disponible, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if(password.equals("")){
						JOptionPane.showMessageDialog(null, "No puede dejar la contraseña vacía","ERROR", JOptionPane.WARNING_MESSAGE);
					} else {
						inputAccepted1=true;
					}
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La edad solo puede contener numeros","ERROR", JOptionPane.WARNING_MESSAGE);		
				}
			}
			mscManager.addUser(nickname, password, age, 0);
			System.out.println("El usuario " + nickname + " ha sido creado");
		} else{
			System.out.println("No se puede crear más usuarios, se ha alcanzado el máximo permitido");
		}
	}
	
	/**
	 * createSong is a method that asks for the necessary information to add a new song, 
	 * it also validates the information provided 
	 */
	public void createSong(){
		String title="";
		String artist="";
		String releaseDate="";
		String duration="";
		String genre="";
		int year=0;
		int month=0;
		int day=0;
		int hour=0;
		int minutes=0;
		int sec=0;
		boolean inputAccepted=false;
		boolean inputAccepted2=false;
		
		if(mscManager.spaceAvailableSong()){
			while(!inputAccepted) {
				
				JTextField Title = new JTextField();
				JTextField Artist = new JTextField();
				JTextField releaseDay = new JTextField();
				JTextField releaseMonth = new JTextField();
				JTextField releaseYear = new JTextField();
				JTextField durationHour = new JTextField();
				JTextField durationMinutes = new JTextField();
				JTextField durationSecs = new JTextField();
				
				//Create several inputs
				Object [] fields = {
					"Title", Title,
					"Artist", Artist,
					"Day of release", releaseDay,
					"Month of release", releaseMonth,
					"Year of release", releaseYear,
					"Hour of duration", durationHour,
					"Minutes of duration", durationMinutes,
					"Seconds of duration", durationSecs
					
				};
				int n= JOptionPane.showConfirmDialog(null,fields,"Añadir canción al pool",JOptionPane.OK_CANCEL_OPTION);
				
				try{
					if(JOptionPane.CANCEL_OPTION==n){
						inputAccepted=true;
					}else{
						title=Title.getText();
						artist=Artist.getText();
						day = Integer.parseInt(releaseDay.getText());
						month = Integer.parseInt(releaseMonth.getText());
						year = Integer.parseInt(releaseYear.getText());
						hour = Integer.parseInt(durationHour.getText());
						minutes = Integer.parseInt(durationMinutes.getText());
						sec = Integer.parseInt(durationSecs.getText());
						if (title.equals("")) {
							JOptionPane.showMessageDialog(null, "No puede dejar el título vacío","ERROR", JOptionPane.WARNING_MESSAGE);
						} else if (mscManager.findSong(title)){
							JOptionPane.showMessageDialog(null, "Canción ya creada","ERROR", JOptionPane.WARNING_MESSAGE);
						} else if(artist.equals("")){
							JOptionPane.showMessageDialog(null, "No puede dejar el artista vacío","ERROR", JOptionPane.WARNING_MESSAGE);
						} else if(day<0 || day>31){
							JOptionPane.showMessageDialog(null, "Día invállido, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);	
						} else if(month<0 || month>12){
							JOptionPane.showMessageDialog(null, "Mes invállido, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
						} else if(year<0 || year>2020){
							JOptionPane.showMessageDialog(null, "Año invállido, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
						} else if(hour<0){
							JOptionPane.showMessageDialog(null, "Hora de duración invállida, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
						} else if(minutes<0 || minutes>59){
							JOptionPane.showMessageDialog(null, "Minuto de duración inválido, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
						}else if(sec<0 || sec>59){
							JOptionPane.showMessageDialog(null, "Segundo de duración inválido, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
						}else {
							inputAccepted=true;
							releaseDate= day+ "/" + month + "/" + year;
							duration= hour + ":" + minutes + ":" + sec ;
							Object genreSelection = JOptionPane.showInputDialog(null,"Seleccione Un género","GENRES", JOptionPane.QUESTION_MESSAGE, null, new Object[] { "ROCK","HIP_HOP", "SALSA", "CLASSIC", "REGGAE", "METAL" },"Seleccione");
							genre = (String)genreSelection;
							mscManager.addSong(title, artist, releaseDate, duration, genre);
						}
					}
				} catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Entradas númericas no válidas, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		}else{
			JOptionPane.showMessageDialog(null,"No se puede añadir más canciones, se ha alcanzado el máximo permitido");
		} 
		
	}
	
	public void createPublicPlaylist(){
		String playlistName= "";
		boolean inputAccepted=false;
		if(mscManager.spaceAvailablePlaylist()){
			
			while(!inputAccepted){
				playlistName = JOptionPane.showInputDialog("Ingrese el nombre de la playlist");
				
				if(playlistName==null){
					inputAccepted=true;
				}
				if (playlistName.equals("")){
					JOptionPane.showMessageDialog(null, "No puede dejar el nombre vacío","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if (mscManager.findPlaylist(playlistName)){
					JOptionPane.showMessageDialog(null, "Nombre no disponible, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
				} else{
					inputAccepted=true;
					mscManager.addPublicPlaylist(playlistName);
					JOptionPane.showMessageDialog(null,"La playlist " + playlistName + " ha sido creada");
				}
			}
		} else{
			JOptionPane.showMessageDialog(null,"No se puede crear, se ha alcanzado el máximo de playlists");
		} 
	}

}