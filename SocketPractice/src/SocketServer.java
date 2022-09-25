
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ソケットServer用クラス
 * コマンドプロンプトから起動し、待ち受けにする
 */
public class SocketServer {

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

		//無限ループで待ち受けを継続させる
		while (true) {
			String line = br.readLine();

			//データがnullの場合のハンドリングを行う
			//nullを表示しなくなるが、ストリームの取得は継続する
			if (line == null) {
				continue;
			}

			//byeが入力されると待ち受けを修了
			if ("bye".equals(line)) {
				break;
			}
			System.out.println(line);

		}
		socket.close();

	}

}
