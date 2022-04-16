abstract class Record<T, A> {
    var records = mutableListOf<T>()
    private var lastId = 0

    abstract fun update(record: T): Boolean
    abstract fun create(record: T): Int
    abstract fun delete(record: T): Boolean

    fun clear() {
        records.clear()
        lastId = 0
    }

    fun getLastId(): Int {
        lastId++
        return lastId
    }

    fun getCount(): Int {
        return records.size
    }

    abstract fun get(recordIds: A, offset: Int = 0, count: Int = 20, sort: Byte = 0): MutableList<T>
}