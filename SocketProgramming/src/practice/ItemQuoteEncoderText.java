package practice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class ItemQuoteEncoderText implements ItemQuoteEncoder, ItemQuoteTextConst {

	private String encoding;//文字エンコード方式

	public ItemQuoteEncoderText() {
		encoding = DEFAULT_ENCODING;
	}

	public ItemQuoteEncoderText(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public byte[] encode(ItemQuote item) throws Exception {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		OutputStreamWriter out = new OutputStreamWriter(buf, encoding);
		out.write(item.itemNumber + " ");

		//商品説明に区切り文字があったら例外を投げる
		if(item.itemDescription.indexOf('\n') != -1) {
			throw new IOException("Invalid description (contrains newline)");
		}

		out.write(item.itemDescription + "\n" + item.quantity + " " + item.unitPrice);

		if(item.discounted) {
			out.write('d');//値引きがある場合のみ「d」を挿入する
		}
		if(item.inStock) {
			out.write('s');//在庫がある場合のみ「s」を挿入する
		}
		out.write('\n');
		out.flush();

		//エンコード結果の文字列の長さが規定値を超えたら例外を投げる
		if(buf.size() > MAX_WIRE_LENGTH) {
			throw new IOException("Encoded length too long");
		}

		return buf.toByteArray();
	}

}
