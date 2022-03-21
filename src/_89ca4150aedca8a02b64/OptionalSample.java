package _89ca4150aedca8a02b64;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class OptionalSample {
    // IDをキー、名前を値に持つ名簿Map
    public static final Map<Integer, String> membershipList = Map.of(1, "James", 2, "Michael");

    public static void main(String... args) {
        // 値の取得
        Optional<String> optionalName = optionalLookUpNameById(1);
        String output = optionalName.get(); // Optionalの値はget()で取り出します。
        System.out.println(output); // James

        Optional<String> optionalEmpty = optionalLookUpNameById(0); // キーに0が存在しないので、空のOptionalが返ります。
        // name = optionalEmpty.get(); 空のOptionalから値を取り出そうとすると、NoSuchElementExceptionで実行時エラーとなります。

        output = optionalEmpty.orElse("No Such Element"); // 空のOptionalインスタンスが呼び出しているので、引数に渡された値が返されます。
        System.out.println(output); // No Such Element

        output = optionalEmpty.orElseGet(() -> "is Empty"); // 空のOptionalインスタンスが呼び出しているので、引数渡したラムダ式が値を返します。
        System.out.println(output); // is Empty

        // 値の存在確認
        System.out.println(optionalName.isPresent());// true
        System.out.println(optionalName.isEmpty()); // false

        // その他
        optionalName.ifPresent(s -> System.out.println("名前の文字数は" + s.length() + "文字です。")); // 名前の文字数は5文字です。

        Optional<String> nullableOptionalEmpty = nullableOptionalLookUpNameById(0);
        try {
            nullableOptionalEmpty.orElseThrow(() -> new Exception("値が存在しません。"));
        } catch (Exception e) { // 検査例外をThrowしたときはcatchしなければコンパイルエラーになります。
            System.out.println(e.getMessage()); // 値が存在しません。
        }

        // Optionalを使わない場合
        String name = lookUpNameById(1);
        System.out.println(name); // James

        String nameNull = lookUpNameById(0); // キーに0が存在しないので、nullが返ります。
        // System.out.println("名前の文字数は" + nameNull.length() + "文字です。"); コメントアウトすると、NullPointerExceptionで実行時エラーとなります。

        // 実行時エラーを避けるためにnullチェックが必要で、コードが冗長になります。
        Random random = new Random();
        int randomId = random.nextInt(3);
        String randomName = lookUpNameById(randomId);
        if(randomName == null) {
            System.out.println("指定されたidは存在しません。");
        }
        else {
            System.out.println(randomName);
        }
    }

    // インスタンスの生成
    public static Optional<String> optionalLookUpNameById(int id) {
        if(membershipList.containsKey(id)) {
            return Optional.of(membershipList.get(id));// idに対応するnameを値に持ったOptionalインスタンスを返します。
        }

        return Optional.empty(); // 空のインスタンスを返します。
    }

    public static Optional<String> nullableOptionalLookUpNameById(int id) {
        return Optional.ofNullable(membershipList.get(id)); // 存在しないidのときは空のインスタンスを返します。
    }

    // Optionalを使わない場合
    public static String lookUpNameById(int id) {
        return membershipList.get(id);
    }

}
