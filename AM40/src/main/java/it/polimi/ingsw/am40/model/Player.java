package it.polimi.ingsw.am40.model;

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
    /**Numberso of total aim done (private and public).
     *Under draw condition, the player with the highest number of aims done wins.*/
    private int numOfAimDone;


    //FLAG ATTRIBUTES

    /**Flag which indicates if the player is online, available in the game*/
    private boolean isOnline;
    /**Flag which indicates if the player was the first to play, basing on the order*/
    private boolean startingPlayer;





    //CONSTRUCTOR METHOD

    /**
     * Constructor for Player class, which create and initializes the attributes for a new player.
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
        this.startingPlayer = startingPlayer;
        this.game = game;
        this.privateBoard = new PrivateBoard();
        this.token = new Token();
        this.privateAim = privateAim;
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
    public boolean startingPlayer() {
        return startingPlayer;
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


    //PRIVATE METHODS

    /*
    **
     * Setter for color on Player class, calling the setter on Token class
     * @param color to set the new game piece color as Color (ENUM)
     *
    private void chooseInitialColor(Color color){
        this.token.setColor(color);
    }

    private void choosePrivateAim(){

    }

    private void chooseStartingCardFacing(CardFacing cardFacing){

    }
    */
    //PUBLIC METHODS
    public void refreshPlayerScore(int points){

    }
}
