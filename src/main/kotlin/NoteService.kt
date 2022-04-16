import java.util.*

object NoteService : Record<Note, ArrayList<Int>>() {
    private var deletedNotes = mutableListOf<Note>()


    override fun create(record: Note): Int {
        val now = Date().time
        val tmpNote = record.copy(id = getLastId(), date = now)

        return try {
            records.add(tmpNote)
            tmpNote.id
        } catch (e: RuntimeException) {
            0
        } catch (e: Exception) {
            0
        } catch (e: Throwable) {
            0
        }
    }

    override fun update(record: Note): Boolean {
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
            return false
        }
        return false


    }

    override fun get(recordIds: ArrayList<Int>, offset: Int, count: Int, sort: Byte): MutableList<Note> {
        return records
            .filter { note -> recordIds.contains(note.id) }
            .sortedWith(compareBy { it.id })
            .subList(offset, offset + count) as MutableList<Note>
    }

    fun getById(noteId: Int): Note? {
        return records.find { note -> note.id == noteId }
    }

    override fun delete(record: Note): Boolean {
        val note = records.find { elem -> elem.id == record.id }
        return try {
            records.remove(note)
        } catch (e: RuntimeException) {
            false
        } catch (e: Exception) {
            false
        } catch (e: Throwable) {
            false
        } finally {
            if (note != null) {
                deletedNotes.add(note)
            }
        }

    }

}
