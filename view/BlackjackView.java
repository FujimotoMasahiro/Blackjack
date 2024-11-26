package view;

import java.util.Scanner;

import model.Dealer;
import model.Human;

public class BlackjackView {

	private int messageSpeed = 10;

	// ゲーム開始時のメッセージを表示
	public void displayStartMessage() {
		printSlowly("=== ブラックジャックを始めます！ ===", messageSpeed); // 100msごとに1文字表示
		printSlowly("ルール: 21を超えないようにカードを引いてください。", messageSpeed); // 100msごとに1文字表示
		printSlowly("ディーラーと手札の合計を競います！", messageSpeed); // 100msごとに1文字表示
		System.out.println();
	}

	// プレイヤーとディーラーの手札を表示
	public void displayHand(Human human, Dealer dealer, boolean showDealerAllCards) {

		printSlowly("【プレイヤーの手札】", messageSpeed); // 100msごとに1文字表示
		printSlowly(human.getHandDetails(), messageSpeed); // 100msごとに1文字表示クラスの手札情報を表示
		printSlowly("現在の合計: " + human.getTotal(), messageSpeed); // 100msごとに1文字表示
		System.out.println();

		printSlowly("【ディーラーの手札】", messageSpeed); // 100msごとに1文字表示
		if (showDealerAllCards) {
			// ディーラーの全てのカードを表示
			printSlowly(dealer.getHandDetails(), messageSpeed); // 100msごとに1文字表示
			printSlowly("現在の合計: " + dealer.getTotal(), messageSpeed); // 100msごとに1文字表示
		} else {
			// ディーラーの1枚目のカードだけを表示
			printSlowly(dealer.getFirstCardDetails(), messageSpeed); // 100msごとに1文字表示
			printSlowly("2枚目以降は非公開", messageSpeed); // 100msごとに1文字表示
		}
		System.out.println();
	}

	// プレイヤーがバーストした場合のメッセージを表示
	public void displayBust(Human human) {

		printSlowly("残念！" + human.getName() + "はバーストしました（21を超えました）。", messageSpeed); // 100msごとに1文字表示
		printSlowly("ゲーム終了です。", messageSpeed); // 100msごとに1文字表示
	}

	// ゲーム結果を表示
	public void displayResult(Human human, Dealer dealer) {
		printSlowly("=== ゲーム結果 ===", messageSpeed); // 100msごとに1文字表示
		printSlowly("プレイヤーの合計: " + human.getTotal(), messageSpeed); // 100msごとに1文字表示
		printSlowly("ディーラーの合計: " + dealer.getTotal(), messageSpeed); // 100msごとに1文字表示

		if (human.isBust()) {
			printSlowly("プレイヤーがバーストしたため、ディーラーの勝利です！", messageSpeed); // 100msごとに1文字表示
		} else if (dealer.isBust()) {
			printSlowly("ディーラーがバーストしたため、プレイヤーの勝利です！", messageSpeed); // 100msごとに1文字表示
		} else if (human.getTotal() > dealer.getTotal()) {
			printSlowly("プレイヤーの勝利です！", messageSpeed); // 100msごとに1文字表示
		} else if (human.getTotal() < dealer.getTotal()) {
			printSlowly("ディーラーの勝利です！", messageSpeed); // 100msごとに1文字表示
		} else {
			printSlowly("引き分けです！", messageSpeed); // 100msごとに1文字表示
		}
		System.out.println();

	}

	// メッセージを指定した間隔で1文字ずつ表示するメソッド
	public static void printSlowly(String message, int delayInMillis) {
		for (char ch : message.toCharArray()) { // 文字列を1文字ずつ取り出す
			System.out.print(ch); // 1文字を出力（改行なし）
			try {
				Thread.sleep(delayInMillis); // 指定したミリ秒だけ待機
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // 割り込み時の処理
				System.out.println("\n処理が中断されました。");
				break;
			}
		}
		System.out.println(); // 最後に改行
	}

	// 再プレイの確認
	public boolean askToPlayAgain(Scanner scanner) {
		printSlowly("もう一度遊びますか？", messageSpeed);
		printSlowly("1: 続ける", messageSpeed);
		printSlowly("2: 終了する", messageSpeed);
		System.out.print("選択: ");

		int choice = scanner.nextInt();
		while (choice != 1 && choice != 2) { // 不正入力の防止
			printSlowly("無効な入力です。もう一度選択してください。", messageSpeed);
			System.out.print("選択: ");
			choice = scanner.nextInt();
		}
		return choice == 1; // 1を選択した場合、再プレイ
	}
}