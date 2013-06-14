package jp.mixi.practice.test.target;

/**
 * TODO: TestPractice2 の各テストケースをパスするメソッドを書く
 */
public class TestTarget2 {
    public boolean isValidLength(String string) {
    	int length = string.length();
    	if (length > 0 && length <= 10) {
    		return true;
    	} else {
    		return false;
    	}
    }

    public String formatTextCount(int count, int max) {
        return String.valueOf(count) + " / " + String.valueOf(max);
    }
}