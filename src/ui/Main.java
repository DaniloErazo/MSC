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
				"(5) Crear y gestionar una playlist\n"+
				"(6) Mostrar las playlist creadas \n" + 
				"(7) Para añadir canción a playlist \n"+
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
			addSongToPlaylist();
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
				"(2) Para crear y administrar playlist restringido\n"+
				"(3) Para crear playlist privado \n"+
				"(4) Para puntuar playlist público \n"+
				"(0) Para volver"
				);
		option2= sc.nextInt();
		sc.nextLine();
		
		switch(option2) {
		case 1:
			createPublicPlaylist();
			break;
		case 2:
			restrictedPlaylistMenu();
			break;
		case 3: 
			createPrivatePlaylist();
			break;
		case 4:
			rankPublicPlaylist();
			break;
		}
	}
	
	private void restrictedPlaylistMenu() {
		int option3=0;
		System.out.println(
				"Menú de Playlist Restringida \n" +
				"(1) Para crear playlist restringido\n" +
				"(2) Para añadir usuario autorizado\n"+
				"(0) Para volver"
				);
		option3= sc.nextInt();
		sc.nextLine();
		
		switch(option3) {
		case 1:
			createRestrictedPlaylist();
			break;
		case 2:
			addAuthorizedUser();
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
					int n = JOptionPane.showConfirmDialog(null,fields,"Crear un nuevo usuario",JOptionPane.OK_CANCEL_OPTION);
					
					if(JOptionPane.CANCEL_OPTION==n){
						inputAccepted1=true;
					}else{
						nickname=Nickname.getText();
						password=Password.getText();
						age= Integer.parseInt(Age.getText());

						int[] userData = mscManager.findUser(nickname);
						
						if (nickname.equals("")) {
						  JOptionPane.showMessageDialog(null, "No puede dejar el nickname vacío","ERROR", JOptionPane.WARNING_MESSAGE);
						} else if (userData[0]==1){
							JOptionPane.showMessageDialog(null, "Nickname no disponible, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
						} else if(password.equals("")){
							JOptionPane.showMessageDialog(null, "No puede dejar la contraseña vacía","ERROR", JOptionPane.WARNING_MESSAGE);
						} else {
							inputAccepted1=true;
							mscManager.addUser(nickname, password, age, 0);
							JOptionPane.showMessageDialog(null,"El usuario " + nickname + " ha sido creado");
						}
					}
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La edad solo puede contener numeros","ERROR", JOptionPane.WARNING_MESSAGE);		
				}
			}
		} else{
			System.out.println("No se puede crear más usuarios, se ha alcanzado el máximo permitido");
		}
	}
	
	/**
	 * createSong is a method that asks for the necessary information to add a new song, 
	 * it also validates the information provided 
	 */
	public void createSong(){
		String user="";
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
		
		if(mscManager.spaceAvailableSong()){
			
			JTextField User = new JTextField();
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
				"User", User,
				"Title", Title,
				"Artist", Artist,
				"Day of release", releaseDay,
				"Month of release", releaseMonth,
				"Year of release", releaseYear,
				"Hour of duration", durationHour,
				"Minutes of duration", durationMinutes,
				"Seconds of duration", durationSecs
				
			};
			while(!inputAccepted) {
				int n= JOptionPane.showConfirmDialog(null,fields,"Añadir canción al pool",JOptionPane.OK_CANCEL_OPTION);
				
				try{
					if(JOptionPane.CANCEL_OPTION==n){
						inputAccepted=true;
					}else{
						user=User.getText();
						title=Title.getText();
						artist=Artist.getText();
						day = Integer.parseInt(releaseDay.getText());
						month = Integer.parseInt(releaseMonth.getText());
						year = Integer.parseInt(releaseYear.getText());
						hour = Integer.parseInt(durationHour.getText());
						minutes = Integer.parseInt(durationMinutes.getText());
						sec = Integer.parseInt(durationSecs.getText());
						
						int[] userData = mscManager.findUser(user);
						int[] songData = mscManager.findSong(title);
						if(userData[0]==0){
							JOptionPane.showMessageDialog(null, "Usuario no encontrado","ERROR", JOptionPane.WARNING_MESSAGE);
						}else if (title.equals("")) {
							JOptionPane.showMessageDialog(null, "No puede dejar el título vacío","ERROR", JOptionPane.WARNING_MESSAGE);
						} else if (songData[0]==1){
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
							int userOldNumberSongs = mscManager.getUser(userData[1]).getUploadedSongs();
							mscManager.getUser(userData[1]).setUploadedSongs(userOldNumberSongs+1);
							mscManager.getUser(userData[1]).setCategory(mscManager.getUser(userData[1]).defineCategory(userOldNumberSongs+1));
							JOptionPane.showMessageDialog(null,"La canción " + title + " se ha añadido al pool de canciones");
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
				
				int[] playlistData = mscManager.findPlaylist(playlistName);
				
				if(playlistName != null){
						if (playlistName.equals("")){
						JOptionPane.showMessageDialog(null, "No puede dejar el nombre vacío","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if (playlistData[0]==1){
						JOptionPane.showMessageDialog(null, "Nombre no disponible, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
					} else{
						inputAccepted=true;
						mscManager.addPublicPlaylist(playlistName);
						JOptionPane.showMessageDialog(null,"La playlist " + playlistName + " ha sido creada");
					}
				}else{
					inputAccepted=true;
				} 
				
			}
		} else{
			JOptionPane.showMessageDialog(null,"No se puede crear, se ha alcanzado el máximo de playlists");
		} 
	}
	
	public void createPrivatePlaylist(){
		String userName;
		String namePlaylist;
		boolean inputAccepted=false;
		if(mscManager.spaceAvailablePlaylist()){
			
			while(!inputAccepted){
				
				JTextField userNick = new JTextField();
				JTextField playlistName = new JTextField();
				
				//Create several inputs
				Object [] fields = {
					"Nickname del usuario autorizado", userNick,
					"Nombre de playlist", playlistName
				};
				int n = JOptionPane.showConfirmDialog(null,fields,"Crear una playlist privada",JOptionPane.OK_CANCEL_OPTION);
				
				if(JOptionPane.CANCEL_OPTION==n){
					inputAccepted=true;
				}else{
					
					userName=userNick.getText();
					namePlaylist=playlistName.getText();
					int[] userData = mscManager.findUser(userName);
					int[] playlistData = mscManager.findPlaylist(namePlaylist);
					
					if(userData[0]==0){
						JOptionPane.showMessageDialog(null, "Usuario no encontrado","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if (namePlaylist.equals("")){
						JOptionPane.showMessageDialog(null, "No puede dejar el nombre vacío","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if (playlistData[0]==1){
						JOptionPane.showMessageDialog(null, "Nombre no disponible, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
					}else {
						inputAccepted=true;
						mscManager.addPrivatePlaylist(namePlaylist, userData[1]);
						JOptionPane.showMessageDialog(null,"La playlist " + namePlaylist + " ha sido creada");
					} 
				}
			}
		} else{
			JOptionPane.showMessageDialog(null,"No se puede crear, se ha alcanzado el máximo de playlists");
		} 
	}

	public void createRestrictedPlaylist(){
		String userName;
		String namePlaylist;
		boolean inputAccepted=false;
		if(mscManager.spaceAvailablePlaylist()){
			
			JTextField userNick = new JTextField();
			JTextField playlistName = new JTextField();
			
			//Create several inputs
			Object [] fields = {
				"Nickname del usuario autorizado", userNick,
				"Nombre de playlist", playlistName
			};
			
			while(!inputAccepted){
			
				int n = JOptionPane.showConfirmDialog(null,fields,"Crear una playlist restringida",JOptionPane.OK_CANCEL_OPTION);
				
				if(JOptionPane.CANCEL_OPTION==n){
					inputAccepted=true;
				}else{
					
					userName=userNick.getText();
					namePlaylist=playlistName.getText();
					int[] userData = mscManager.findUser(userName);
					int[] playlistData = mscManager.findPlaylist(namePlaylist);
					
					if (userName== ""){
						JOptionPane.showMessageDialog(null, "No puede dejar el usuario vacío","ERROR", JOptionPane.WARNING_MESSAGE);
					}else if(userData[0]==0){
						JOptionPane.showMessageDialog(null, "Usuario no encontrado","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if (namePlaylist.equals("")){
						JOptionPane.showMessageDialog(null, "No puede dejar el nombre vacío","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if (playlistData[0]==1){
						JOptionPane.showMessageDialog(null, "Nombre no disponible, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
					} else{
						inputAccepted=true;
						mscManager.addRestrictedPlaylist(namePlaylist, userData[1]);
						JOptionPane.showMessageDialog(null,"La playlist " + namePlaylist + " ha sido creada. El usuario principal " + userName + " puede añadir hasta 4 usuarios autorizados desde el menú");
					} 
				}
			}
		} else{
			JOptionPane.showMessageDialog(null,"No se puede crear, se ha alcanzado el máximo de playlists");
		}
	}

	public void addAuthorizedUser(){
		String currentUser="";
		String newUser="";
		String playlistName="";
		boolean inputAccepted=false;
		
		JTextField oldUser = new JTextField();
		JTextField addUser = new JTextField();
		JTextField playlist = new JTextField();
		
		//Create several inputs
		Object [] fields = {
			"Playlist", playlist,
			"Usuario autorizado", oldUser,
			"Usuario a añadir", addUser
		};
		
		while(!inputAccepted){
			int n = JOptionPane.showConfirmDialog(null,fields,"Añadir usuario autorizado a playlist restringida",JOptionPane.OK_CANCEL_OPTION);
			if(JOptionPane.CANCEL_OPTION==n){
			inputAccepted=true;
			}else{
				playlistName=playlist.getText();
				currentUser=oldUser.getText();
				newUser=addUser.getText();

				int[] userData = mscManager.findUser(currentUser);
				int[] userData2 = mscManager.findUser(newUser);
				int[] playlistData = mscManager.findPlaylist(playlistName);
				
				if (playlist.equals("")){
				  JOptionPane.showMessageDialog(null, "No puede dejar el nombre de playlist vacío","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if (currentUser.equals("")){
					JOptionPane.showMessageDialog(null, "No puede dejar el usuario autorizado vacío","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if(newUser.equals("")){
					JOptionPane.showMessageDialog(null, "No puede dejar el nuevo usuario autorizado vacío","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if(playlistData[0]!=1){
					JOptionPane.showMessageDialog(null, "La playlist no existe, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if(!mscManager.findPlaylistType(3, playlistData[1])){
					JOptionPane.showMessageDialog(null, "La playlist no es restringida, intente de nuevo","ERROR", JOptionPane.WARNING_MESSAGE); 
				} else if(userData[0]!=1){
					JOptionPane.showMessageDialog(null, "El usuario autorizado no existe","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if(userData2[0]!=1){
					JOptionPane.showMessageDialog(null, "El usuario a autorizar no existe","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if(!mscManager.findAuthorizedUser(currentUser, playlistData[1])){
					JOptionPane.showMessageDialog(null, "El usuario autorizado no tiene derechos en esta playlist","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if(mscManager.checkRepeatedUser(newUser, playlistData[1])){
					JOptionPane.showMessageDialog(null, "El usuario a autorizar ya tiene derechos sobre esta playlist","ERROR", JOptionPane.WARNING_MESSAGE);
				}else{
					inputAccepted=true;
					mscManager.addAuthorizedUserRestricted(playlistData[1], userData2[1]);
					JOptionPane.showMessageDialog(null,"Permisos concedidos, ahora " + newUser + " tiene derechos sobre la playlist " + playlistName);
				}
			}
		} 
		
	}

	public void rankPublicPlaylist(){
		String playlistName="";
		int rank=0;
		boolean inputAccepted=false;
		
		JTextField playlist = new JTextField();
		JTextField user = new JTextField();
		JTextField ranked = new JTextField();
		
		//Create several inputs
		Object [] fields = {
			"Nombre de la playlist", playlist,
			"Puntuación", ranked
		};
		while(!inputAccepted){
			int n = JOptionPane.showConfirmDialog(null,fields,"Calificar playlist pública",JOptionPane.OK_CANCEL_OPTION);
			if(JOptionPane.CANCEL_OPTION==n){
			inputAccepted=true;
			}else{
				playlistName=playlist.getText();
				rank=Integer.parseInt(ranked.getText());
				
				int[] playlistData = mscManager.findPlaylist(playlistName);
				
				if (playlistName.equals("")){
				  JOptionPane.showMessageDialog(null, "No puede dejar el nombre de playlist vacío","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if(rank<0 || rank==0){
					JOptionPane.showMessageDialog(null, "Puntuación no válida","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if (playlistData[0]!=1){
					JOptionPane.showMessageDialog(null, "La playlist no existe, por favor verifique","ERROR", JOptionPane.WARNING_MESSAGE);
				} else if (!mscManager.findPlaylistType(1, playlistData[1])){
					JOptionPane.showMessageDialog(null, "La playlist no es pública","ERROR", JOptionPane.WARNING_MESSAGE);
				} else {
					inputAccepted=true;
					mscManager.rankPlaylist(playlistData[1], rank);
					JOptionPane.showMessageDialog(null,"La playlist ha sido puntuada, consulte la lista de playlists para ver el promedio");
					
				}
			}
		
		}
	}

	public void addSongToPlaylist(){
		String playlistName="";
		String songName="";
		boolean inputAccepted=false;
		boolean inputAccepted2=false;
		
		JTextField playlist = new JTextField();
		JTextField song = new JTextField();
		
		//Create several inputs
		Object [] fields = {
			"Nombre de la playlist", playlist,
			"Canción a añadir", song
		};
		
		while(!inputAccepted){
			
			int n = JOptionPane.showConfirmDialog(null,fields,"Añadir canción a playlist",JOptionPane.OK_CANCEL_OPTION);
			String playlistSelection = (String)JOptionPane.showInputDialog(null,"Seleccione un tipo de playlist ","PLAYLISTS", JOptionPane.QUESTION_MESSAGE, null, new Object[] { "PÚBLICA","PRIVADA", "RESTRINGIDA"},"Seleccione");
			
			if(playlistSelection!=null){
				if(JOptionPane.CANCEL_OPTION==n){
				inputAccepted=true;
				}else{
					
					playlistName=playlist.getText();
					songName=song.getText();
					
					int[] playlistData = mscManager.findPlaylist(playlistName);
					int[] songData = mscManager.findSong(songName);
					
					
					if (playlistName.equals("")){
					  JOptionPane.showMessageDialog(null, "No puede dejar el nombre de playlist vacío","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if(songName.equals("")){
						JOptionPane.showMessageDialog(null, "No puede dejar el nombre de la canción vacío","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if(playlistData[0]!=1){
						JOptionPane.showMessageDialog(null, "La playlist no existe, por favor verifique","ERROR", JOptionPane.WARNING_MESSAGE);
					} else if (songData[0]!=1){
						JOptionPane.showMessageDialog(null, "La canción no existe, por favor verifique","ERROR", JOptionPane.WARNING_MESSAGE);
					}
					if(playlistSelection.equals("PÚBLICA")){
						while(!inputAccepted2){
							if(!mscManager.findPlaylistType(1, playlistData[1])){
								JOptionPane.showMessageDialog(null, "La playlist no es pública","ERROR", JOptionPane.WARNING_MESSAGE);
							}else {
								inputAccepted2=true;
							}
						}
					}
					if(playlistSelection.equals("PRIVADA")){
						while(!inputAccepted2){
							String name = JOptionPane.showInputDialog("Digite su nombre de usuario");
							if(name!=null){
								if(!mscManager.findPlaylistType(2, playlistData[1])){
									JOptionPane.showMessageDialog(null, "La playlist no es privada","ERROR", JOptionPane.WARNING_MESSAGE);
								}else if(!mscManager.checkOwner(name, playlistData[1])){
									JOptionPane.showMessageDialog(null, "No es un usuario autorizado","ERROR", JOptionPane.WARNING_MESSAGE);
								}else {
									inputAccepted2=true;
								}
							} 
						}
					}
					if(playlistSelection.equals("RESTRINGIDA")){
						while(!inputAccepted2){
							String name = JOptionPane.showInputDialog("Digite su nombre de usuario");
							if(!mscManager.findPlaylistType(3, playlistData[1])){
								JOptionPane.showMessageDialog(null, "La playlist no es restringida","ERROR", JOptionPane.WARNING_MESSAGE);
							}else if(!mscManager.findAuthorizedUser(name, playlistData[1])){
								JOptionPane.showMessageDialog(null, "No es un usuario autorizado","ERROR", JOptionPane.WARNING_MESSAGE);
							}else{
								inputAccepted2=true;
							}
						}
					}
					if(inputAccepted2){
						
						inputAccepted=true;
						mscManager.addSongToPlaylist(songData[1], playlistData[1]);
						JOptionPane.showMessageDialog(null,"La canción se añadió exitosamente");
						
					}
				}
			}else{
				inputAccepted=true;
			}
		}
		
	}

}
