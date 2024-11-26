package model;

import java.util.Scanner;

// ユーザー操作専用のプレイヤークラス
public class Player extends Human {

	// コンストラクタ: プレイヤーの名前を設定
	public Player(String name) {
		super(name); // 親クラス（Human）のコンストラクタを呼び出し
	}

	// プレイヤーにヒット（カードを引く）かスタンド（止める）を選択させる
	public boolean decideAction(Scanner scanner) {
		System.out.println(getName() + "さん、次の行動を選んでください:");
		System.out.println("1: ヒット（カードを引く）");
		System.out.println("2: スタンド（止める）");
		System.out.print("選択: ");

		int choice = scanner.nextInt(); // ユーザーの入力を受け取る
		while (choice != 1 && choice != 2) { // 入力が1または2でない場合、再入力を促す
			System.out.print("無効な入力です。もう一度選択してください: ");
			choice = scanner.nextInt();
		}

		return choice == 1; // 1ならヒット、2ならスタンド
	}

	// 手札の情報を文字列として取得
	@Override
	public String getHandDetails() {
		StringBuilder details = new StringBuilder(); // 手札情報を格納するStringBuilder
		for (Card card : getHand()) {
			details.append(card).append(" "); // 各カードを文字列として追加
		}
		return details.toString().trim(); // 余分な空白を削除して返す
	}
}