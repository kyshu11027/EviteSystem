

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class PartyProgram {
	
	private BFF bff;
	private User currUser;
	private List<Party> partyList; 
	private Map<String, Host> registeredUsers; //set of emails representing registered users
	private boolean loggedIn;
	
	
	public PartyProgram() {
		bff = new BFF();
		currUser = null;
		partyList = new ArrayList<>();
		registeredUsers = new HashMap<>();
		loggedIn = false;
	}
	
	//METHODS TO WRITE TO THE FILES
	public void writePartyFile(List<Party> parties) {
		String fileName = "PartyFile.ser";
		try (FileOutputStream fs = new FileOutputStream(fileName)){
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(parties);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(fileName + " was not found.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeUserFile(Map<String, Host> registeredUsers) {
		/*String fileName = "UserFile.ser";
		try (FileOutputStream fs = new FileOutputStream(fileName)){
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(registeredUsers);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(fileName = " was not found.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String fileName = "UserFile.tsv";
		try (FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter pw = new PrintWriter(fos)){
			for(String email: registeredUsers.keySet()) {
				//System.out.println(registeredUsers.get(email).toFileString());
				pw.println(registeredUsers.get(email).toFileString());
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//METHODS TO READ THE FILES
	public List<Party> readPartyFile() {
		List<Party> tempMap = null;
		try (FileInputStream fs = new FileInputStream("PartyFile.ser");
			ObjectInputStream os = new ObjectInputStream(fs)){
			Object o = os.readObject();
			
			if (o instanceof List) {
				tempMap = (List<Party>)o;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Problem in reading Object File.");
		} 
		
		if (tempMap == null) {
			System.out.println("Error reading list from file, empty list will be created");
			tempMap = new ArrayList<>();
		}
		
		return tempMap;
		
	}
	
	public Map<String, Host> readUserFile() {
		Map<String, Host> tempMap = new HashMap<>();
		/*try (FileInputStream fs = new FileInputStream("UserFile.ser");
			ObjectInputStream os = new ObjectInputStream(fs)){
			Object o = os.readObject();
			
			if (o instanceof Map) {
				tempMap = (Map<String, Host>) o;
			}
			
			
		}*/
		try {
			//String header = fs.nextLine();
			//fs.useDelimiter("\t");
			FileInputStream fis = new FileInputStream("UserFile.tsv");
			Scanner fs = new Scanner(fis);
			//String header = fs.nextLine();
			while(fs.hasNextLine()) {
				try {
					String line = fs.nextLine();
					Host host = parseLineToHost(line);
					tempMap.put(host.getEmail(), host);
				} catch (Exception e) {
					System.out.println("Something went wrong saving the Host data.");
				}
				
			}
			fis.close();
			fs.close();
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Problem in reading file. " + e);
		} 
		
		/*if (tempMap == null) {
			System.out.println("The current file is empty, empty map will be created");
			tempMap = new HashMap<>();
		}*/
		
		return tempMap;
	}
	
	
	private Host parseLineToHost(String line) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(line);
		sc.useDelimiter("\t");
		
		try {
			String email = sc.next();
			String password = sc.next();
			return new Host(email, password);
		} catch(Exception e) {
			System.out.println("Something went wrong saving the Host data.");
			throw new Exception(line);
		}

		
	}

	public void run() {
		//MAKE SURE TO READ THE FILES BEFORE EACH RUN OF THE PROGRAM
		this.partyList = readPartyFile();
		this.registeredUsers = readUserFile();
		
		//This is the menu where the user can choose to do all of their actions.
		int choice = 0;
		while(Menu.values()[choice] != Menu.QUIT) {
			Menu.printMenuOptions();
			choice = bff.inputInt("Please choose an option: ", 0, Menu.getNumOptions()-1);
			switch (Menu.getOption(choice)) {
				case RSVP: 
					rsvp();
					break;
				//TODO: make the switch for all the methods and write the necessary functions
				case PLAN:
					plan();
					break;
				
				case ADD_GUEST:
					//Ask all of the inputs here so that the addGuest function can be used in different instances too.
					if (currUser instanceof Host) { // always need to make sure what the currUser is.
						if (((Host) currUser).getParties().size() != 0) {
							((Host)currUser).printParties();
							int partyChoice = bff.inputInt("Which party would you like to add a guest to?", 0, ((Host)currUser).getParties().size()-1);
							addGuest(((Host)currUser).getParties().get(partyChoice));
						}
						else {
							//accounting for all of the possible cases
							System.out.println("You have no planned parties.");
						}
					}
					else {
						//if currUser is a Guest, their functionality is very limited
						System.out.println("Guests cannot plan or edit parties. Guests can only RSVP to parties.");
					}
					break;
				
				case SHOW_PARTIES:
					showParties();
					break;
					
				case LOGOUT:
					//logout function is easy, just set the currUser variable to null and loggedIn to false.
					if(currUser != null) {
						currUser = null;
						loggedIn = false;
						System.out.println("Successfully Logged Out");
					}
					else {
						System.out.println("You are not logged into an account.");
					}
					System.out.println();
					break;
					
				case LOGIN:
					//login allows for both Guests and Hosts to log in. Guests have limited access, but can make themselves the currUser
					login();
					break;
				
				case REGISTER:
					//puts the user into the registeredUsers map so they can log in later.
					register();
					break;
					
				case QUIT:
					System.out.println("Have a great day!");
					break; 
					
				default:
					System.out.println("Bye!");
					
				
					
			}
		}
		writeUserFile(registeredUsers);
		writePartyFile(partyList);
		
	}
	
	private void showParties() {
		//method to show parties and whose going
		if(currUser instanceof Host) {
			if(((Host) currUser).getParties().size() == 0) {
				System.out.println("You have no planned parties at the moment.");
			}
			//iterate through all of the parties and print the guest lists. if they are Holiday or Potluck, print what people are bringing
			for (Party p: ((Host) currUser).getParties()) {
				System.out.println(p);
				System.out.println("Guest List: ");
				for (String email: p.getGuests().keySet()) {
					System.out.println("\t-" + email + ": " + p.getGuests().get(email).getDisplayString());
				}
				System.out.println();
				
				if(p instanceof Holiday) {
					((Holiday) p).printGifts();
				}
				else if (p instanceof Potluck) {
					((Potluck) p).printFoodMap();
				}
			}
		}
		else if (currUser instanceof Guest){
			System.out.println("You must be a Host to look at your planned parties.\n");
		}
		else {
			login();
			showParties();
		}
		
	}

	private void plan() {
		//initializing all the variables that are used for all of the parties
		String name = "";
		int age;
		String address = "";
		String day = "";
		String time = "";
		if (currUser instanceof Host) {//can only do this if they are a Host
			PartyType.printPartyOptions();
			int choice = bff.inputInt("What kind of party would you like to plan?", 0, PartyType.getNumOptions()-1);
			switch (PartyType.getOption(choice)) {
			case BIRTHDAY: 
				
				//getting all the variables to instantiate a birthday object
				name = bff.inputLine("What is the name of your party?");
				age = bff.inputInt("What is the birthday person's age?", 0, 120);
				boolean surprise = bff.inputYesNo("Is this a surprise party? (y/n)");
				String whoseBday = bff.inputLine("Whose birthday are you celebrating?");
				address = bff.inputLine("What is the address of the venue?");
				day = bff.inputLine("What date is your party? (MM/DD/YYYY)");
				time = bff.inputLine("What time is your party in military time? (XX:XX)");
				
				//if there's a mistake in input, we have a try catch mechanism so the program doesn't just crash. Same mechanism for all of the party objects
				try {
					LocalDateTime t = parseStringToTime(day, time);
					Birthday birthday = new Birthday(((Host) currUser), name, address, t, whoseBday, surprise, age);
					((Host)currUser).addParty(birthday);
					partyList.add(birthday);
					addGuest(birthday);
				}
				catch (BadTimeException be){
					System.out.println("There was a mistake inputting the time. Please try again.");
				}
				
				break;
				
			case POTLUCK:
				name = bff.inputLine("What is the name of your party?");
				address = bff.inputLine("What is the address of the venue?");
				day = bff.inputLine("What date is your party? (MM/DD/YYYY)");
				time = bff.inputLine("What time is your party in military time? (XX:XX)");
				
				try {
					LocalDateTime t = parseStringToTime(day, time);
					Potluck potluck = new Potluck(((Host) currUser), name, address, t);
					((Host)currUser).addParty(potluck);
					partyList.add(potluck);
					addGuest(potluck);
				}
				catch (BadTimeException be){
					System.out.println("There was a mistake inputting the time. Please try again.");
				}
				break;

			case HOLIDAY:
				name = bff.inputLine("What is the name of your party?");
				address = bff.inputLine("What is the address of the venue?");
				day = bff.inputLine("What date is your party? (MM/DD/YYYY)");
				time = bff.inputLine("What time is your party in military time? (XX:XX)");
				
				try {
					LocalDateTime t = parseStringToTime(day, time);
					Holiday holiday = new Holiday(((Host) currUser), name, address, t);
					((Host)currUser).addParty(holiday);
					partyList.add(holiday);
					addGuest(holiday);
				}
				catch (BadTimeException be){
					System.out.println("There was a mistake inputting the time. Please try again.");
				}
				break;

			case REUNION:
				name = bff.inputLine("What is the name of your party?");
				int numYears = bff.inputInt("How many years reunion is this?", 0, 120);
				address = bff.inputLine("What is the address of the venue?");
				day = bff.inputLine("What date is your party? (MM/DD/YYYY)");
				time = bff.inputLine("What time is your party in military time? (XX:XX)");
				
				try {
					LocalDateTime t = parseStringToTime(day, time);
					Reunion reunion = new Reunion(((Host) currUser), name, address, t, numYears);
					((Host)currUser).addParty(reunion);
					partyList.add(reunion);
					addGuest(reunion);
				}
				catch (BadTimeException be){
					System.out.println("There was a mistake inputting the time. Please try again.");
				}
				break;

			case FORMAL:
				name = bff.inputLine("What is the name of your party?");
				boolean hasFood = bff.inputYesNo("Will this formal have food? (y/n)");
				address = bff.inputLine("What is the address of the venue?");
				day = bff.inputLine("What date is your party? (MM/DD/YYYY)");
				time = bff.inputLine("What time is your party in military time? (XX:XX)");
				DressCode formalDressCode = chooseDressCode();
				
				try {
					LocalDateTime t = parseStringToTime(day, time);
					Formal formal = new Formal(((Host) currUser), name, address, t, formalDressCode, hasFood);
					((Host)currUser).addParty(formal);
					partyList.add(formal);
					addGuest(formal);
				}
				catch (BadTimeException be){
					System.out.println("There was a mistake inputting the time. Please try again.");
				}
				break;

			case PERFORMANCE:
				name = bff.inputLine("What is the name of your party?");
				address = bff.inputLine("What is the address of the venue?");
				day = bff.inputLine("What date is your party? (MM/DD/YYYY)");
				time = bff.inputLine("What time is your party in military time? (XX:XX)");
				DressCode performanceDressCode = chooseDressCode();
				PerformanceType performanceType = choosePerformanceType();
				
				try {
					LocalDateTime t = parseStringToTime(day, time);
					Performance performance = new Performance(((Host) currUser), name, address, t, performanceDressCode, performanceType);
					((Host)currUser).addParty(performance);
					partyList.add(performance);
					addGuest(performance);
				}
				catch (BadTimeException be){
					System.out.println("There was a mistake inputting the time. Please try again.");
				}
				break;

			case QUIT:
				System.out.println("Going back to main menu!");
				break; 

			default:
				System.out.println("Bye!");
			}
			generateInvites(currUser);
		}
		else if (currUser instanceof Guest) {
			//if they're just a guest they can't use this function
			System.out.println("Guests cannot plan or edit parties. Guests can only RSVP to parties.\n");
		}
		else {
			//if currUser is null, then they need to login to either a guest or a host and run the plan function again.
			login();
			plan();
		}
	}
	
	private PerformanceType choosePerformanceType() {
		// all this stuff is showing the options to be used in the plan method
		PerformanceType.printPerformanceOptions();
		int choice = bff.inputInt("Please choose an option to bring to the potluck: ", 0, PerformanceType.getNumOptions()-1);
		return PerformanceType.getOption(choice);
	}

	private DressCode chooseDressCode() {
		// all this stuff is showing the options to be used in the plan method
		DressCode.printDressOptions();
		int choice = bff.inputInt("Please choose an option to bring to the potluck: ", 0, DressCode.getNumOptions()-1);
		return DressCode.getOption(choice);
	}

	private LocalDateTime parseStringToTime(String d, String t) throws BadTimeException{
		//parsing through two different strings to make the LocalDateTime object
		Scanner scDay = new Scanner(d);
		Scanner scTime = new Scanner(t);
		scDay.useDelimiter("/");
		scTime.useDelimiter(":");
		try {
			int month = scDay.nextInt();
			int day = scDay.nextInt();
			int year = scDay.nextInt();
			int hour = scTime.nextInt();
			int minute = scTime.nextInt();
			scDay.close();
			scTime.close();
			return LocalDateTime.of(year, month, day, hour, minute);
		}
		catch(Exception e) {
			//throws this exception if there's a problem with the time input
			throw new BadTimeException("Problem parsing through time");
		}
		
		
	}
	
	private void addGuest(Party p) {
		//add guests to parties and continue asking for emails until entered -1
		String email = bff.inputLine("What is the email of the guest you would like to add? (Enter -1 to stop)");
		while(!email.equals("-1")) {
			p.addGuest(email);
			email = bff.inputLine("What is the email of the guest you would like to add? (Enter -1 to stop)");
		}
		System.out.println();
		
	}
	
	private void login() {
		// this method asks for an email to login, and if it's registered, it will login to the Host account.
		String email = bff.inputLine("Please input the email you're logging into.");
		int count = 0;
		
		if (isRegistered(email)) {
			while (loggedIn == false && count <3) {
				String attempt = bff.inputWord("Password Attempt (" + (count + 1) + " out of 3): ");
				loggedIn = registeredUsers.get(email).checkPassword(attempt);
				count++;
			}
			
			if (loggedIn) {
				currUser = registeredUsers.get(email);
				generateInvites(currUser);//need to update their invite list
				updatePartyList(currUser);//need to update their party list
				System.out.println("Successfully logged in!");
			}
			else {
				System.out.println("Login Failed");
			}
			System.out.println();
		}
		else {
			//not registered as a Host, just a guest.
			System.out.println("This email is not registered. You can still check your RSVP's as a guest.");
			currUser = new Guest(email); //add all of the parties in which they're email is mentioned
			//change the map for parties to emails not guests and add all of the parties with the guest's emails to their invites list
			generateInvites(currUser);
			loggedIn = false;
		}
	}
	
	//updates the currUser's party list if they are a host.
	private void updatePartyList(User host) {
		((Host) host).getParties().clear();
		for (Party p: partyList) {
			if (p.getHost().equals(currUser)) {
				((Host) host).getParties().add(p);
			}
		}
	}
	
	//this generates the invites for the currUser every time they log in.
	private void generateInvites(User user) {
		((Guest) user).getInvites().clear(); //in case the user's invites list is already filled we want to make sure there are no repeats so we just clear it.
		for (Party p: partyList) {
			if (p.getGuests().containsKey(user.getEmail())) {
				((Guest) user).getInvites().add(p);
			}
		}
	}
	
	private void rsvp() {
		// TODO check all of the invites in the currUser's invite list. Then ask yes or no, and add to the party's coming list. if currUser is 
		if (currUser == null) {//make sure to log in until the user is filled
			login();
		}
		
		if(currUser != null) {
			if (((Guest) currUser).getInvites().size() == 0) {//check if there are 0 invites to let them know
				System.out.println("You have no pending invites.");
			}
			//iterate through the invites of currUser
			for (Party p: ((Guest) currUser).getInvites()) {
				if (p.getGuests().get(currUser.getEmail()) == Response.TENTATIVE) {
					//use the HasDressCode interface
					if (p instanceof HasDressCode) {
						System.out.println("This party has a dress code " + "(" + ((HasDressCode)p).getDressCode().getDisplayString() + ")");
					}
					
					boolean yesNo = bff.inputYesNo("Would you like to attend " + p + "? (y/n)");
					
					if (yesNo) {
						p.getGuests().put(currUser.getEmail(), Response.COMING);
						//MUST GO THROUGH ALL THE DIFFERENT PARTIES AND ASK WHAT THEY'RE BRINGING
						if (p instanceof Birthday) {
							if (((Birthday) p).isSurprise()) {
								System.out.println("This party is a surprise party. Come inconspicuously and early!");
							}
						}
						else if (p instanceof Potluck) {
							((Potluck) p).printFoodMap();
							chooseFoodOption(p);
						}
						else if (p instanceof Holiday) {
							((Holiday) p).printGifts();
							String gift = bff.inputLine("What gift are you planning on bringing?");
							((Holiday) p).addGift(gift);
						}

						System.out.println("Have fun at the party!");



					}
					else {
						p.getGuests().put(currUser.getEmail(), Response.NOT_COMING); //if not coming, remove them from the guest list
					}
					
				}
			}
		}
	}

	private void chooseFoodOption(Party party) {
		// all this stuff is showing the options to be used in the plan method
		FoodType.printFoodOptions();
		int choice = bff.inputInt("Please choose an option to bring to the potluck: ", 0, FoodType.getNumOptions());
		((Potluck) party).addFood(FoodType.getOption(choice));			
			
	}

	private void register() {
		//putting the email and password into the map. the email is the key and the Host object is the value
		String email = bff.inputLine("Please input your email.");
		if (isRegistered(email)){
			System.out.println("The email is already registered.");
		}
		else {
			String password = bff.inputLine("Please input your password.");
			registeredUsers.put(email, new Host(email, password));
		}
		
		
		
	}

	private boolean isRegistered(String email) {
		//use this as a tool to make sure the email is registered
		return registeredUsers.containsKey(email);
	}

	//MAIN METHOD
	public static void main(String[] args) {
		PartyProgram p = new PartyProgram();
		p.run();
	}
}
