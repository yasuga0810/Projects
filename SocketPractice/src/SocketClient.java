

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ソケットClient用クラス
 * Eclipseから実行し、コマンドプロンプト上で表示させる
 */
public class SocketClient {
	public static void main(String[] args) throws IOException {

		//Clientはサーバーに接続をまず行う
		//接続のためにはIPアドレスとポートを指定する
		//インスタンスを生成するだけで接続を行う
		Socket server = new Socket("localhost", 9999);

		//サーバーに送る内容を設定する
		//OutputStreamをWriterに変換する
		//バイナリーで送るのではなく、テキストデータで扱いたいから変換している
		PrintWriter out = new PrintWriter(server.getOutputStream());

		/**
		 * 固定の内容を送る場合
		 */

		//出力させたい文字を入れる
		//		out.println("hello");

		//サーバー側の判定処理で処理を修了させる
		//		out.println("bye");

		/**
		 * コンソールに直接入力する場合
		 */

		//入力された値を受け取り、効率的に文字列を読み込む
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line = null;

		//ヌルではない限り、処理を継続する
		while ((line = br.readLine()) != null) {
			out.println(line);
			//Streamはデータを最後に送り出す必要がある
			out.flush();
			//byeと送られてきた場合、処理を修了する
			if ("bye".equals(line)) {
				break;
			}
		}
		br.close();
		server.close();

	}
}
