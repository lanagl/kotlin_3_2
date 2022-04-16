import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

val note1 = Note(
    id = 0,
    title = "Title 1",
    text = "Text 1",
    date = 0,
    comments = 0,
    readComments = 0,
    viewUrl = "",
    privacyView = arrayListOf(),
    privacyComment = arrayListOf(),
    canComment = true,
    textWiki = "",
)

val note2 = Note(
    id = 0,
    title = "Title 2",
    text = "Text 2",
    date = 0,
    comments = 0,
    readComments = 0,
    viewUrl = "",
    privacyView = arrayListOf(),
    privacyComment = arrayListOf(),
    canComment = true,
    textWiki = "",
)
val note3 = Note(
    id = 1,
    title = "Title 3",
    text = "Text 3",
    date = 0,
    comments = 0,
    readComments = 0,
    viewUrl = "",
    privacyView = arrayListOf(),
    privacyComment = arrayListOf(),
    canComment = true,
    textWiki = "",
)
val note4 = Note(
    id = 4,
    title = "Title 4",
    text = "Text 4",
    date = 0,
    comments = 0,
    readComments = 0,
    viewUrl = "",
    privacyView = arrayListOf(),
    privacyComment = arrayListOf(),
    canComment = true,
    textWiki = "",
)


class NoteServiceTest {

    @Before
    fun init() {
        NoteService.create(note1)
    }

    @Test
    fun create_id() {
        val resultId = NoteService.create(note2)
        Assert.assertNotEquals(0, resultId)
    }

    @Test
    fun create_count() {
        NoteService.create(note2)
        val result = NoteService.getCount()
        Assert.assertEquals(2, result)
    }

    @Test
    fun update_true() {
        val note = note3.copy(id = 1)
        val result = NoteService.update(note)
        Assert.assertEquals(true, result)
    }

    @Test
    fun update_false() {
        val note = note3.copy(id = 3)
        val result = NoteService.update(note)
        Assert.assertEquals(false, result)
    }


    @Test
    fun getById_find() {
        val result = NoteService.getById(1)
        Assert.assertEquals(1, result?.id)
    }

    @Test
    fun getById_notFind() {
        val result = NoteService.getById(5)
        Assert.assertEquals(null, result)
    }

    @Test
    fun delete_find() {
        val note = note1.copy(id = 1)
        val result = NoteService.delete(note)
        Assert.assertEquals(true, result)
    }

    @Test
    fun delete_notFind() {
        val note = note4.copy(id = 4)
        val result = NoteService.delete(note)
        Assert.assertEquals(false, result)
    }

    @After
    fun clear() {
        NoteService.clear()
    }
}