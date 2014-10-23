
import org.mongodb.morphia.Datastore;

import com.tgy.App;
import com.tgy.entity.Bookmark;

public class TestMain {

	public static void main(String[] args) {
		try {

			Bookmark bk = new Bookmark();
			bk.name = "testbk";

			Datastore ds = App.getInstance().getDatastore();
			ds.save(bk);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
