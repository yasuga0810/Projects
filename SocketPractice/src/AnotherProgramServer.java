
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ブラウザからの確認用クラス
 * ブラウザからのGET情報（1行目）が表示されればOK
 * ブラウザとJavaの連携を確認する
 * ブラウザではhelloが表示されればOK
 */
public class AnotherProgramServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		//9999のポート番号で待ち受けをする
		ServerSocket server = new ServerSocket(9999);

		//サーバーに接続(リクエスト)があるとaccept()はSocketを返却する
		Socket socket = server.accept();

		//送られてきたデータの読み出し
		InputStream is = socket.getInputStream();

		//送られ来たデータをバイナリからテキストに変換する
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		PrintWriter out = null;
		String line;

		try {
			line = br.readLine();
			out = new PrintWriter(socket.getOutputStream());
			//リクエストに応じてレスポンスを設定する
			if (line.contains("/hello")) {
				out.print("HTTP/1.1 200 OK");
				//ヘッダー情報を改行でスキップする
				out.print("\r\n");
				out.print("\r\n");
				out.print("hello");
				out.flush();
				
			} else if (line.contains("/bye")) {
				out.print("HTTP/1.1 200 OK");
				//ヘッダー情報を改行でスキップする
				out.print("\r\n");
				out.print("\r\n");
				out.print("bye");
				out.flush();
			}
		} finally {
			out.close();
			br.close();
			socket.close();
		}
	}
}
