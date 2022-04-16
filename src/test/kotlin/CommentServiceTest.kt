import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


val comment1 = Comment(
    id = 0,
    nid = 1,
    message = "Comment 1",
    date = 0,
)

val comment2 = Comment(
    id = 0,
    nid = 1,
    message = "Comment 2",
    date = 0,
)


class CommentServiceTest {
    @Before
    fun init() {
        NoteService.create(note1)
        CommentService.create(comment1)
    }

    @Test
    fun update_true() {
        val comment = comment2.copy(id = 1)
        val result = CommentService.update(comment)
        Assert.assertEquals(true, result)
    }

    @Test
    fun update_false() {
        val comment = comment2.copy(id = 3)
        val result = CommentService.update(comment)
        Assert.assertEquals(false, result)
    }

    @Test
    fun create_id() {
        val resultId = CommentService.create(comment2)
        Assert.assertNotEquals(0, resultId)
    }

    @Test
    fun create_count() {
        CommentService.create(comment2)
        val result = CommentService.getCount()
        Assert.assertEquals(2, result)
    }

    @Test
    fun restore() {
        val comment = comment1.copy(id = 1)
        CommentService.delete(comment)
        val resultBefore = CommentService.getCount()
        CommentService.restore(1)
        val resultAfter = CommentService.getCount()
        Assert.assertEquals(resultBefore + 1, resultAfter)
    }


    @Test
    fun delete_find() {
        val comment = comment1.copy(id = 1)
        val result = CommentService.delete(comment)
        Assert.assertEquals(true, result)
    }

    @Test
    fun delete_notFind() {
        val comment = comment2.copy(id = 4)
        val result = CommentService.delete(comment)
        Assert.assertEquals(false, result)
    }

    @After
    fun clear() {
        NoteService.clear()
        CommentService.clear()
    }
}