import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`

//ファイルからデータを読み込むデシリアライズ
object GsonUtils {
    //単体で取得
    fun <T> objectFromJson(json: String?, clazz: Class<T>?): T {
        return Gson().fromJson(json, clazz)
    }

    //listで取得
    fun <T> objectsFromJson(json: String?, clazz: Class<T>?): List<T> {
        val type = `$Gson$Types`.newParameterizedTypeWithOwner(null, ArrayList::class.java, clazz)
        return Gson().fromJson(json, type)
    }
}

//Gsonクラス
open class gsonData() {
    var dStr: String = "test"
    var dInt: Int = 1234
    var dDouble: Double = 5.6
}

//データ確認用
fun printGson(gsonData: gsonData) {
    println(gsonData.dStr)
    println(gsonData.dInt)
    println(gsonData.dDouble)
}

fun main() {
    //インスタンス生成
    val gsonData = gsonData()
    gsonData.dStr = "hello"
    gsonData.dInt = 9876
    gsonData.dDouble = 0.0

    //Gson形式に変更
    val str = Gson().toJson(gsonData)

    println(str)

    //保存したデータをGsonで読み込む
    val gs: gsonData = GsonUtils.objectFromJson(str, gsonData::class.java)
    printGson(gs)


    val gsonData2 = gsonData()
    gsonData.dStr = "buy"
    gsonData.dInt = 12223
    gsonData.dDouble = 3.0

    //配列データ
    val l_gson = listOf<gsonData>(gsonData, gsonData2)
    val str2 = Gson().toJson(l_gson)

    println(str2)

    val gs_list = GsonUtils.objectsFromJson(str2, gsonData::class.java)

    for (gs in gs_list) {
        printGson(gs)
    }

}
