package test.com.tp.unit.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import test.com.tp.spring.SpringTxTestCase;

import com.tp.dao.unlock.FileItemDao;
import com.tp.dao.unlock.UnlockFileDao;
import com.tp.entity.unlock.UnlockFile;
import com.tp.entity.unlock.FileType.*;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UnlockFileDaoTest extends SpringTxTestCase {

	private FileItemDao fileItemDao;
	private UnlockFileDao unlockFileDao;

	@Test
	@Rollback(false)
	public void saveApk() {
		Apk apk = new Apk();
		apk.setName("test");
		apk.setPath("/aa/bb");
		apk.setSize(12L);
		fileItemDao.save(apk);
		Ux ux = new Ux();
		ux.setName("ux");
		ux.setPath("/aa/bb");
		ux.setSize(13L);
		fileItemDao.save(ux);
		UnlockFile unlock = new UnlockFile();
		unlock.setUx(ux);
		unlock.setApk(apk);
		unlock.setTitle("test");
		unlockFileDao.save(unlock);
		unlockFileDao.delete(unlock.getId());
	}

	@Autowired
	public void setFileItemDao(FileItemDao fileItemDao) {
		this.fileItemDao = fileItemDao;
	}

	@Autowired
	public void setUnlockFileDao(UnlockFileDao unlockFileDao) {
		this.unlockFileDao = unlockFileDao;
	}
}
