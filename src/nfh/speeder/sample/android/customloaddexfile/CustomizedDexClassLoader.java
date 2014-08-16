package nfh.speeder.sample.android.customloaddexfile;

import java.io.File;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import dalvik.system.DexClassLoader;

public class CustomizedDexClassLoader {

	private static Context context;

	private static DexClassLoader loader;

	public static void setContext(Context context) {
		CustomizedDexClassLoader.context = context;
	}

	public static DexClassLoader load(final String dexFileName) throws RuntimeException {
		if(null == context) {
			throw new RuntimeException("No context provided");
		}
		if(null == loader) {
			final File dexInternalStoragePath = new File(context.getDir("dex", Context.MODE_PRIVATE), dexFileName);
			if (!dexInternalStoragePath.exists()) {
				try {
					(new DexPreparationTask(context, dexFileName)).execute(dexInternalStoragePath).get();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				} catch (ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			final File optimizedDexOutputPath = context.getDir("outdex", Context.MODE_PRIVATE);

			loader = new DexClassLoader(dexInternalStoragePath.getAbsolutePath(), optimizedDexOutputPath.getAbsolutePath(), null, context.getClassLoader().getParent());
		}
		return loader;
	}
}