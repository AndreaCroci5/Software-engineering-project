package it.polimi.ingsw.am40.server.model;

/**The Player class manages the player in the game,
 giving to the Game class the information about who is playing.
 Every player has a private space (one-to-one relation) in the game,
 called private board, which displays the cards played by the owner player.
 The game can start only if there are from 2 to 4 players.
 Every player has got a game piece, named Token.
 Lastly, a player has a specific aim to reach, represented by the aim card
 */
public class Player {
    //REFERENCE ATTRIBUTES

    /**Reference to the border (main) class "Game" in model*/
    private final Game game;
    /**Reference to the personal game area*/
    private final PrivateBoard privateBoard;
    /**Reference to the personal game piece (token)*/
    private final Token token;
    /**Reference to the private aim card*/
    private AimCard privateAim;


    //PLAYER ATTRIBUTES

    /**Player's nickname*/
    private String nickname;
    /**Updated score totaled in time*/
    private int score;
    /**Number of total aim done (private and public).
     *Under draw condition, the player with the highest number of aims done wins.*/
    private int numOfAimDone;

    //FLAG ATTRIBUTES

    /**Flag which indicates if the player is online, available in the game*/
    private boolean isOnline;
    /**Flag which indicates if the player was the first to play, basing on the order*/
    private boolean startingPlayer;

    /**
     * Flag which indicates if the player is currently playing the round
     */
    private boolean isCurrentlyPlaying;





    //CONSTRUCTOR METHOD

    /**
     * Constructor for Player class, which creates and initializes the attributes for a new player.
     * The parameters which are not taken in input are set on default value
     * @param nickname Nickname of the player (usually received from controller)
     * @param game as reference of Game in which the player is playing
     * @param privateAim  as reference of the private aim card
     */
    public Player(String nickname, Game game, AimCard privateAim) {
        this.nickname = nickname;
        this.score = 0;
        this.numOfAimDone = 0;
        this.isOnline = true;
        this.startingPlayer = false;
        this.game = game;
        this.privateBoard = new PrivateBoard();
        this.token = new Token();
        this.privateAim = privateAim;
        this.isCurrentlyPlaying = false;
    }

    /**
     * Constructor for Player class, which creates and initializes the attributes for a new player.
     * The parameters which are not taken in input are set on default value
     * @param nickname Nickname of the player (usually received from controller)
     * @param game as reference of Game in which the player is playing
     */
    public Player(String nickname, Game game) {
        this.nickname = nickname;
        this.score = 0;
        this.numOfAimDone = 0;
        this.isOnline = true;
        this.startingPlayer = false;
        this.game = game;
        this.privateBoard = new PrivateBoard();
        this.token = new Token();
        this.isCurrentlyPlaying = false;
    }


    //GETTER METHODS

    /**
     * Getter for Game reference
     * @return game as Game reference
     */
    public Game getGame() {
        return game;
    }

    /**
     * Getter for Private Board reference
     * @return privateBoard as Private Board reference
     */
    public PrivateBoard getPrivateBoard() {
        return privateBoard;
    }

    /**
     * Getter for Token reference
     * @return token as Token reference
     */
    public Token getToken() {
        return token;
    }

    /**
     * Getter for numOfAimDone
     * @return the number of aim done by the player as int
     */
    public int getNumOfAimDone() {
        return numOfAimDone;
    }

    /**
     * Getter for nickname
     * @return the nickname of the player as String
     */
    public String getNickname(){
        return this.nickname;
    }

    /**
     * Getter for score
     * @return the actual score of the player as int
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for private aim
     * @return the private aim card as reference
     */
    public AimCard getPrivateAim() {
        return privateAim;
    }

    /**
     * Getter for isOnline
     * @return true if the player is online in the game (as boolean)
     */
    public boolean isOnline() {
        return isOnline;
    }

    /**
     * Getter for startingPlayer
     * @return true if the player is the starting player of the game (as boolean)
     */
    public boolean isStartingPlayer() {
        return startingPlayer;
    }

    /**
     * Getter for isCurrentlyPlaying
     * @return true if the player is the one that is playing the round, false otherwise
     */
    public boolean isCurrentlyPlaying() {
        return isCurrentlyPlaying;
    }

    //SETTER METHODS

    /**
     * Setter for privateAim
     * @param aimCard to set the reference to the private aim card
     */
    public void setPrivateAim(AimCard aimCard){
        this.privateAim = aimCard;
    }

    /**
     * Setter for score
     * @param newScore to set the new value of the player score
     */
    public void setScore(int newScore){
        this.score = newScore;
    }

    /**
     * Increases the number of points of a specified value
     * @param pointsToAdd to increase the value of score by this int
     */
    public void increaseScore(int pointsToAdd){
        this.score += pointsToAdd;
    }

    /**
     * Increases the number of points by one
     */
    public void increaseScore(){
        this.score++;
    }

    /**
     * Setter for nickname
     * @param newNickname to set the new nickname of the Player
     */
    public void setNickname(String newNickname){
        this.nickname = newNickname;
    }

    /**
     * Setter for numOfAimDone
     * @param newNumOfAimDone to set the number of aims done
     */
    public void setNumOfAimDone(int newNumOfAimDone){
        this.numOfAimDone = newNumOfAimDone;
    }

    /**
     * Increases the number of aim done of a specified value
     * @param valueToAdd to increase the value of score by this int
     */
    public void increaseNumOfAimDone(int valueToAdd){
        this.numOfAimDone += valueToAdd;
    }

    /**
     * Increases the number of points by one
     */
    public void increaseNumOfAimDone(){
        this.numOfAimDone++;
    }

    /**
     * Setter for isCurrently Playing
     * @param currentlyPlaying is the player we want to set as current so the one that has to play this turn
     */
    public void setCurrentlyPlaying(boolean currentlyPlaying) {
        isCurrentlyPlaying = currentlyPlaying;
    }

    /**
     * Setter for isOnline.
     * It sets this attribute to true
     */
    public void setOnline() {
        isOnline = true;
    }

    /**
     * Setter for isOnline.
     * It sets this attribute to false
     */
    public void setOffline() {
        isOnline = false;
    }

    /**
     * Setter for isOnline
     * @param online to set isOnline to true or false
     */
    public void setIsOnline(boolean online) {
        isOnline = online;
    }

    /**
     * Setter for starting player
     * @param startingPlayer True for starting player, false (default) if not
     */
    public void setStartingPlayer(boolean startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    /**
     * Setter for starting player.
     * It sets the attribute to true
     */
    public void setStartingPlayerTrue(){
        this.startingPlayer = true;
    }

}
