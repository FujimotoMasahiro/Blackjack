package controller;

import java.util.Scanner;

import model.Dealer;
import model.Deck;
import model.Player;
import view.BlackjackView;

public class BlackjackController {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private BlackjackView view;
    private Scanner scanner;

    // コンストラクタ: 必要なインスタンスを初期化
    public BlackjackController() {
    	this.scanner = new Scanner(System.in);
        this.deck = new Deck();              // 新しいデッキを作成
        this.player = new Player("Player");  // プレイヤーを作成
        this.dealer = new Dealer("Dealer");  // ディーラーを作成
        this.view = new BlackjackView();     // 表示クラスを初期化
        this.scanner = new Scanner(System.in); // ユーザー入力用Scannerを初期化
    }

    // ゲームの開始
    public void startGame() {
        boolean keepPlaying = true; // ゲームを続けるフラグ

        while (keepPlaying) {
            resetGame(); // ゲームの状態をリセット

            // 1. ゲーム開始メッセージを表示
            view.displayStartMessage();

            // 2. カードのシャッフルと配布
            deck.shuffle(); // デッキをシャッフル
            player.addCard(deck.drawCard());
            player.addCard(deck.drawCard());
            dealer.addCard(deck.drawCard());
            dealer.addCard(deck.drawCard());

            // 3. 初期の手札を表示（ディーラーの2枚目は非公開）
            view.displayHand(player, dealer, false);

            // 4. プレイヤーのターン
            while (player.decideAction(scanner)) { // プレイヤーが「ヒット」を選択している間
                player.addCard(deck.drawCard()); // プレイヤーにカードを追加
                view.displayHand(player, dealer, false); // 手札を表示

                if (player.isBust()) { // プレイヤーがバーストした場合
                    view.displayBust(player);
                    break;
                }
            }

            // 5. ディーラーのターン（プレイヤーがバーストしていない場合）
            if (!player.isBust()) {
                view.displayHand(player, dealer, true); // ディーラーの手札を公開
                while (dealer.shouldDraw()) {          // ディーラーが引くべきならカードを引く
                    dealer.addCard(deck.drawCard());
                }
            }

            // 6. 勝敗の判定と結果表示
            view.displayResult(player, dealer);

            // 7. 再プレイの確認
            keepPlaying = view.askToPlayAgain(scanner);
        }
    }

    // ゲーム状態をリセット
    private void resetGame() {
        player.getHand().clear(); // プレイヤーの手札をリセット
        dealer.getHand().clear(); // ディーラーの手札をリセット
    }
}
