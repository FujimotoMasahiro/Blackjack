import controller.BlackjackController;

public class Main {
	public static void main(String[] args) {
		// ゲームコントローラーを作成し、ゲームを開始
		BlackjackController controller = new BlackjackController();
		controller.startGame();
	}
}