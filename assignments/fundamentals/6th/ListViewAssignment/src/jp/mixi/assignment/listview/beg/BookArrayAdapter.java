
package jp.mixi.assignment.listview.beg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookArrayAdapter extends ArrayAdapter<Book> {
	private ArrayList<Book> books = new ArrayList<Book>();

    @SuppressWarnings("unused")
    private static final String TAG = BookArrayAdapter.class.getSimpleName();

    private LayoutInflater mLayoutInflater;

    public BookArrayAdapter(Context context, ArrayList<Book> list) {
        // 第2引数はtextViewResourceIdとされていますが、カスタムリストアイテムを使用する場合は特に意識する必要のない引数です
        super(context, 0, list);
        // レイアウト生成に使用するインフレーター
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        // ListViewに表示する分のレイアウトが生成されていない場合レイアウトを作成する
        if (convertView == null) {
            // レイアウトファイルからViewを生成する
            view = mLayoutInflater.inflate(R.layout.list_item_book, parent, false);

        } else {
            // レイアウトが存在する場合は使いまわす
            view = convertView;
        }

        // リストアイテムに対応するデータを取得する
        Book book = this.books.get(position);

        // 各Viewに表示する情報を設定
        TextView text1 = (TextView) view.findViewById(R.id.Title);
        text1.setText(book.getTitle());
        TextView text2 = (TextView) view.findViewById(R.id.Publisher);
        text2.setText(book.getPublisher());
        TextView text3 = (TextView) view.findViewById(R.id.Price);
        text3.setText(book.getPrice());

        return view;
    }

}
