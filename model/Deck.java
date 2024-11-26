package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cards; // デッキ内のカードリスト

	// コンストラクタ: 52枚のカードを生成
	public Deck() {
		cards = new ArrayList<>();
		// スート（Suit）と値（Rank）を基にカードを生成
		for (String suit : new String[] { "スペード", "ハート", "ダイヤ", "クラブ" }) {
			for (int rank = 1; rank <= 13; rank++) {
				cards.add(new Card(suit, rank));
			}
		}
	}

	// デッキをシャッフルする
	public void shuffle() {
		Collections.shuffle(cards); // カードの順序をランダムにする
	}

	// デッキから1枚のカードを配布
	public Card drawCard() {
		if (cards.isEmpty()) {
			throw new IllegalStateException("デッキが空です！");
		}
		return cards.remove(0); // デッキの先頭からカードを取り出す
	}

	// 残りのカード枚数を取得
	public int getRemainingCards() {
		return cards.size();
	}
}