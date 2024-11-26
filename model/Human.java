package model;

import java.util.ArrayList;
import java.util.List;

// プレイヤーやディーラーの共通機能を提供する抽象クラス
public abstract class Human {
	protected String name; // 名前（プレイヤー名またはディーラー名）
	private List<Card> hand; // 手札（カードのリスト）

	// コンストラクタ: 名前を設定し、手札を初期化
	public Human(String name) {
		this.name = name; // 名前を設定
		this.setHand(new ArrayList<>()); // 空の手札を作成
	}

	// 名前を取得するメソッド
	public String getName() {
		return name;
	}

	// カードを手札に追加するメソッド
	public void addCard(Card card) {
		getHand().add(card); // カードを手札リストに追加
	}

	// 手札の合計値を計算するメソッド
	public int getTotal() {
		int total = 0; // 合計値を初期化
		int aceCount = 0; // 手札に含まれるエースの数

		for (Card card : getHand()) {
			total += card.getValue(); // 各カードの値を合計
			if (card.isAce()) { // エースの場合カウント
				aceCount++;
			}
		}

		// エースを柔軟に扱う: 合計が21を超えない範囲で11として計算
		while (total > 21 && aceCount > 0) {
			total -= 10; // エースを1として扱う
			aceCount--; // 残りのエースを減らす
		}

		return total; // 合計値を返す
	}

	// プレイヤーがバースト（21を超える）したか判定
	public boolean isBust() {
		return getTotal() > 21; // 合計値が21を超えたらバーストとみなす
	}

	// 手札の情報を文字列として取得（各サブクラスで実装する抽象メソッド）
	public abstract String getHandDetails();

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
}