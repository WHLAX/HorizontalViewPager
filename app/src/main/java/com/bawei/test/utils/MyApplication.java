package com.bawei.test.utils;


import android.app.Application;

import com.bawei.test.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		imgto();
	}

	private void imgto() {
		// TODO Auto-generated method stub
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisk(true)
				.showImageOnLoading(R.mipmap.ic_launcher)
				.showImageOnFail(R.mipmap.ic_launcher)
				.showImageOnFail(R.mipmap.ic_launcher).build();
		int Maxsize = (int) (Runtime.getRuntime().maxMemory() / 8);
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.discCache(new UnlimitedDiskCache(getCacheDir()))
				.memoryCache(new UsingFreqLimitedMemoryCache(Maxsize))
				.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 1)
				.defaultDisplayImageOptions(options).build();
		ImageLoader.getInstance().init(configuration);
	}

}
