package model;

// トランプ1枚を表現するクラス
public class Card {
	private String suit; // カードのスート（例: スペード）
	private int rank; // カードの値（例: 1～13）

	// コンストラクタ: スートと値を設定
	public Card(String suit, int rank) {
		this.suit = suit; // スートを設定
		this.rank = rank; // 値を設定
	}

	// カードのスートを取得
	public String getSuit() {
		return suit;
	}

	// カードの値を取得
	public int getRank() {
		return rank;
	}

	// カードのゲーム内の値を取得（J, Q, K=10, A=1 or 11）
	public int getValue() {
		if (rank >= 10) { // 10以上のカード（J, Q, K）は10とする
			return 10;
		} else if (rank == 1) { // エースの場合は11（柔軟に扱えるようにする）
			return 11;
		} else { // それ以外はそのままの値
			return rank;
		}
	}

	// このカードがエースかどうかを判定
	public boolean isAce() {
		return rank == 1;
	}

	// カードの文字列表現を返す（例: スペードのA, ハートのK）
	@Override
	public String toString() {
		String rankString;
		switch (rank) {
		case 1:
			rankString = "A"; // エース
			break;
		case 11:
			rankString = "J"; // ジャック
			break;
		case 12:
			rankString = "Q"; // クイーン
			break;
		case 13:
			rankString = "K"; // キング
			break;
		default:
			rankString = String.valueOf(rank); // 2～10
		}
		return suit + "の" + rankString; // 例: スペードのA
	}
}