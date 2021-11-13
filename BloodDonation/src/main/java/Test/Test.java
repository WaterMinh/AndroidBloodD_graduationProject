package Test;
import java.util.List;

import com.bkap.dao.IHistoryDAO;
import com.bkap.dao.ImplHistoryDAO;
import com.bkap.entity.History;

public class Test {
	public static void main(String[] args) {
		IHistoryDAO dao = new ImplHistoryDAO();
		List<History> lst = dao.selectByUserId(1);
		System.out.println("Size : "  + lst.size());
	}
}
