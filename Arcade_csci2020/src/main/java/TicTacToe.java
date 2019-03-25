package mainApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

public class TicTacToe extends Application {

	//determines if player has won
	public boolean WIN;
	private char currentPlayer = 'X';

	private Cell[][] cell = new Cell[3][3]; //a 3x3 cell matrix which is the tictactoe board
	private Label status = new Label("---"); //announces if player or computer has won
	int wins = 0;
	int losses = 0;
	private Label score = new Label("Wins: " + wins + "\tLosses: " + losses + "\t"); //status of wins and losses of player

	private GridPane pane = new GridPane();
	public String temp;
	public String file;

	//sets the username from player database file
	public void settemp(String temp, String file) {
		this.temp = temp;
		this.file = file;
	}

	@Override
	public void start(Stage primaryStage) {
		WIN = false;

		//construct the board with cells
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cell[i][j] = new Cell();
				pane.add(cell[i][j], j, i);

			}
		}

		pane.setStyle("-fx-background-color: Beige");
		
		//resets the board (empties each cell)
		Button reset = new Button("Reset");
		reset.setOnAction(e -> {
			clearBoard();
		});

		status.setTranslateX(60);
		score.setTranslateX(50);
		reset.setTranslateX(25);
		HBox statusBar = new HBox(100);
		statusBar.getChildren().addAll(status, score, reset);

		//add nodes to scene
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(statusBar);

		Scene scene = new Scene(borderPane, 450, 300);
		primaryStage.setTitle("Tic Tac Toe");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//returns to the arcade menu once escape button is pressed
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case ESCAPE:
				if (WIN == true) {
					readAdd();
				}
				Scene3 hi = new Scene3();
				try {
					hi.settemp(temp, file);
					hi.start(primaryStage);
					hi.curentLevel();
					primaryStage.setWidth(900);
					primaryStage.setHeight(920);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			default:
				break;
			}
		});
	}

	//checks if the board is filled with x's and o's
	public boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cell[i][j].getPlayer() == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	
	public void readAdd() {
		CSV savea = new CSV();
		try {
			savea.read(temp, file, "1");
			savea.save(file);
		} catch (IOException e) {
		}

	}

	//determines if a player has won
	public boolean hasWon(char player) {
		for (int i = 0; i < 3; i++) {
			if (cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player
					&& cell[i][2].getPlayer() == player) {
				WIN = true;
				return true;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (cell[0][i].getPlayer() == player && cell[1][i].getPlayer() == player
					&& cell[2][i].getPlayer() == player) {
				WIN = true;
				return true;
			}
		}

		if (cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player) {
			WIN = true;
			return true;
		}

		if (cell[0][2].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer() == player) {
			WIN = true;
			return true;
		}
		return false;

	}

	//sets prperties of the cell
	public class Cell extends Pane {
		private char player = ' ';

		public Cell() {
			setStyle("-fx-border-color: black");
			this.setPrefSize(300, 300);
			this.setOnMouseClicked(e -> {
				try {
					handleClick();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			});
		}

		//once a cell is clicked, the current players move is recorded on the board
		//and checks if the currentplayer has won, or if it was a draw
		private void handleClick() throws InterruptedException {
			if (player == ' ' && currentPlayer == 'X') {
				setPlayer(currentPlayer);

				if (hasWon(currentPlayer)) {
					status.setText(currentPlayer + " won!");
					updateScore(currentPlayer);
					// TimeUnit.SECONDS.sleep(1);
					clearBoard();
				} else if (isBoardFull()) {
					status.setText("Draw!");
					currentPlayer = ' ';
					clearBoard();

				} else {
					if (currentPlayer == 'X') {
						currentPlayer = 'O';
						computerMove();

						if (hasWon(currentPlayer)) {
							status.setText(currentPlayer + " won!");
							updateScore(currentPlayer);
							TimeUnit.SECONDS.sleep(1);
							clearBoard();
						}
						currentPlayer = 'X';

					} else if (currentPlayer == 'O') {
						currentPlayer = 'X';
					}

				}
			}
		}

		//clears the board
		public void clearBoard() {
			pane.getChildren().clear();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					cell[i][j] = new Cell();
					pane.add(cell[i][j], j, i);

				}
			}
		}

		//returns current player
		public char getPlayer() {
			return player;
		}

		//updates the score status
		public void updateScore(char c) {
			if (c == 'X') {
				wins += 1;
				score.setText("Wins: " + wins + "\tLosses: " + losses + "\t");
			} else if (c == 'O') {
				losses += 1;
				score.setText("Wins: " + wins + "\tLosses: " + losses + "\t");
			}
		}

		//Determines the computers next move
		public void computerMove() {
			Random rand = new Random();

			int x = rand.nextInt(3);
			int y = rand.nextInt(3);

			if (cell[0][0].getPlayer() == 'X' && cell[1][1].getPlayer() == 'X' && cell[2][2].getPlayer() == ' ') {
				cell[2][2].setPlayer(currentPlayer);
			}

			else if (cell[0][2].getPlayer() == 'X' && cell[1][1].getPlayer() == 'X' && cell[2][0].getPlayer() == ' ') {
				cell[2][0].setPlayer(currentPlayer);
			}

			else if (cell[x][y].getPlayer() == ' ') {
				cell[x][y].setPlayer(currentPlayer);
			} else {
				computerMove();
			}
		}

		private Image X = new Image("x.png", 150, 85, false, false);
		private Image O = new Image("o.png", 150, 85, false, false);

		//creates an x or o on the selected cell 
		public void setPlayer(char c) {
			player = c;

			if (player == 'X') {
				getChildren().addAll(new ImageView(X));

			} else if (player == 'O') {
				getChildren().add(new ImageView(O));
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}