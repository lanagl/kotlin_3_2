import java.util.*

object CommentService : Record<Comment, Int>() {
    private var deletedComments = mutableListOf<Comment>()


    override fun update(record: Comment): Boolean {
        for ((index, item) in records.withIndex()) {
            if (item.id == record.id) {
                return try {
                    records[index] = record
                    true
                } catch (e: RuntimeException) {
                    false
                } catch (e: Exception) {
                    false
                } catch (e: Throwable) {
                    false
                }
            }
        }
        return false
    }

    override fun create(record: Comment): Int {
        val now = Date().time
        val tmpComment = record.copy(id = getLastId(), date = now)
        return try {
            records.add(tmpComment)
            val note = NoteService.records.find { elem -> elem.id == record.nid }
            if (note?.comments != null) {

                note.comments++
            }
            tmpComment.id
        } catch (e: RuntimeException) {
            0
        } catch (e: Exception) {
            0
        } catch (e: Throwable) {
            0
        }
    }

    fun restore(commentId: Int): Boolean {
        val comment = deletedComments.find { elem -> elem.id == commentId }
        return if (comment != null) {
            records.add(comment)
            val note = NoteService.records.find { elem -> elem.id == comment.nid }
            if (note != null) {
                note.comments++
            }
            deletedComments.remove(comment)
            true
        } else {
            false
        }
    }

    override fun get(recordIds: Int, offset: Int, count: Int, sort: Byte): MutableList<Comment> {
        return records
            .filter { comment -> comment.nid == recordIds }
            .sortedWith(compareBy { it.id })
            .subList(offset, offset + count)
                as MutableList<Comment>
    }

    override fun delete(record: Comment): Boolean {
        val tmpComment = records.find { elem -> elem.id == record.id }
        val noteId = tmpComment?.nid
        return if (tmpComment != null) {
            deletedComments.add(tmpComment)
            val note = NoteService.records.find { elem -> elem.id == noteId }
            if (note != null) {
                var commentsCount = note.comments
                note.comments = if (commentsCount < 1) 0 else commentsCount--

            }
            records.remove(tmpComment)
            true
        } else {
            false
        }
    }


}