package Model;

public class player {
	
	private static int idCounter = 1;
	private Integer playerID;
	private String nickName;
	
	
	/* constructor && getters && setters method */
	public player(int playerID, String nickName) {
		super();
		this.playerID = idCounter++;
		this.nickName = nickName;
	}



	public static int getIdCounter() {
		return idCounter;
	}



	public static void setIdCounter(int idCounter) {
		player.idCounter = idCounter;
	}



	public Integer getPlayerID() {
		return playerID;
	}



	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}



	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
}
