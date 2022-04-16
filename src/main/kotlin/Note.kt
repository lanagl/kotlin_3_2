data class Note(
    val id: Int,
    var title: String,
    var text: String,
    val date: Long,
    var comments: Int = 0,
    var readComments: Int = 0,
    val viewUrl: String? = "",
    var privacyView: ArrayList<String>? = arrayListOf(),
    var privacyComment: ArrayList<String>? = arrayListOf(),
    var canComment: Boolean? = true,
    var textWiki: String? = "",
)




