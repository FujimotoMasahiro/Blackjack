package model;

// ディーラー専用のクラス
public class Dealer extends Human {

	// コンストラクタ: ディーラーの名前を設定
	public Dealer(String name) {
		super(name); // 親クラス（Human）のコンストラクタを呼び出し
	}

	// ディーラーの手札を文字列形式で取得
	@Override
	public String getHandDetails() {
		StringBuilder details = new StringBuilder(); // 手札情報を格納するStringBuilder
		for (Card card : getHand()) {
			details.append(card).append(" "); // 各カードを文字列として追加
		}
		return details.toString().trim(); // 余分な空白を削除して返す
	}

	// ディーラーが最初に公開するカードの情報を取得
	public String getFirstCardDetails() {
		if (!getHand().isEmpty()) {
			return getHand().get(0).toString(); // 手札の1枚目を取得
		}
		return "カードなし"; // 手札が空の場合
	}

	// ディーラーがカードを引くべきか判定
	public boolean shouldDraw() {
		return getTotal() < 17; // 手札の合計値が17未満ならカードを引く
	}
}