package nfh.speeder.sample.android.customloaddexfile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.Context;
import android.os.AsyncTask;

public class DexPreparationTask extends AsyncTask<File, Void, Boolean> {

	private Context mContext;
	private String mDexFileName;
	private final int BUF_SIZE = 8 * 1024;

	public DexPreparationTask(Context context, String dexFileName) {
		mContext = context;
		mDexFileName = dexFileName;
	}

	@Override
	protected Boolean doInBackground(File... dexInternalStoragePaths) {
		prepareDex(dexInternalStoragePaths[0]);
		return null;
	}

	public boolean prepareDex(File dexInternalStoragePath) {
		BufferedInputStream bis = null;
		OutputStream dexWriter = null;

		try {
			bis = new BufferedInputStream(mContext.getAssets().open(mDexFileName));
			dexWriter = new BufferedOutputStream(new FileOutputStream(dexInternalStoragePath));
			byte[] buf = new byte[BUF_SIZE];
			int len;
			while ((len = bis.read(buf, 0, BUF_SIZE)) > 0) {
				dexWriter.write(buf, 0, len);
			}
			dexWriter.close();
			bis.close();
			return true;
		} catch (IOException e) {
			if (dexWriter != null) {
				try {
					dexWriter.close();
				} catch (IOException ioe) {
					throw new RuntimeException(ioe);
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException ioe) {
					throw new RuntimeException(ioe);
				}
			}
			return false;
		}
	}
}
